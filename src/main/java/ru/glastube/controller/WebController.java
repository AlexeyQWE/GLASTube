package ru.glastube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.glastube.entity.Comments;
import ru.glastube.entity.User;
import ru.glastube.repository.UserRepository;

@Controller
public class WebController {

    @Autowired
    private UserRepository crudRep;

    @RequestMapping("/")
    public String indexStart(Model model) {
        model.addAttribute("name", "Welcome, You!");

        return "indexStart";
    }
    @RequestMapping("/sign_up")
    public String indexSignUp(Model model) {
        model.addAttribute("name", "Sign Up");

        return "indexSignUp";
    }
    @RequestMapping("/sign_in")
    public String indexMainPage(Model model) {
        model.addAttribute("name", "Home Page");
        return "indexMainPage";
    }
    @RequestMapping("/test")
    public String test(Model model)
    {
        model.addAttribute("comment",new Comments());
        return "comments";
    }
    @RequestMapping("/user_profile/{login}")
    public String UserProfile(@PathVariable("login")String login, Model model)
    {
        User user = crudRep.findByLogin(login);

        model.addAttribute("user", user);
        return "UserProfile";
    }
}
