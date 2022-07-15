package ru.job4j.cars.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "brands")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String brandname;

    public static Brand of(String name) {
        Brand brand = new Brand();
        brand.brandname = name;
        return brand;
    }

}