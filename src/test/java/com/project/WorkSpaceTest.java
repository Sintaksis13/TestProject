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
    private static Subject testSubject = new Subject.Builder()
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
    void testFillUpObjectsCount() {
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

    /**
     * Generate subjects list for WorkSpace class test constructor
     * @return subjects list
     */
    private static List<Subject> getTestSubjects() {
        List<Subject> subjects = new ArrayList<>();
        subjects.add(testSubject);
        return subjects;
    }
}