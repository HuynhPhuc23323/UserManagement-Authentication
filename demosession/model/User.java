package milliwatt.springbootapp.demosession.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class User {
    
    private String id;
    private String fullname;
    private String email;
    private String hashedPassword;
    private State state;

}
