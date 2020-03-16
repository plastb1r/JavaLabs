package term1.day1;

import ru.vsu.lab.entities.IPerson;

import java.util.Comparator;

public class PersonNameComparator implements Comparator<IPerson> {

    public int compare(IPerson a, IPerson b) {
        return a.getFirstName().compareTo(b.getFirstName());
    }
}
