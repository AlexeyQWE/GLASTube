package project.GLASTube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;
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
}
