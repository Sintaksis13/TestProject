package com.project;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WorkSpaceTest {
    private final int count = 5;
    private WorkSpace workSpace = new WorkSpace();

    @Test
    public void testFillUpObjects() {
        List<Object> objects = workSpace.fillUpObjects(count);
        assertEquals(objects.size(), count, "Object count is invalid!");
    }

    @Test
    public void testFillUpSubjects() {
        List<Subject> subjects = workSpace.fillUpSubjects(count);
        assertEquals(subjects.size(), count, "Subjects count is invalid!");
    }
}