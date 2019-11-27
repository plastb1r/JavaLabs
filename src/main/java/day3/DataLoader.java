package day3;

import day3.entities.Division;
import day3.entities.Person;
import day3.repository.MyRepository;
import ru.vsu.lab.entities.enums.Gender;
import ru.vsu.lab.repository.IRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

public class DataLoader {

    public static MyRepository load() {
        String path = "jar:file:C:/Users/wwwki/.m2/repository/com/github/MaickelVRN/NCLab/task1/master-ee9a6fa3fb-1/task1-master-ee9a6fa3fb-1.jar!/persons.csv";

        String[] lines;
        MyRepository<Person> repository = new MyRepository<>();
        ArrayList<Division> divisions = new ArrayList<>();

        try {
            URL url = new URL(path);
            InputStream is = url.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line = br.readLine();
            line = br.readLine();
            Division localDivision = null;
            int numOfDivision = 0;

            while (line != null) {
                lines = line.split(";");

                String[] date = lines[3].split("\\.");
                int[] intDate = new int[]{
                        Integer.parseInt(date[0]),
                        Integer.parseInt(date[1]),
                        Integer.parseInt(date[2])};

                for (Division division : divisions) {
                    if (division.getName().equals(lines[4])) {
                        localDivision = division;
                        break;
                    }
                }
                if (localDivision == null) {
                    divisions.add(new Division(numOfDivision++, lines[4]));
                    localDivision = divisions.get(numOfDivision - 1);
                }

                repository.add(
                        new Person(
                                Integer.parseInt(lines[0]), lines[1], lines[1],
                                lines[2].equals("Male") ? Gender.MALE : Gender.FEMALE,
                                LocalDate.of(intDate[2], intDate[1], intDate[0]),
                                localDivision,
                                new BigDecimal(lines[5])
                        )
                );

                line = br.readLine();
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return repository;
    }
}
