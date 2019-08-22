package ru.glas***.repository;

import org.springframework.data.repository.CrudRepository;
import ru.glas***.entity.Comments;

public interface CommentRepository extends CrudRepository <Comments, Integer> {

}
