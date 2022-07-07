package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(List<Advert> adverts) {
        this.adverts = adverts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", username='" + username + '\''
                + ", email='" + email + '\''
                + ", password='" + password + '\''
                + ", phonenumber='" + phonenumber + '\''
                + ", adverts=" + adverts
                + '}';
    }
}
