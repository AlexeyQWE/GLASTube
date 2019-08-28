package ru.glas***.repository;

import org.springframework.data.repository.CrudRepository;
import ru.glas***.entity.Comments;

import java.util.List;

public interface CommentRepository extends CrudRepository <Comments, Integer> {
    List<Comments> findAllByVideo(Integer id);
}
