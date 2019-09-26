package com.project.entities;

import com.project.entities.priority.Priority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * The {@code Subject} class is a representation of the subject element from the test task and
 * implements "builder" pattern via {@code Subject.Builder} class.
 * Field {@link #active} means subject will be contesting for the objects.
 * Also {@code Subject} class provides a couple of methods to remove ({@link #removeObject(Object)} object from
 * the {@link #objects} and add ({@link #addObject(Object)}) objects to the {@link #objects}.
 *
 * @author Rinat Abdullin
 * @see Object
 */
public class Subject {
    private final List<Integer> desiredObjectIds;
    private final List<Object> objects;
    private final Priority priority;
    private final boolean active;

    private Subject(Builder builder) {
        this.desiredObjectIds = builder.getObjectIds();
        this.priority = builder.getPriority();
        this.active = builder.isActive();
        this.objects = new ArrayList<>();
    }

    public void addObject(Object object) {
        object.setOwner(this);
        objects.add(object);
    }

    public void removeObject(Object object) {
        object.setOwner(null);
        objects.remove(object);
    }

    public List<Integer> getDesiredObjectIds() {
        return desiredObjectIds;
    }

    public List<Object> getObjects() {
        return objects;
    }

    public int getObjectsCount() {
        return objects.size();
    }

    public Priority getPriority() {
        return priority;
    }

    public boolean isActive() {
        return active;
    }

    public static class Builder {
        private final List<Integer> objectIds;
        private Priority priority = Priority.NORMAL;
        private boolean active = true;

        public Builder(Integer... objectIds) {
            this.objectIds = Arrays.asList(objectIds);
        }

        public Builder priority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public Builder active(boolean active) {
            this.active = active;
            return this;
        }

        boolean isActive() {
            return active;
        }

        Priority getPriority() {
            return priority;
        }

        List<Integer> getObjectIds() {
            return objectIds;
        }

        public Subject build() {
            return new Subject(this);
        }
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return active == subject.active &&
                desiredObjectIds.equals(subject.desiredObjectIds) &&
                Objects.equals(objects, subject.objects) &&
                priority == subject.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(desiredObjectIds, objects, priority, active);
    }
}
