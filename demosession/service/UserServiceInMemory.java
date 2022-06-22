package milliwatt.springbootapp.demosession.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import milliwatt.springbootapp.demosession.exception.UserException;
import milliwatt.springbootapp.demosession.hash.Hashing;
import milliwatt.springbootapp.demosession.model.State;
import milliwatt.springbootapp.demosession.model.User;
import milliwatt.springbootapp.demosession.repository.UserRepo;

@Service
@AllArgsConstructor
public class UserServiceInMemory implements UserService {
    
    private UserRepo userRepo;
    private Hashing hashing;

    @Override
    public User login(String email, String password){
        //Neu User k ton tai thi bao loi
        Optional<User> optiUser = userRepo.findByEmail(email);
        if(!optiUser.isPresent()){
            throw new UserException("User is not found");
        }

        User user = optiUser.get();
        //User muon login phai co trang thai active
        if(user.getState()!= State.ACTIVE){
            throw new UserException("User is not activated");
        }

        //kiem tra phai PW 
        if (hashing.validatePassword(password, optiUser.get().getHashedPassword())){
            return user;
        } else{
            throw new UserException("Password is incorrect");
        }
    }

    @Override
    public boolean logout(String email) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public User addUser(String fullname, String email, String password) {
        return userRepo.addUser(fullname, email, hashing.hashPassword(password));
    }

    @Override
    public User addUserThenAutoActivate(String fullname, String email, String password) {
        return userRepo.addUser(fullname, email, hashing.hashPassword(password),State.ACTIVE);
    }

    @Override
    public Boolean activateUser(String activationCode) {
        return null;
    }


    @Override
    public Boolean updatePassword(String email, String password) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean updateEmail(String email, String password) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }
    

}
