package morti.service;

import morti.dto.Release;

import java.util.List;

/**
 * Created by mark on 05/11/2016.
 */
public interface ReleaseRepositoryService {

    void createRelease(Release release);

    void createReleaseProjectMapping(String releaseId, String projectId);

    void createReleaseRequirementMapping(String releaseId, String requirementId);

    Release getReleaseByReleaseId(String releaseId);

    List<Release> getAllRelease();

    int getReleaseEffort(Release release);

    List<Release> getAllReleaseByProjectId(String projectId);

}
