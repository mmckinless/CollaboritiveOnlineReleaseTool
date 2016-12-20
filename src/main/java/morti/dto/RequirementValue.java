package morti.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class RequirementValue {

    @JsonProperty("requirementId")
    private String requirementId;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("value")
    private int value;


    @Builder
    public RequirementValue(@JsonProperty("requirementId") String requirementId,
                            @JsonProperty("userId") String userId,
                            @JsonProperty("value") int value) {
        this.requirementId = requirementId;
        this.userId = userId;
        this.value = value;
    }

}
