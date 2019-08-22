package ru.glastube.entity;

import lombok.*;;
import javax.persistence.*;

@NoArgsConstructor
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Getter @Setter private Integer id;
    @Getter @Setter private String nickname;
    @Getter @Setter private String login;
    @Getter @Setter private String password;

    public User(String nickname, String login, String password) {
        //this.id = id;
        this.nickname = nickname;
        this.login = login;
        this.password = password;
    }
}