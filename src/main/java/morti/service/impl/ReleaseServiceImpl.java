package morti.service.impl;


import morti.dto.Requirement;
import morti.dto.Release;
import morti.service.ReleaseRepositoryService;
import morti.service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ReleaseServiceImpl implements ReleaseService {

    private ReleaseRepositoryService releaseRepositoryService;

    @Autowired
    public ReleaseServiceImpl(ReleaseRepositoryService releaseRepositoryService) {
        this.releaseRepositoryService = releaseRepositoryService;
    }


    @Override
    public void createRelease(Release release) {
        release.setReleaseId(UUID.randomUUID().toString());
        releaseRepositoryService.createRelease(release);
        releaseRepositoryService.createReleaseProjectMapping(release.getReleaseId(), release.getProjectId());
        if (release.getRequirements() != null) {
            createReleaseRequirementMapping(release);
        }
    }

    public void createReleaseRequirementMapping(Release release) {
        List<Requirement> requirementList = release.getRequirements();

        for(Requirement requirement: requirementList) {
            releaseRepositoryService.createReleaseRequirementMapping(release.getReleaseId(), requirement.getRequirementId());
        }
    }

    @Override
    public Release getReleaseByReleaseId(String releaseId) {
        return releaseRepositoryService.getReleaseByReleaseId(releaseId);
    }

    @Override
    public List<Release> getAllRelease() {
        List<Release> releaseList = releaseRepositoryService.getAllRelease();

        for(Release release: releaseList) {
            release.setTotalEffort(releaseRepositoryService.getReleaseEffort(release));
        }
        return releaseList;
    }

    @Override
    public List<Release> getAllReleaseByProjectId(String projectId) {
        return releaseRepositoryService.getAllReleaseByProjectId(projectId);
    }
}
