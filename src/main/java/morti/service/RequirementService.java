package morti.service;


import morti.dto.Requirement;
import morti.dto.RequirementConflict;
import morti.dto.RequirementValue;

import java.util.List;

public interface RequirementService {

    void createRequirement(Requirement requirement);

    void createRequirementUserMapping(String requirementId, String userId, Long rank);

    Requirement getRequirement(String requirementId);

    List<Requirement> getAllRequirements(String userId);

    void insertUserValue(RequirementValue requirementValue);

    void insertConflict(RequirementConflict requirementConflict);

    void insertDependency(RequirementConflict requirementConflict);

}
