package day1;

import ru.vsu.lab.entities.IPerson;

import java.util.Comparator;

public class PersonAgeComparator implements Comparator<IPerson> {

    public int compare(IPerson a, IPerson b) {

        if (a.getAge() > b.getAge())
            return 1;
        else if (a.getAge() < b.getAge())
            return -1;
        else
            return 0;
    }
}
