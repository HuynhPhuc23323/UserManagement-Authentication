package milliwatt.springbootapp.demosession.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record LoginRequest(
    @NotBlank(message = "Email cannot blank")
    @Email(message = "Invalid email")
    String email,

    @Size(min = 5, max = 20, message = "Password length must be between 5 - 20 characters")
    String password
) {
    
}
