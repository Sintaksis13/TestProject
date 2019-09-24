package com.project;

import com.project.priority.Priority;

import java.util.Arrays;
import java.util.List;

public class Subject {
    private final List<Object> objects;
    private final Priority priority;
    private final boolean lazy;

    private Subject(Builder builder) {
        this.objects = builder.getObjects();
        this.priority = builder.getPriority();
        this.lazy = builder.isLazy();
    }

    public Object addObject(Object object) {
        objects.add(object);
        return object;
    }

    public Object removeObject(Object object) {
        objects.remove(object);
        return object;
    }

    public List<Object> getObjects() {
        return objects;
    }

    public Priority getPriority() {
        return priority;
    }

    public boolean isLazy() {
        return lazy;
    }

    public static class Builder {
        private final List<Object> objects;
        private Priority priority = Priority.NORMAL;
        private boolean lazy = false;

        public Builder(Object... objects) {
            this.objects = Arrays.asList(objects);
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

        public List<Object> getObjects() {
            return objects;
        }

        public Subject build() {
            return new Subject(this);
        }
    }
}
