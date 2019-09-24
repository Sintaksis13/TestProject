package com.project.priority;

public enum Priority {
    LOW(0), NORMAL(1);

    private int level;

    Priority(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
