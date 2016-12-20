package morti.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class RequirementConflict {

    @JsonProperty("requirementId1")
    private String requirementId1;
    @JsonProperty("requirementId2")
    private String requirementId2;
    @JsonProperty("conflictId")
    private String conflictId;

    @Builder
    public RequirementConflict(
            @JsonProperty("requirementId1") String requirementId1,
            @JsonProperty("requirementId2") String requirementId2,
            @JsonProperty("conflictId") String conflictId) {
        this.requirementId1 = requirementId1;
        this.requirementId2 = requirementId2;
        this.conflictId = conflictId;
    }

}
