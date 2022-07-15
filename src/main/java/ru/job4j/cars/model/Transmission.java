package ru.job4j.cars.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "transmissions")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Transmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String transmissionname;

    public static Transmission of(String transmissionname) {
        Transmission transmission = new Transmission();
        transmission.transmissionname = transmissionname;
        return transmission;
    }
}
