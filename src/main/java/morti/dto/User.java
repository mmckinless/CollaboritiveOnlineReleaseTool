package morti.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import morti.data.Role;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {

    @JsonProperty("id")
    private long id;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("email")
    private String email;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("password")
    private String password;
    @JsonProperty("role")
    private List<Role> role;

    @Builder
    public User(@JsonProperty("id") long id,
                @JsonProperty("userId") String userId,
                @JsonProperty("email") String email,
                @JsonProperty("firstName") String firstName,
                @JsonProperty("lastName") String lastName,
                @JsonProperty("password") String password,
                @JsonProperty("role") List<Role> role) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }


}
