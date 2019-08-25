package ru.glastube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.glastube.entity.Comments;
import ru.glastube.entity.User;
import ru.glastube.repository.UserRepository;

@Controller
public class WebController {

    @Autowired
    private UserRepository crudRep;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView indexStart() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
            model.addObject("signin_myprofile", "My profile");
            model.addObject("signup_signout", "Sign out");
        } else {
            model.addObject("signin_myprofile", "Sign in");
            model.addObject("signup_signout", "Sign up");
        }
        model.setViewName("indexStart");
        return model;
    }

    @RequestMapping(value = "/signin_myprofile", method = RequestMethod.GET)
    public ModelAndView signInMyProfile() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("nickname", userDetail.getUsername());
            System.out.println(userDetail.getUsername());
//            model.addObject("myprofile", "My profile");
//            model.addObject("signout", "Sign out");
            model.setViewName("indexMainPage");
            return model;
        } else {
            model.setViewName("Login");
            return model;
        }
    }

    @RequestMapping(value = "/user_profile", method = RequestMethod.GET)
    public ModelAndView userProfile() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("nickname", userDetail.getUsername());
            System.out.println(userDetail.getUsername());
            model.addObject("signoin_myprofile", "My profile");
            model.addObject("signout_signup", "Sign out");
            //return "Us";
        } else {
           // return "Login";
            model.addObject("signin_myprofile", "Sign in");
            model.addObject("signup_signout", "Sign up");
        }
        model.setViewName("UserProfile");
        return model;
    }

//    @RequestMapping(value = "/my_profile", method = RequestMethod.GET)
//    public String myProfile() {
//        ModelAndView model = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (!(auth instanceof AnonymousAuthenticationToken)) {
//            UserDetails userDetail = (UserDetails) auth.getPrincipal();
//            model.addObject("nickname", userDetail.getUsername());
//            System.out.println(userDetail.getUsername());
//            model.addObject("myprofile", "My profile");
//            model.addObject("signout", "Sign out");
//            return "indexMainPage";
//        } else {
//            return "Login";
//        }
//    }

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
