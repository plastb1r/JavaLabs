package term1.day3.entities;

import term1.day3.DI.Inject;
import ru.vsu.lab.entities.IDivision;

public class Division implements IDivision {
    private int id;
    private String name;

    public Division() {
        this.id = 0;
        this.name = "";
    }

    public Division(int id, String name) {
        this.id = id;
        this.name = name;
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
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Division{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
