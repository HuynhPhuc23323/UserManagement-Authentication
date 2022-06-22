package milliwatt.springbootapp.demosession.repository;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import milliwatt.springbootapp.demosession.model.State;
import milliwatt.springbootapp.demosession.model.User;

@Repository
public class UserRepo {
    private ConcurrentHashMap<String,User> users = new ConcurrentHashMap<>();

    public User addUser(String fullName, String email, String hashedPassword){
        return addUser(fullName, email, hashedPassword, State.PENDING);
    } 

    public User addUser(String fullName, String email, String hashedPassword, State state){
        String id = UUID.randomUUID().toString();
        User user = User.builder()
        .id(id)
        .fullname(fullName)
        .email(email)
        .hashedPassword(hashedPassword)
        .state(state)
        .build();
        users.put(id,user);
        return user;
    }

    public boolean isEmailExist(String email){
        return users.values().stream()
        .filter(user->user.getEmail().equalsIgnoreCase(email)).count() > 0; // count nếu trùng thì nó sẽ >1 tra ve T
    }

    public Optional<User> findByEmail(String email){
        return users.values().stream()
        .filter(user->user.getEmail().equalsIgnoreCase(email)).findFirst();
    }


}
