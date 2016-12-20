package morti.controller;

import morti.dto.Release;
import morti.service.ReleaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/release")
public class ReleaseController {

    private Logger LOGGER = Logger.getLogger(ReleaseController.class);

    private ReleaseService releaseService;

    @Autowired
    public ReleaseController(ReleaseService releaseService) {
        this.releaseService = releaseService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createRelease(@RequestBody Release release) {
        releaseService.createRelease(release);
        return new ResponseEntity<String>("Release created", HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Release> getRelease(@RequestParam("releaseId") String releaseId) {
        return new ResponseEntity<Release>(releaseService.getReleaseByReleaseId(releaseId), HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Release>> getAllRelease() {
        return new ResponseEntity<List<Release>>(releaseService.getAllRelease(),HttpStatus.OK);
    }

    @RequestMapping(value = "/allByProject", method = RequestMethod.GET)
    public ResponseEntity<List<Release>> getAllReleaseByProject(@RequestBody String projectId) {
        return new ResponseEntity<List<Release>>(releaseService.getAllReleaseByProjectId(projectId),HttpStatus.OK);
    }

}
