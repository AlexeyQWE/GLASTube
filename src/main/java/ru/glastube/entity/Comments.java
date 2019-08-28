package ru.glastube.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Entity
public class Comments {

    @Id@GeneratedValue(generator = "COM_G", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "COM_G", sequenceName = "COM_S", allocationSize = 1)
    @Setter @Getter private Integer id;
    @Getter @Setter private String user;
    @Setter @Getter private String date;
    @Getter @Setter private String text;
    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="video") @JsonIgnore
    @Getter @Setter private Video video;

    public Comments(String user, String date, String text, Video video){
        setUser(user);
        setDate(date);
        setText(text);
        setVideo(video);
    }
}
