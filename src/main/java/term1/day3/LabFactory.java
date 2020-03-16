package term1.day3;

import term1.day3.DI.InjectException;
import term1.day3.DI.MyDIAndIoC;
import term1.day3.entities.Division;
import term1.day3.entities.Person;
import term1.day3.repository.MyRepository;
import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.factory.ILabFactory;
import ru.vsu.lab.repository.IPersonRepository;
import ru.vsu.lab.repository.IRepository;

public class LabFactory implements ILabFactory {
    @Override
    public IPerson createPerson() {
        return new Person();
    }

    @Override
    public IDivision createDivision() {
        return new Division();
    }

    @Override
    public <T> IRepository<T> createRepository(Class<T> clazz) {
        try {
            return (IRepository<T>) new MyDIAndIoC().inject(new MyRepository());
        } catch (InjectException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public IPersonRepository createPersonRepository() {
        try {
            return (IPersonRepository) new MyDIAndIoC().inject(new MyRepository());
        } catch (InjectException e) {
            e.printStackTrace();
            return null;
        }
    }
}
