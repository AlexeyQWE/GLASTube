package ru.glastube.entity;

import lombok.*;;
import javax.persistence.*;

@NoArgsConstructor
@Entity
public class User {

    @Id@GeneratedValue(generator = "USER_G", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "USER_G", sequenceName = "USER_S", allocationSize = 1)
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