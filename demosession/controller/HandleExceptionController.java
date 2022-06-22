package milliwatt.springbootapp.demosession.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import milliwatt.springbootapp.demosession.exception.UserException;

@ControllerAdvice
public class HandleExceptionController {
    @ExceptionHandler(UserException.class)
    public String handleUserException(UserException ex, Model model){
        model.addAttribute("error", ex);
        return "error";
    }
}
