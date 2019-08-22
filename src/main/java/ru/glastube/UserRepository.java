package ru.glastube;

import org.springframework.data.repository.CrudRepository;

interface UserRepository extends CrudRepository <User, Integer> {
    User findByLogin(String login);
}
