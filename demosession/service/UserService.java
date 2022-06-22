package milliwatt.springbootapp.demosession.service;

import java.util.Optional;

import milliwatt.springbootapp.demosession.model.User;

public interface UserService {
    public User login(String email, String password);
    public boolean logout(String email);

    public User addUser(String fullname, String email, String password);
    public User addUserThenAutoActivate(String fullname, String email, String password);
    public Boolean activateUser(String activationCode);

    public Boolean updatePassword(String email, String password);
    public Boolean updateEmail(String email, String password);

    public Optional<User> findByEmail(String email);
    public User findById(String id);
}
