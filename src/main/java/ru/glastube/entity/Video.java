package ru.glastube.entity;

import lombok.*;
import javax.persistence.*;

@NoArgsConstructor
@Entity
public class Video {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Getter @Setter private Integer id;
    @Getter @Setter private String name;
    @Getter @Setter private String author;
    @Getter @Setter private String path;

    public Video(String name, String author, String path) {
        this.name = name;
        this.author = author;
        this.path = path;
    }
}