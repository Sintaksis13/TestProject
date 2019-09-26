package com.project.entities.priority;

import com.project.entities.Subject;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class PriorityTest {
    private final Subject lowPrioritySubject = new Subject.Builder()
            .priority(Priority.LOW).build();
    private final Subject normalPrioritySubject = new Subject.Builder()
            .priority(Priority.NORMAL).build();

    @Test
    public void testIsMoreImportant() {
        assertTrue(normalPrioritySubject.getPriority().isMoreImportant(lowPrioritySubject));
    }

    @Test
    public void testIsLessImportant() {
        assertFalse(lowPrioritySubject.getPriority().isMoreImportant(normalPrioritySubject));
    }
}