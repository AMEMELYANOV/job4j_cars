package ru.job4j.cars.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "cars")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String carModel;
    private int year;
    private int mileage;
    private String color;
    private String drive;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bodytype_id")
    private BodyType bodyType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transmission_id")
    private Transmission transmission;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "engine_id")
    private Engine engine;

    public static Car of(String model, Brand brand, BodyType bodyType, Engine engine,
                         Transmission transmission, int year, int mileage) {
        Car car = new Car();
        car.carModel = model;
        car.brand = brand;
        car.bodyType = bodyType;
        car.engine = engine;
        car.transmission = transmission;
        car.year = year;
        car.mileage = mileage;
        return car;
    }
}
