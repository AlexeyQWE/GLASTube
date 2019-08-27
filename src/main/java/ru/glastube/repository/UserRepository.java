package ru.glas***.repository;

import org.springframework.data.repository.CrudRepository;
import ru.glas***.entity.User;

public interface UserRepository extends CrudRepository <User, Integer> {
    User findByLogin(String login);
}
