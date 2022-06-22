package milliwatt.springbootapp.demosession.hash;

import java.security.MessageDigest;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class SHAHash implements Hashing{

    private String salt = "01X-343n42mnl3905u";

    @Override
    public String hashPassword(String password) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb= new StringBuilder();
            for(int i = 0; i<bytes.length; i++){
                sb.append(Integer.toString((bytes[i] & 0xff)+ 0x100,16)
                    .substring(1));
            }
            generatedPassword = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    @Override
    public boolean validatePassword(String originalPassword, String storedPassword) {
        String hashedPassword = hashPassword(originalPassword);
        return storedPassword.equals(hashedPassword);
    }
    
}
