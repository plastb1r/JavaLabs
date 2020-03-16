package term1.day3;

import term1.day3.repository.MyRepository;
import ru.vsu.lab.repository.IRepository;

public class InjectionMain {
    public static void main(String[] args) {

        LabFactory factory = new LabFactory();
        IRepository repo = factory.createRepository(MyRepository.class);

        System.out.println(repo);
    }
}
