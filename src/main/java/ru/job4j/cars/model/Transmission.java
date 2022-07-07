package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "transmissions")
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

    public String getTransmissionname() {
        return transmissionname;
    }

    public void setTransmissionname(String transmissionname) {
        this.transmissionname = transmissionname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transmission that = (Transmission) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Transmission{"
                + "id=" + id
                + ", transmissionname='" + transmissionname + '\''
                + '}';
    }
}
