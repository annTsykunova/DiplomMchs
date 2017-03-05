package by.bsu.modulestatic.controller;
import by.bsu.modulestatic.controller.util.ModelAttributeNames;
import by.bsu.modulestatic.controller.util.ModelViewNames;
import by.bsu.modulestatic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@SessionAttributes(ModelAttributeNames.USER_NAME)
public class AuthorizationController {

    @Autowired
    private UserService userService;


    private static final String WRONG_CREDENTIALS = "Invalid username or password!";
    private static final String SUCCESS_LOGOUT = "You've been logged out successfully";

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getPage(@RequestParam(value = ModelAttributeNames.ERROR, required = false) String error, @RequestParam(value = ModelAttributeNames.LOGOUT, required = false) String logout) {
        ModelAndView modelAndView = new ModelAndView();
        if (error != null) {
            modelAndView.addObject(ModelAttributeNames.ERROR, WRONG_CREDENTIALS);
        }
        if (logout != null) {
            modelAndView.addObject(ModelAttributeNames.MESSAGE, SUCCESS_LOGOUT);
        }
        modelAndView.setViewName(ModelViewNames.LOGIN);
        return modelAndView;
    }

}
