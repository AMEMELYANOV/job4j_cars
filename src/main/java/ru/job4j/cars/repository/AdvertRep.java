package ru.job4j.cars.repository;

import ru.job4j.cars.model.Advert;

import java.util.List;

public interface AdvertRep {

    List<Advert> findAllAdverts();

    Advert save(Advert advert);

    Advert findAdvertById(Integer advertId);

    List<Advert> findAdvertsByUserId(int id);

    boolean deleteAdvertById(Integer advertId);

    Advert update(Advert advert);
}
