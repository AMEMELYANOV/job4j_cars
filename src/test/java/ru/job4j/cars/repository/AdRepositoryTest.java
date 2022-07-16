package ru.job4j.cars.repository;

import org.assertj.core.api.Assertions;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import ru.job4j.cars.model.*;

import java.util.List;

class AdRepositoryTest {

    private Advert advertOne;
    private Advert advertTwo;

    private User user;

    private AdRepository adRepository;
    private UserRepository userRepository;

    @Bean(destroyMethod = "close")
    SessionFactory sf() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @BeforeEach
    void setUp() {
        SessionFactory sf = sf();
        adRepository = new AdRepository(sf);
        userRepository = new UserRepository(sf);
        advertOne = Advert.of("title1", "description1", true,
                Car.of("model1",
                        Brand.of("brand1"),
                        BodyType.of("bodyType1"),
                        Engine.of(1.0, 100, "fuel1"),
                        Transmission.of("transmission1"),
                        1, 1),
                1, "city1");
        advertTwo = Advert.of("title2", "description2", true,
                Car.of("model2",
                        Brand.of("brand2"),
                        BodyType.of("bodyType2"),
                        Engine.of(2.0, 200, "fuel2"),
                        Transmission.of("transmission2"),
                        2, 2),
                2, "city2");
        user = User.of("username", "email", "password", "+79059991111");
    }

    @Test
    public void whenSaveAdvert() {
        userRepository.add(user);
        advertOne.setUser(user);
        adRepository.save(advertOne);
        List<Advert> adverts = adRepository.findAllAdverts();

        Assertions.assertThat(adverts).hasSize(1);
    }

    @Test
    public void whenGetAllAdverts() {
        userRepository.add(user);
        advertOne.setUser(user);
        adRepository.save(advertOne);
        advertTwo.setUser(user);
        adRepository.save(advertTwo);
        List<Advert> adverts = adRepository.findAllAdverts();

        Assertions.assertThat(adverts).hasSize(2);
    }

    @Test
    public void whenFindAdvertById() {
        userRepository.add(user);
        advertOne.setUser(user);
        Advert advert = adRepository.save(advertOne);
        Advert rsl = adRepository.findAdvertById(advert.getId());

        Assertions.assertThat(rsl).isEqualTo(advert);
    }

    @Test
    public void whenUpdateAdvert() {
        userRepository.add(user);
        advertOne.setUser(user);
        Advert advert = adRepository.save(advertOne);
        advert.setTitle("updatedTitle1");
        Advert rsl = adRepository.update(advert);

        Assertions.assertThat(rsl).isEqualTo(advert);
    }

    @Test
    public void whenFindAdvertByUserId() {
        User storedUser = userRepository.add(user);
        advertOne.setUser(user);
        adRepository.save(advertOne);
        advertTwo.setUser(user);
        adRepository.save(advertTwo);
        List<Advert> adverts = adRepository.findAdvertsByUserId(storedUser.getId());

        Assertions.assertThat(adverts).hasSize(2);
    }

    @Test
    public void whenDeleteAdvertById() {
        userRepository.add(user);
        advertOne.setUser(user);
        Advert advert = adRepository.save(advertOne);
        adRepository.deleteAdvertById(advert.getId());
        List<Advert> adverts = adRepository.findAllAdverts();

        Assertions.assertThat(adverts).hasSize(0);
    }

    @Test
    public void whenFilterAdverts() {
        userRepository.add(user);
        advertOne.setUser(user);
        Advert advert = adRepository.save(advertOne);
        advertTwo.setUser(user);
        List<Advert> adverts = adRepository.filterAdverts(advert
                .getCar()
                .getBodyType()
                .getBodytypename(), "", "", "");

        Assertions.assertThat(adverts.get(0).getTitle()).isEqualTo("title1");
        Assertions.assertThat(adverts).hasSize(1);
    }
}