package ru.glas***;

import org.springframework.data.repository.CrudRepository;

interface UserRepository extends CrudRepository <User, Integer> {
    User findByLogin(String login);
}
