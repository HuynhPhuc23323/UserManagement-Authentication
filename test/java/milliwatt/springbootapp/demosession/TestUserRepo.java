package milliwatt.springbootapp.demosession;

import org.junit.jupiter.api.Test;

import milliwatt.springbootapp.demosession.model.State;
import milliwatt.springbootapp.demosession.model.User;
import milliwatt.springbootapp.demosession.repository.UserRepo;

import static org.assertj.core.api.Assertions.*;

//@SpringBootTest // nhược điểm là chậm và nặng
public class TestUserRepo {
    
    @Test
    public void testAddUser(){
        UserRepo userRepo = new UserRepo();
        User user =userRepo.addUser("JohnLevy", "levy@gmail.com", "0X-1321am21321", State.PENDING);
        assertThat(user).isNotNull();
        System.out.println(user.getId());
        assertThat(user.getId()).isNotBlank();
    }

    @Test
    public void testAddUserWithPendingState(){
        UserRepo userRepo = new UserRepo();
        User user =userRepo.addUser("JohnLevy", "levy@gmail.com", "0X-1321am21321");
        assertThat(user).isNotNull();
        System.out.println(user.getId());
        assertThat(user.getId()).isNotBlank();
        assertThat(user.getState()).isEqualTo(State.PENDING);
    }

}
