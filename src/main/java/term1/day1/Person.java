package term1.day1;

import org.joda.time.DateTime;

/**
 * Тестовый класс, описывающий человека
 */
public class Person {
    private int id;
    private String firstName;
    private String secondName;
    private String gender;
    private DateTime birthDay;

    public Person(int id, String firstName, String secondName, String gender, DateTime birthDay) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.gender = gender;
        this.birthDay = birthDay;
    }

    /**
     * Возвращает возраст человека, на основе его даты рождения
     *
     * @return колличество лет
     */
    public int getAge() {
        return DateTime.now().getYear() - birthDay.getYear();
    }

    public void setId(int id) {
        this.id = id;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthDay(DateTime birthDay) {
        this.birthDay = birthDay;
    }

}
