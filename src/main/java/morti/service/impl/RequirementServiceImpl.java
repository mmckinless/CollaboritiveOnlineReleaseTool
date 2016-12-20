package morti.service.impl;

import morti.dto.Requirement;
import morti.dto.RequirementConflict;
import morti.dto.RequirementValue;
import morti.service.RequirementRepositoryService;
import morti.service.RequirementService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RequirementServiceImpl implements RequirementService {

    private Logger LOGGER = Logger.getLogger(RequirementServiceImpl.class);

    private RequirementRepositoryService requirementRepositoryService;

    @Autowired
    public RequirementServiceImpl(RequirementRepositoryService requirementRepositoryService) {
        this.requirementRepositoryService = requirementRepositoryService;
    }

    @Override
    public void createRequirement(Requirement requirement) {
        Requirement finishedRequirement;

            finishedRequirement = Requirement.builder()
                    .requirementId(UUID.randomUUID().toString())
                    .requirementName(requirement.getRequirementName())
                    .description(requirement.getDescription())
                    .releaseId(requirement.getReleaseId())
                    .effort(requirement.getEffort())
                    .build();
            requirementRepositoryService.createRequirement(finishedRequirement);
            if (finishedRequirement.getReleaseId() != "" && finishedRequirement.getReleaseId() != null) {
                requirementRepositoryService.createRequirementReleaseMapping(finishedRequirement);
            }
    }

    @Override
    public void createRequirementUserMapping(String requirementId, String userId, Long rank) {
        requirementRepositoryService.createRequirementUserMapping(requirementId, userId, rank);
    }

    @Override
    public Requirement getRequirement(String requirementId) {
        Requirement requirement = requirementRepositoryService.getRequirement(requirementId);
        try {
            Requirement releaseRequirement = requirementRepositoryService.getReleaseRequirement(requirementId);
            requirement.setReleaseId(releaseRequirement.getReleaseId());
            requirement.setReleaseName(releaseRequirement.getReleaseName());
        } catch (Exception e) {

        }
        return requirement;
    }

    @Override
    public List<Requirement> getAllRequirements(String userId) {
        List<Requirement> requirementList = requirementRepositoryService.getAllRequirements();

            for (Requirement requirement: requirementList) {
                try {
                    Requirement releaseRequirement = requirementRepositoryService.getReleaseRequirement(requirement.getRequirementId());
                    requirement.setReleaseId(releaseRequirement.getReleaseId());
                    requirement.setReleaseName(releaseRequirement.getReleaseName());
                } catch (Exception e) {

            }
        }

        for(Requirement requirement: requirementList) {
            int value = requirementRepositoryService.getUserValue(userId, requirement.getRequirementId());
            requirement.setValue(value);
        }
        return requirementList;
    }

    @Override
    public void insertUserValue(RequirementValue requirementValue) {
        try {
            requirementRepositoryService.deleteUserValue(requirementValue.getUserId(), requirementValue.getRequirementId());
        } catch (Exception e) {

        }
        requirementRepositoryService.insertUserValue(requirementValue);
    }

    @Override
    public void insertConflict(RequirementConflict requirementConflict) {
        requirementConflict.setConflictId(UUID.randomUUID().toString());
        requirementRepositoryService.insertConflict(requirementConflict);
    }

    @Override
    public void insertDependency(RequirementConflict requirementConflict) {
        requirementConflict.setConflictId(UUID.randomUUID().toString());
        requirementRepositoryService.insertDependency(requirementConflict);
    }

}
