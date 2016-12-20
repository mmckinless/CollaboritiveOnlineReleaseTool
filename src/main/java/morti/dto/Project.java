package morti.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Project {

    @JsonProperty("id")
    private long id;
    @JsonProperty("projectId")
    private String projectId;
    @JsonProperty("projectName")
    private String projectName;
    @JsonProperty("numberOfReleases")
    private int numberOfReleases;

    @Builder
    public Project(@JsonProperty("id") long id,
                   @JsonProperty("projectId") String projectId,
                   @JsonProperty("projectName") String projectName,
                   @JsonProperty("numberOfReleases") int numberOfReleases)
    {
        this.id = id;
        this.projectId = projectId;
        this.projectName = projectName;
        this.numberOfReleases = numberOfReleases;
    }


}
