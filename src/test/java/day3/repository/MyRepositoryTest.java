package day3.repository;

import day1.PersonAgeComparator;
import day3.entities.Division;
import day3.entities.Person;
import junit.framework.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.vsu.lab.entities.enums.Gender;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Predicate;

class MyRepositoryTest {

    private Person person1;
    private Person person11;
    private Person person2;
    private Person person3;
    private Person person4;
    private Person person5;
    private Person person6;
    private Person person7;

    @BeforeEach
    void setUp() {
        person1 = new Person(1, "Николай", "Иванов",
                Gender.MALE, LocalDate.of(1991, 11, 1),
                new Division(0, "A"), new BigDecimal("1000"));
        person11 = new Person(1, "Николай", "Иванов",
                Gender.MALE, LocalDate.of(1991, 11, 1),
                new Division(0, "A"), new BigDecimal("1000"));
        person2 = new Person(2, "Иван", "Николаев",
                Gender.MALE, LocalDate.of(1992, 5, 13),
                new Division(1, "B"), new BigDecimal("2000"));
        person3 = new Person(3, "Павел", "Секов",
                Gender.MALE, LocalDate.of(1993, 1, 14),
                new Division(2, "C"), new BigDecimal("6000"));
        person4 = new Person(4, "Мария", "Враночева",
                Gender.FEMALE, LocalDate.of(1994, 8, 7),
                new Division(1, "B"), new BigDecimal("1500"));
        person5 = new Person(5, "Мария", "Враночева",
                Gender.FEMALE, LocalDate.of(1950, 8, 7),
                new Division(3, "C"), new BigDecimal("5000"));
        person6 = new Person(6, "Сергей", "Враночев",
                Gender.MALE, LocalDate.of(1981, 8, 7),
                new Division(0, "A"), new BigDecimal("4000"));
        person7 = new Person(7, "Сергей", "Враночев",
                Gender.MALE, LocalDate.of(1982, 8, 7),
                new Division(3, "C"), new BigDecimal("2000"));
    }

    @Test
    void add() {
        MyRepository<Person> rep = new MyRepository<>();
        rep.add(person1);

        Assert.assertTrue(rep.get(0).equals(person11));
        Assert.assertTrue(rep.getCount() == 1);
    }

    @Test
    void get() {
        MyRepository<Person> rep = new MyRepository<>();
        rep.add(person1);

        Assert.assertTrue(rep.get(0).equals(person11));
        Assert.assertTrue(rep.getCount() == 1);
    }

    @Test
    void delete() {
        MyRepository<Person> rep = new MyRepository<>();
        rep.add(person1);
        rep.add(person2);

        Assert.assertTrue(rep.getCount() == 2);

        rep.delete(1);

        Assert.assertTrue(rep.getCount() == 1);
    }

    @Test
    void set() {
        MyRepository<Person> rep = new MyRepository<>();
        rep.add(person1);
        rep.add(person2);

        Assert.assertTrue(rep.get(0).equals(person1));

        rep.set(0, person4);

        Assert.assertFalse(rep.get(0).equals(person1));
    }

    @Test
    void sortBy() {
        MyRepository<Person> rep = new MyRepository<>();
        rep.add(person1);
        rep.add(person2);
        rep.add(person3);
        rep.add(person4);
        rep.add(person5);
        rep.add(person6);
        rep.add(person7);

        rep.sortBy(new PersonAgeComparator());
        System.out.println();
    }

    @Test
    void searchBy() {
        MyRepository<Person> rep = new MyRepository<>();
        rep.add(person1);
        rep.add(person2);
        rep.add(person3);
        rep.add(person4);
        rep.add(person5);
        rep.add(person6);
        rep.add(person7);

        Predicate<Person> olderThen = x -> x.getAge() > 40;
        Predicate<Person> sex = x -> x.getGender() == Gender.FEMALE;
        MyRepository<Person> result = (MyRepository<Person>) rep.searchBy(olderThen.and(sex));

        Assert.assertTrue(result.getCount() == 1);
        Assert.assertTrue(result.get(0).equals(person5));
    }
}