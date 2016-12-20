package morti.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Requirement {

    @JsonProperty("requirementId")
    private String requirementId;
    @JsonProperty("requirementName")
    private String requirementName;
    @JsonProperty("releaseId")
    private String releaseId;
    @JsonProperty("releaseName")
    private String releaseName;
    @JsonProperty("description")
    private String description;
    @JsonProperty("effort")
    private int effort;
    @JsonProperty("value")
    private int value;


    @Builder
    public Requirement(@JsonProperty("requirementId") String requirementId,
                       @JsonProperty("requirementName") String requirementName,
                       @JsonProperty("releaseId") String releaseId,
                       @JsonProperty("releaseName") String releaseName,
                       @JsonProperty("description") String description,
                       @JsonProperty("effort") int effort,
                       @JsonProperty("value") int value) {
        this.requirementId = requirementId;
        this.requirementName = requirementName;
        this.releaseId = releaseId;
        this.releaseName = releaseName;
        this.description = description;
        this.effort = effort;
        this.value = value;
    }

}
