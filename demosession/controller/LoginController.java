package milliwatt.springbootapp.demosession.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import milliwatt.springbootapp.demosession.dto.UserDTO;
import milliwatt.springbootapp.demosession.exception.UserException;
import milliwatt.springbootapp.demosession.model.User;
import milliwatt.springbootapp.demosession.request.LoginRequest;
import milliwatt.springbootapp.demosession.service.UserService;

@Controller
@RequestMapping("/")
public class LoginController {
    
    @Autowired
    UserService userService;

    @GetMapping
    public String showHomePage(Model model, HttpSession session){
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        if(userDTO != null){
            model.addAttribute("user", userDTO);
        }
        return "index";
    }

    @GetMapping("login")
    public String showLogin(Model model){
        model.addAttribute("loginrequest", new LoginRequest("",""));
        return "login";
    }

    @PostMapping("login")
    public String handleLogin(@Valid @ModelAttribute("loginrequest") LoginRequest loginRequest, 
    BindingResult result,
    HttpSession session){
        if(result.hasErrors()){
            return "login";
        }
        User user;
        try{
            user = userService.login(loginRequest.email(), loginRequest.password());
            //De luu thong tin user khi sang cac trang khac k bi dang nhap lai
            session.setAttribute("user", new UserDTO(user.getId(),user.getFullname(),user.getEmail()));
            return "redirect:/";
        } catch (UserException ex){
            switch (ex.getMessage()){
                case "User is not found":{
                    result.addError(new FieldError("loginrequest", "email", "Email does not exist"));
                    break;
                }
                case "User is not activated":{
                    result.addError(new FieldError("loginrequest", "email", "User is not activated"));
                    break;
                }
                case "Password is incorrect":{
                    result.addError(new FieldError("loginrequest", "password", "Password is incorrect"));
                    break;
                }
            }
            return "login";
        }

        
    }

    @GetMapping("register")
    public String showRegisterForm(){
        return "register";
    }

    @GetMapping("foo")
    public String foo(){
        throw new UserException("Some error");
    }

}
