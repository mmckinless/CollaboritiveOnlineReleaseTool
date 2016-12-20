package morti.service;

import morti.dto.Release;

import java.util.List;

/**
 * Created by mark on 05/11/2016.
 */
public interface ReleaseService {

    void createRelease(Release release);

    Release getReleaseByReleaseId(String releaseId);

    List<Release> getAllRelease();

    List<Release> getAllReleaseByProjectId(String projectId);

}
