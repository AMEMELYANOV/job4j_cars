package ru.job4j.cars.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

    @NotBlank(message = "Поле не должно быть пустым")
    @Size(min = 5, message = "Имя пользователя должно быть не менее 5 символов")
    private String username;

    @Column(unique = true)
    @NotBlank(message = "Поле не должно быть пустым")
    private String email;

    @NotBlank(message = "Поле не должно быть пустым")
    @Size(min = 4, message = "Пароль пользователя должен быть не менее 4 символов")
    private String password;

    @NotBlank(message = "Поле не должно быть пустым")
    @Pattern(regexp = "\\+\\d{11}",
            message = "Должно быть в формате \"+\" и 11 значный номер")
    private String phonenumber;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Advert> adverts = new ArrayList<>();

    private boolean active;

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
