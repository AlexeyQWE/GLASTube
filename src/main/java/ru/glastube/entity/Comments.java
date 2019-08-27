package ru.glas***.entity;

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
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Setter @Getter
    private Integer id;
    @Getter @Setter
    private Integer id_user;
    @Setter @Getter
    private String date;
    @Getter @Setter
    private String text;
    public Comments(Integer id_user, String date, String text){
        setId_user(id_user);
        setDate(date);
        setText(text);
    }
}
