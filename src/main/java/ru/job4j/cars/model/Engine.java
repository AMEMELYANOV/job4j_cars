package ru.job4j.cars.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "engines")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double capacity;
    private int hp;
    private String fuel;

    public static Engine of(double capacity, int hp, String fuel) {
        Engine engine = new Engine();
        engine.capacity = capacity;
        engine.hp = hp;
        engine.fuel = fuel;
        return engine;
    }

}
