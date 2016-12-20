package morti.service;


import morti.dto.Requirement;
import morti.dto.RequirementConflict;
import morti.dto.RequirementValue;

import java.util.List;

public interface RequirementRepositoryService {

    void createRequirement(Requirement requirement);

    void createRequirementUserMapping(String requirementId, String userId, Long rank);

    void createRequirementReleaseMapping(Requirement requirement);

    Requirement getRequirement(String requirementId);

    Requirement getReleaseRequirement(String requirementId);

    List<Requirement> getAllRequirements();

    List<Requirement> getAllReleaseRequirements();

    void insertUserValue(RequirementValue requirementValue);

    void insertConflict(RequirementConflict requirementConflict);

    void insertDependency(RequirementConflict requirementConflict);

    int getUserValue(String userId, String requirementId);

    void deleteUserValue(String userId, String requirementId);

}
