package ru.glas***.repository;

import org.springframework.data.repository.CrudRepository;
import ru.glas***.entity.Video;

public interface VideoRepository extends CrudRepository<Video, Integer> {
    Video findByName(String name);
}
