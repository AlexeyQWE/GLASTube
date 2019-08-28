package ru.glas***.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.glas***.entity.User;
import ru.glas***.entity.Video;
import ru.glas***.repository.UserRepository;
import ru.glas***.repository.VideoRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    private VideoRepository videoRep;

    @Autowired
    private UserRepository userRep;

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

        model.addObject("videos", videoRep.findAll());
        model.setViewName("indexStart");
        return model;
    }

    @RequestMapping(value = "/signup_signout", method = RequestMethod.GET)
    public ModelAndView signUpSignOut() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            SecurityContextHolder.clearContext();
            model.addObject("login", ".O.");
            model.addObject("signin_myprofile", "Sign in");
            model.addObject("signup_signout", "Sign up");
            model.setViewName("indexStart");
            return model;
        } else {
            model.setViewName("SignUpPage");
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
            String username = userDetail.getUsername();
            User user = userRep.findByLogin(username);
            List<Video> videos = videoRep.findAllByAuthor(user);
//            videos.forEach(video -> {
//                System.out.println(video.getName());
//            });
            model.addObject("videos", videos);
            model.setViewName("indexMainPage");
            return model;
        } else {
            model.setViewName("Login");
            return model;
        }
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public ModelAndView settings() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("login", userDetail.getUsername());
            System.out.println(userDetail.getUsername());
            model.setViewName("SettingsPage");
            return model;
        } else {
            model.setViewName("Login");
            return model;
        }
    }

    @RequestMapping("/sign_up")
    public String signUp(Model model) {
        return "SignUpPage";
    }
}
