package ru.job4j.cars.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    private String phonenumber;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
    orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Advert> adverts = new ArrayList<>();

    public static User of(String username, String email, String password, String phonenumber) {
        User user = new User();
        user.username = username;
        user.email = email;
        user.password = password;
        user.phonenumber = phonenumber;
        return user;
    }

    public void addAdvert(Advert advert) {
        this.adverts.add(advert);
        advert.setUser(this);
    }
}
