package day1;

import java.util.Comparator;

public class PersonNameComparator implements Comparator<Person> {

    public int compare(Person a, Person b) {

        return a.getFirstName().compareTo(b.getFirstName());
    }
}
