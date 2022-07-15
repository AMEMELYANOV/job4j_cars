package ru.job4j.cars.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "adverts")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
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
}
