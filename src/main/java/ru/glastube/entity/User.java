package ru.glastube.entity;

import lombok.*;;
import javax.persistence.*;

@NoArgsConstructor
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Getter @Setter private Integer id;
    @Getter @Setter private String login;
    @Getter @Setter private String password;
    @Getter @Setter private Integer enabled;

    public User(String login, String password, Integer enabled) {
        this.login = login;
        this.password = password;
        this.enabled = enabled;
    }
}