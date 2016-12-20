package morti.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Release {

    @JsonProperty("releaseId")
    private String releaseId;
    @JsonProperty("releaseName")
    private String releaseName;
    @JsonProperty("projectId")
    private String projectId;
    @JsonProperty("projectName")
    private String projectName;
    @JsonProperty("requirements")
    private List<Requirement> requirements;
    @JsonProperty("totalEffort")
    private int totalEffort;
    @JsonProperty("releaseDate")
    private String releaseDate;

    @Builder
    public Release(@JsonProperty("releaseId") String releaseId,
                   @JsonProperty("releaseName") String releaseName,
                   @JsonProperty("projectId") String projectId,
                   @JsonProperty("projectName") String projectName,
                   @JsonProperty("requirements") List<Requirement> requirements,
                   @JsonProperty("totalEffort") int totalEffort,
                   @JsonProperty("releaseDate") String releaseDate) {
        this.releaseId = releaseId;
        this.releaseName = releaseName;
        this.projectId = projectId;
        this.projectName = projectName;
        this.requirements = requirements;
        this.totalEffort = totalEffort;
        this.releaseDate = releaseDate;
    }
}
