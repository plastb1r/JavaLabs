package term1.day2;

import term1.day1.*;
import org.joda.time.DateTime;
import ru.vsu.lab.entities.IPerson;

import java.util.Comparator;

public class Main {
        public static void main(String[] args) {

                Comparator<IPerson> pcomp = new PersonNameComparator().thenComparing(new PersonAgeComparator());
                // Comparator<Person> pcomp = new PersonNameComparator();
                // Comparator<Person> pcomp = new PersonAgeComparator();
                MyList<Person> list = new MyList<>(pcomp);

                Person person1 = new Person(1, "Николай", "Иванов", "мужчина", new DateTime(1991, 11, 16, 0, 0));
                Person person2 = new Person(2, "Иван", "Николаев", "мужчина", new DateTime(1992, 5, 13, 0, 0));
                Person person3 = new Person(3, "Павел", "Секов", "мужчина", new DateTime(1993, 1, 14, 0, 0));
                Person person4 = new Person(4, "Мария", "Враночева", "женщина", new DateTime(1994, 8, 7, 0, 0));
                Person person5 = new Person(5, "Мария", "Враночева", "женщина", new DateTime(1980, 8, 7, 0, 0));
                Person person6 = new Person(6, "Сергей", "Враночев", "мужчина", new DateTime(1981, 8, 7, 0, 0));
                Person person7 = new Person(7, "Сергей", "Враночев", "мужчина", new DateTime(1982, 8, 7, 0, 0));

                list.add(person1);
                list.add(person7);
                list.add(person6);
                list.add(person2);
                list.add(person3);
                list.add(person5);
                list.add(person4);

                for (int i = 0; i < list.getCount(); i++) {
                        System.out.println(list.get(i).getFirstName() + "  " + list.get(i).getAge());
                }
                System.out.println();

                list.sort();

                for (int i = 0; i < list.getCount(); i++) {
                        System.out.println(list.get(i).getFirstName() + "  " + list.get(i).getAge());
                }
                System.out.println();

                System.out.println(list.search(person1));

                Person newPerson = new Person(8, "Михаил", "Враночев", "мужчина", new DateTime(1982, 8, 7, 0, 0));

                System.out.println(list.search(newPerson));
        }
}
