package ru.glas***.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.glas***.entity.User;
import ru.glas***.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository crudRep;


    @RequestMapping("/sign_up/{nickname}/{login}/{password}")
    public User signUp(@PathVariable("nickname") String nickname, @PathVariable("login") String login, @PathVariable("password") String password) {

        return crudRep.save(new User(nickname, login, password));
    }

    @RequestMapping("/get_users")
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        crudRep.findAll().forEach(users::add);
        return users;
    }

}
