package com.project.distributor;

import com.project.entities.Object;
import com.project.entities.Subject;
import com.project.entities.priority.Priority;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class DistributorTest {
    private Object testObject1 = new Object();
    private Object testObject2 = new Object();
    private Object testObject3 = new Object();
    private Object testObject4 = new Object();
    private Object objectForCleanUp = new Object();

    private Subject contender = new Subject.Builder().build();
    private Subject lowPriorityContender = new Subject.Builder().priority(Priority.LOW).build();
    private Subject normalPriorityContender = new Subject.Builder(0, 3).priority(Priority.NORMAL).build();
    private Subject subjectForCleanUp = new Subject.Builder().build();
    private Subject testSubject;

    private static Distributor distributor;

    @Before
    public void setUp() {
        testSubject = new Subject.Builder(1, 2).build();
        List<Object> objects = new ArrayList<>();

        lowPriorityContender.addObject(testObject1);
        objects.add(testObject1);

        contender.addObject(testObject2);
        objects.add(testObject2);

        contender.addObject(testObject3);
        objects.add(testObject3);

        objects.add(testObject4);

        subjectForCleanUp.addObject(objectForCleanUp);
        objects.add(objectForCleanUp);

        distributor = new Distributor(objects);
    }

    @Test
    public void tesFindSolution() {
        distributor.findSolution(testSubject);

        assertEquals(1, testSubject.getObjectsCount());
    }

    @Test
    public void testFindContenders() {
        Set<Subject> contenders = distributor.findContenders(testSubject);

        assertEquals(contender, contenders.iterator().next());
    }

    @Test
    public void testGetTheBiggestSubject() {
        Set<Subject> subjects = new HashSet<>();
        subjects.add(testSubject);
        subjects.add(contender);

        Subject theBiggestSubject = distributor.getTheBiggestSubject(subjects);

        assertEquals(contender, theBiggestSubject);
    }

    @Test
    public void testGrabEmptyOrLowPriorityObjects() {
        distributor.grabEmptyOrLowPriorityObjects(normalPriorityContender);

        assertEquals(2, normalPriorityContender.getObjectsCount());
    }

    @Test
    public void testCleanUpObjects() {
        distributor.cleanUpObjects(subjectForCleanUp);

        assertEquals(0, subjectForCleanUp.getObjectsCount());
    }
}