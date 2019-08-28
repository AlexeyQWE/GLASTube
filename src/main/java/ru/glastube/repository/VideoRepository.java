package ru.glastube.repository;

import org.springframework.data.repository.CrudRepository;
import ru.glastube.entity.User;
import ru.glastube.entity.Video;

import java.util.List;

public interface VideoRepository extends CrudRepository<Video, Integer> {
    Video findByName(String name);
    List<Video> findAllByAuthor(User author);
}
