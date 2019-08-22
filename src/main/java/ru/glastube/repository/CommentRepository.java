package ru.glastube.repository;

import org.springframework.data.repository.CrudRepository;
import ru.glastube.entity.Comments;

public interface CommentRepository extends CrudRepository <Comments, Integer> {

}
