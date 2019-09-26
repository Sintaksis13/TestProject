package com.project.entities;

/**
 * The {@code Object} class is a representation of the object element from the test task.
 * Instance of the {@code Object} class have two fields:
 *
 * @author Rinat Abdullin
 * @see Subject
 */
public class Object {
    private Subject owner;

    public Subject getOwner() {
        return owner;
    }

    public void setOwner(Subject owner) {
        this.owner = owner;
    }

    public boolean isFree() {
        return owner == null;
    }
}
