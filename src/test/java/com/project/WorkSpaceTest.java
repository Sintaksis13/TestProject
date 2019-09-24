package com.project;

import com.project.priority.Priority;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for WorkSpace class
 * @author Rinat Abdullin
 * @since 23.09.2019
 */
class WorkSpaceTest {
    private static final int COUNT = 5;
    private static WorkSpace workSpace;
    private static final Integer[] objectIds = {1, 2, 3};
    private static final Subject testSubject = new Subject.Builder(objectIds)
            .lazy(true)
            .priority(Priority.LOW)
            .build();

    @BeforeAll
    static void setUp() {
        workSpace = new WorkSpace(getTestSubjects());
    }

    /**
     * Verifies objects count after method invocation
     */
    @Test
    void testGenerateObjects() {
        List<Object> objects = workSpace.generateObjects(COUNT);
        assertEquals(objects.size(), COUNT, "Objects count is invalid!");
    }

    /**
     * Tests subject addition to the list
     */
    @Test
    void testAddSubject() {
        Subject subject = new Subject.Builder()
                .lazy(true)
                .build();
        Subject addedSubject = workSpace.addSubject(subject);
        assertTrue(workSpace.getSubjects().contains(addedSubject));
    }

    /**
     * Tests subject removing from the list
     */
    @Test
    void testRemoveSubject() {
        Subject removedSubject = workSpace.removeSubject(testSubject);
        assertFalse(workSpace.getSubjects().contains(removedSubject));
    }

    @Test
    void testPutInSubject() {
    }

    @Test
    void testPutOutSubject() {
    }

    @Test
    void testDesiredObjectsEmpty() {
    }

    /**
     * Generate subjects list for WorkSpace class test constructor
     * @return subjects list
     */
    private static List<Subject> getTestSubjects() {
        List<Subject> subjects = new ArrayList<>();
        subjects.add(testSubject);
        return subjects;
    }

    private List<Object> getObjectsByIds() {
        List<Object> objects = new ArrayList<>();
        for (Integer objectId : objectIds) {
            objects.add(workSpace.getObjects().get(objectId));
        }

        return objects;
    }
}