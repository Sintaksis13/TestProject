package com.project;

import java.util.Objects;

public class Object {
    private int id;
    private Subject owner;

    public Object(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Subject getOwner() {
        return owner;
    }

    public void setOwner(Subject owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return this.id == ((Object) o).getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
