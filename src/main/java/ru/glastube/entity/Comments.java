package ru.glas***.entity;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@NoArgsConstructor
@Entity
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter @Getter
    private Integer id;
    @Getter @Setter
    private Integer id_user;
    @Setter @Getter
    private Long date;
    @Getter @Setter
    private String text;
    public Comments(Integer id_user, Long date, String text){
        setId_user(id_user);
        setDate(date);
        setText(text);
    }
}
