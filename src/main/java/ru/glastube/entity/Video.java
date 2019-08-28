package ru.glastube.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

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
    @OneToMany(mappedBy="video", fetch=FetchType.EAGER)
    @Getter @Setter private List<Comments> comments;

    public Video(String name, User author, String path) {
        this.name = name;
        this.author = author;
        this.path = path;
    }
}