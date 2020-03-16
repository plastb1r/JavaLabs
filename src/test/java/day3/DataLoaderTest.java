package day3;

import term1.day3.DataLoader;
import term1.day3.entities.Person;
import term1.day3.repository.MyRepository;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataLoaderTest {

    @Test
    void load() {
        MyRepository<Person> rep = DataLoader.load();

        Assert.assertTrue(rep.getCount() == 25898);
    }
}