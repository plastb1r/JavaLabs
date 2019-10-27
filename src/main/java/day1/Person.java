package day1;

import org.joda.time.DateTime;

public class Person {
    private int id;
    private String firstName;
    private String secondName;
    private String gender;
    private DateTime birthDay;

    public Person(int id, String firstName, String secondName,
                  String gender, DateTime birthDay) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.gender = gender;
        this.birthDay = birthDay;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getGender() {
        return gender;
    }

    /**
     * Возвращает возраст человека, на основе его даты рождения
     *
     * @return колличество лет
     */
    public int getAge() {
        return DateTime.now().getYear() - birthDay.getYear();
    }
}
