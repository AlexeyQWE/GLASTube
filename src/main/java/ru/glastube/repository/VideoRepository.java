package ru.glastube.repository;

import org.springframework.data.repository.CrudRepository;
import ru.glastube.entity.Video;

public interface VideoRepository extends CrudRepository<Video, Integer> {
    Video findByName(String name);
}
