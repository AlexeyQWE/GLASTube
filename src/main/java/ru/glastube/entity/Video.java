package ru.glastube.entity;

import lombok.*;
import javax.persistence.*;

@NoArgsConstructor
@Entity
public class Video {

    @Id@GeneratedValue(generator = "VIDEO_G", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "VIDEO_G", sequenceName = "VIDEO_S", allocationSize = 1)
    @Getter @Setter private Integer id;
    @Getter @Setter private String name;
    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="author")
    @Getter @Setter private User author;
    @Getter @Setter private String path;

    public Video(String name, User author, String path) {
        this.name = name;
        this.author = author;
        this.path = path;
    }
}