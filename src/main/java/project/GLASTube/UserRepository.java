package project.GLASTube;

import org.springframework.data.repository.CrudRepository;

interface UserRepository extends CrudRepository <User, Integer> {
    User findByLogin(String login);
}
