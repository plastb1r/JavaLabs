package day1;

import org.joda.time.DateTime;

public class Main {
    public static void main(final String[] args) {

        Person person1 = new Person(1, "Николай", "Иванов",
                "мужчина", new DateTime(1991, 11, 16, 0, 0));

        Person person2 = new Person(1, "Иван", "Николаев",
                "мужчина", new DateTime(1992, 5, 13, 0, 0));

        Person person3 = new Person(1, "Павел", "Секов",
                "мужчина", new DateTime(1993, 1, 14, 0, 0));

        Person person4 = new Person(1, "Мария", "Враночева",
                "мужчина", new DateTime(1994, 8, 7, 0, 0));

        MyList<Person> list = new MyList<Person>();

        list.add(person1);
        list.add(person2);
        list.add(person3);
        list.add(person4);

        for (int i = 0; i < list.getCount(); i++) {
            System.out.println(list.get(i).getFirstName());
        }
        System.out.println();

        list.removeAt(0);
        for (int i = 0; i < list.getCount(); i++) {
            System.out.println(list.get(i).getFirstName());
        }
        System.out.println();

        list.insert(3, person1);
        for (int i = 0; i < list.getCount(); i++) {
            System.out.println(list.get(i).getFirstName());
        }
        System.out.println();

        System.out.println(list.get(1).getAge());
    }
}
