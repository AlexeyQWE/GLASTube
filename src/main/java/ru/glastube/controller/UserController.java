package ru.glastube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.glastube.entity.User;
import ru.glastube.repository.UserRepository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository crudRep;

    @RequestMapping("/register")
    public User register(@RequestParam("nickname") String nickname, @RequestParam("login") String login, @RequestParam("password") String password) {
        password = new StandardPasswordEncoder().encode(password);
        User user = crudRep.findByLogin(login);

        if (user != null) {
            return null;
        }
        return crudRep.save(new User(nickname, login, password, 1));
    }

    @RequestMapping("/get_users")
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        crudRep.findAll().forEach(users::add);
        return users;
    }

    @RequestMapping("/newLogin")
    public User newLogin(@RequestParam("newLogin") String newLogin, @RequestParam("login") String login) throws SQLException {
        User user = crudRep.findByLogin(newLogin);

        if (user != null) {
            return null;
        }
        user = crudRep.findByLogin(login);

        if (user == null) {
            return null;
        }
        user.setLogin(newLogin);
        return crudRep.save(user);
    }

    @RequestMapping("/newPassword")
    public User newPassword(@RequestParam("newPassword") String newPassword, @RequestParam("Password") String oldPassword,
         @RequestParam("login") String login) throws SQLException {
        User user = crudRep.findByLogin(login);
        System.out.println(oldPassword);

        if (user != null && new StandardPasswordEncoder().matches(oldPassword, user.getPassword())) {
            newPassword = new StandardPasswordEncoder().encode(newPassword);
            user.setPassword(newPassword);
            return crudRep.save(user);
        }
        return null;
    }

    @RequestMapping(value = "/user_profile", method = RequestMethod.GET)
    public ModelAndView userProfile(@RequestParam("login") String login) {
        User user = crudRep.findByLogin(login);
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("login", userDetail.getUsername());
            model.addObject("signin_myprofile", "My profile");
            model.addObject("signout_signup", "Sign out");
            //return "Us";
        } else {
            model.addObject("login", ".O.");
            model.addObject("signin_myprofile", "Sign in");
            model.addObject("signup_signout", "Sign up");
        }
        model.addObject("user", user.getLogin());
        model.setViewName("UserProfile");
        return model;
    }
}
