package model;

import java.util.Objects;

public class Subject {

    private int id;
    private String description;

    public Subject(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Subject() {

    }

    @Override
    public String toString() {
        return "model.Subject{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;
        Subject subject = (Subject) o;
        return id == subject.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
