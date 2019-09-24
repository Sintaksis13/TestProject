package com.project;

import com.project.priority.Priority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Subject {
    private final List<Integer> desiredObjectIds;
    private final List<Object> objects;
    private final Priority priority;
    private final boolean lazy;

    private Subject(Builder builder) {
        this.desiredObjectIds = builder.getObjectIds();
        this.priority = builder.getPriority();
        this.lazy = builder.isLazy();
        this.objects = new ArrayList<>();
    }

    public Object addObject(Object object) {
        object.setOwner(this);
        objects.add(object);
        return object;
    }

    public Object removeObject(Object object) {
        objects.remove(object);
        object.setOwner(null);
        return object;
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

    public boolean isLazy() {
        return lazy;
    }

    public static class Builder {
        private final List<Integer> objectIds;
        private Priority priority = Priority.NORMAL;
        private boolean lazy = false;

        public Builder(Integer... objectIds) {
            this.objectIds = Arrays.asList(objectIds);
        }

        public Builder priority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public Builder lazy(boolean lazy) {
            this.lazy = lazy;
            return this;
        }

        public boolean isLazy() {
            return lazy;
        }

        public Priority getPriority() {
            return priority;
        }

        public List<Integer> getObjectIds() {
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
        return lazy == subject.lazy &&
                desiredObjectIds.equals(subject.desiredObjectIds) &&
                Objects.equals(objects, subject.objects) &&
                priority == subject.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(desiredObjectIds, objects, priority, lazy);
    }
}
