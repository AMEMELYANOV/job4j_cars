package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bodytypes")
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

    public String getBodytypename() {
        return bodytypename;
    }

    public void setBodytypename(String bodytypename) {
        this.bodytypename = bodytypename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BodyType bodyType = (BodyType) o;
        return id == bodyType.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BodyType{"
                + "id=" + id
                + ", type='" + bodytypename + '\''
                + '}';
    }
}
