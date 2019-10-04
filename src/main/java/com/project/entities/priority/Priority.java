package com.project.entities.priority;

import com.project.entities.Subject;

/**
 * {@code Priority} enum is necessary for determining {@code Subject} priority to own the {@code Object}.
 *
 * @author Rinat Abdullin
 * @see Subject
 * @see Object
 */
public enum Priority {
    LOW(0), NORMAL(1);

    private int level;

    Priority(int level) {
        this.level = level;
    }

    /**
     * Determines is provided subject less important then the targeted one or not.
     * @param priority needs to compare it with current priority.
     * @return true if target more important and false if provided subject is more important.
     */
    public boolean isMoreImportant(Priority priority) {
        return this.level > priority.level;
    }
}
