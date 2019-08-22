package ru.glas***.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.glas***.entity.User;
import ru.glas***.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository crudRep;

    @RequestMapping("/register")
    public User signUp(@RequestParam("nickname") String nickname, @RequestParam("login") String login, @RequestParam("password") String password) {
        password = new StandardPasswordEncoder().encode(password);
        return crudRep.save(new User(nickname, login, password, 1));

    @RequestMapping("/get_users")
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        crudRep.findAll().forEach(users::add);
        return users;
    }

}
