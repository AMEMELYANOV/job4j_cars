package ru.job4j.cars.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "bodytypes")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class BodyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String bodytypename;

    public static BodyType of(String bodytypename) {
        BodyType bodyType = new BodyType();
        bodyType.bodytypename = bodytypename;
        return bodyType;
    }
}
