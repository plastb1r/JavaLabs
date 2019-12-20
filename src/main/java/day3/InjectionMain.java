package day3;

import day1.PersonAgeComparator;
import day3.DI.MyDIAndIoC;
import day3.entities.Division;
import day3.entities.Person;
import day3.repository.MyRepository;
import day3.sorts.BubbleSort;
import day3.sorts.ISort;
import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.repository.IRepository;

public class InjectionMain {
    public static void main(String[] args) {

        LabFactory factory = new LabFactory();
        IRepository repo = factory.createRepository(MyRepository.class);

        System.out.println(repo);
    }
}
