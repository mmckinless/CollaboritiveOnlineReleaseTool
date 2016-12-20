package morti.service.impl;

import morti.dto.Project;
import morti.service.ProjectRepositoryService;
import morti.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepositoryService projectRepositoryService;

    @Autowired
    public ProjectServiceImpl(ProjectRepositoryService projectRepositoryService) {
        this.projectRepositoryService = projectRepositoryService;
    }

    @Override
    public void createProject(Project project) {
        project.setProjectId(UUID.randomUUID().toString());
        projectRepositoryService.createProject(project);
    }

    @Override
    public Project getProject(String projectId) {
        return projectRepositoryService.getProject(projectId);
    }

    @Override
    public List<Project> getAllProjects() {
        List<Project> projectList = projectRepositoryService.getAllProjects();
        for(Project project: projectList) {
            int num = projectRepositoryService.getNumberOfReleasesPerProject(project.getProjectId());
            project.setNumberOfReleases(num);
        }
        return projectList;
    }
}
