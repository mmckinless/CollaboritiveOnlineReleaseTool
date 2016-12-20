package morti.service;


import morti.dto.Project;

import java.util.List;

public interface ProjectService {

    void createProject(Project project);

    Project getProject(String projectId);

    List<Project> getAllProjects();

}
