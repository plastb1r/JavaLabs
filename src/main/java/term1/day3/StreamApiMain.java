package term1.day3;

//day 4 String Api
//зп по отделениям
// общая зарплата
//все, в которых ,а, больше 30 и зп меньше..
//выдать map по годам {год: сколько человек}    в меин

//свои аннотации для дальнейшего инджекшена в фабриках универсальный Inject.injection(new Class());
// сортировки в разные классы от интерфейса

import term1.day3.entities.Person;
import term1.day3.repository.MyRepository;
import ru.vsu.lab.entities.IDivision;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamApiMain {
    public static void main(String[] args) {
        List<Person> list = DataLoader.load().toList();

        List<Person> filtredList1 = list
                .stream().filter(x -> (x.getFirstName().contains("a") || x.getFirstName().contains("A"))
                        && (x.getAge() > 30) && (x.getSalary().compareTo(new BigDecimal("5000")) > 0))
                .collect(Collectors.toList());

        for (Person person : filtredList1) {
            System.out.println(person);
        }

        Map<IDivision, Double> filtredMap2 = list.stream()
                .collect(Collectors.groupingBy(Person::getDivision, Collectors.summingDouble(Person::getDoubleSalary)));

        for (Map.Entry<IDivision, Double> item : filtredMap2.entrySet()) {
            System.out.println(item.getKey());
        }

        Map<Integer, Long> filteredMap3 = list.stream()
                .collect(Collectors.groupingBy(Person::getAge, Collectors.counting()));

        for (Map.Entry<Integer, Long> item : filteredMap3.entrySet()) {
            System.out.println(item.getKey());
        }
    }
}
