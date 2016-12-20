package morti.controller;

import morti.dto.Requirement;
import morti.dto.RequirementConflict;
import morti.dto.RequirementValue;
import morti.service.RequirementService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requirement")
public class RequirementController {

    private RequirementService requirementService;

    private Logger LOGGER = Logger.getLogger(RequirementController.class);

    @Autowired
    public RequirementController(RequirementService requirementService) {
        this.requirementService = requirementService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createRequirement(@RequestBody Requirement requirement) {
        try {
            requirementService.createRequirement(requirement);
            return new ResponseEntity<String>("Created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/rank", method = RequestMethod.POST)
    public ResponseEntity<String> createRequirementUserMapping(@RequestParam("requirementId") String requirementId,
                                                  @RequestParam("userId") String userId,
                                                  @RequestParam("rank") Long rank) {
        requirementService.createRequirementUserMapping(requirementId, userId, rank);
        return new ResponseEntity<String>("Created", HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Requirement> getRequirement(@RequestParam("requirementId") String requirementId) {
        return ResponseEntity.ok(requirementService.getRequirement(requirementId));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Requirement>> getAllRequirements(@RequestParam("userId") String userId) {
        return ResponseEntity.ok(requirementService.getAllRequirements(userId));
    }

    @RequestMapping(value = "/value", method = RequestMethod.POST)
    public ResponseEntity<String> insertUserValue(@RequestBody RequirementValue requirementValue) {
        requirementService.insertUserValue(requirementValue);
        return ResponseEntity.ok("Value added");
    }


    @RequestMapping(value = "/conflict", method = RequestMethod.PUT)
    public ResponseEntity<String> insertConflict(@RequestBody RequirementConflict requirementConflict) {
        try {
            requirementService.insertConflict(requirementConflict);
            return ResponseEntity.ok("Conflict Added");
        } catch (Exception e) {
            return new ResponseEntity("Assign Values first", HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/dependency", method = RequestMethod.PUT)
    public ResponseEntity<String> insertDependency(@RequestBody RequirementConflict requirementConflict) {
        try {
            requirementService.insertDependency(requirementConflict);
            return ResponseEntity.ok("Conflict Added");
        } catch (Exception e) {
            return new ResponseEntity("Assign Values first", HttpStatus.BAD_REQUEST);
        }
    }

}
