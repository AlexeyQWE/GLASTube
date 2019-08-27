package ru.glas***.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.glas***.entity.Comments;

@Controller
public class WebController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView indexStart() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("login", userDetail.getUsername());
            model.addObject("signin_myprofile", "My profile");
            model.addObject("signup_signout", "Sign out");
            model.addObject("settings", "Settings");
        } else {
            model.addObject("login", ".O.");
            model.addObject("signin_myprofile", "Sign in");
            model.addObject("signup_signout", "Sign up");
        }
        model.setViewName("indexStart");
        return model;
    }

    @RequestMapping(value = "/signup_signout", method = RequestMethod.GET)
    public ModelAndView signUpSignOut() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("login", userDetail.getUsername());
            System.out.println(userDetail.getUsername());
            model.setViewName("Login");
            return model;
        } else {
            model.setViewName("indexSignUp");
            return model;
        }
    }

    @RequestMapping(value = "/signin_myprofile", method = RequestMethod.GET)
    public ModelAndView signInMyProfile() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("login", userDetail.getUsername());
            System.out.println(userDetail.getUsername());
            model.setViewName("indexMainPage");
            return model;
        } else {
            model.setViewName("Login");
            return model;
        }
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public ModelAndView privatePage() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("login", userDetail.getUsername());
            System.out.println(userDetail.getUsername());
            model.setViewName("settings");
            return model;
        } else {
            model.setViewName("Login");
            return model;
        }
    }

    @RequestMapping("/sign_up")
    public String indexSignUp(Model model) {
        model.addAttribute("name", "Sign Up");

        return "indexSignUp";
    }

//    @RequestMapping("/sign_in")
//    public String indexMainPage(Model model) {
//        model.addAttribute("name", "Home Page");
//        return "indexMainPage";
//    }
}
