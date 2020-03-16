package term1.day3.entities;

import term1.day3.DI.Inject;
import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.entities.enums.Gender;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Person implements IPerson {
    private int id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate birthDay;

    @Inject
    private IDivision division;
    private BigDecimal salary;

    public Person(int id, String firstName, String lastName, Gender gender, LocalDate birthDay, IDivision division,
            BigDecimal salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDay = birthDay;
        this.division = division;
        this.salary = salary;
    }

    public Person() {
        this.id = 0;
        this.firstName = "";
        this.lastName = "";
        this.gender = Gender.MALE;
        this.birthDay = LocalDate.now();
        this.division = null;
        this.salary = new BigDecimal("0");
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public void setFirstName(String name) {
        this.firstName = name;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public void setLastName(String name) {
        this.lastName = name;
    }

    @Override
    public LocalDate getBirthdate() {
        return this.birthDay;
    }

    @Override
    public void setBirthdate(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public Integer getAge() {
        return LocalDate.now().getYear() - this.birthDay.getYear();
    }

    @Override
    public Gender getGender() {
        return this.gender;
    }

    @Override
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public IDivision getDivision() {
        return this.division;
    }

    @Override
    public void setDivision(IDivision iDivision) {
        this.division = iDivision;
    }

    @Override
    public BigDecimal getSalary() {
        return this.salary;
    }

    @Override
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public double getDoubleSalary() {
        return Integer.parseInt(this.salary.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Person person = (Person) o;
        return this.id == person.id && this.firstName.equals(person.firstName) && this.lastName.equals(person.lastName)
                && this.gender == person.gender && this.division.getId() == person.division.getId()
                && this.division.getName().equals(person.division.getName())
                && this.salary.toString().equals(person.salary.toString());
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\''
                + ", gender=" + gender + ", birthDay=" + birthDay + ", division=" + division + ", salary=" + salary
                + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, gender, birthDay, division, salary);
    }
}
