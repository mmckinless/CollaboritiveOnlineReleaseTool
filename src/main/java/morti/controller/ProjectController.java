package morti.controller;

import morti.dto.Project;
import morti.dto.User;
import morti.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createProject(@RequestBody Project project) {
        try {
            projectService.createProject(project);
            return new ResponseEntity<String>("Created Project", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

        }


    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Project> getProject(@RequestBody Project project) {
        try {
            return new ResponseEntity<Project>(projectService.getProject(project.getProjectId()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Project>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseEntity<List<Project>> getAllProjects() {
        try {
            return new ResponseEntity<List<Project>>(projectService.getAllProjects(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Project>>(HttpStatus.BAD_REQUEST);
        }
    }



}
