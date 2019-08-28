package ru.glas***.repository;

import org.springframework.data.repository.CrudRepository;
import ru.glas***.entity.User;
import ru.glas***.entity.Video;

import java.util.List;

public interface VideoRepository extends CrudRepository<Video, Integer> {
    Video findByName(String name);
    List<Video> findAllByAuthor(User author);
}
