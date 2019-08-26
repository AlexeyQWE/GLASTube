package ru.glastube.repository;

import org.springframework.data.repository.CrudRepository;
import ru.glastube.entity.User;

public interface UserRepository extends CrudRepository <User, Integer> {
    User findByLogin(String login);
    User findByNickname(String nickname);
}
