package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "adverts")
public class Advert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    private boolean active;
    private int price;
    private String filename;
    private String city;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", unique = true)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date(System.currentTimeMillis());

    public static Advert of(String title, String description,
                            boolean active, Car car, int price, String city) {
        Advert advert = new Advert();
        advert.title = title;
        advert.description = description;
        advert.active = active;
        advert.car = car;
        advert.created = new Date(System.currentTimeMillis());
        advert.price = price;
        advert.city = city;
        return advert;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Advert advert = (Advert) o;
        return id == advert.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Advert{"
                + "id=" + id
                + ", title='" + title + '\''
                + ", description='" + description + '\''
                + ", active=" + active
                + ", price=" + price
                + ", filename='" + filename + '\''
                + ", city='" + city + '\''
                + ", car=" + car
                + ", user=" + user
                + ", created=" + created
                + '}';
    }
}
