package com.project;

import com.project.category.IntegrationTest;
import com.project.distributor.Distributor;
import com.project.entities.Subject;
import com.project.entities.priority.Priority;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Integration test for whole algorithm workflow
 * @author Rinat Abdullin
 */

@Category(IntegrationTest.class)
public class AlgorithmIntegrationTest {
    private static final int COUNT = 10;
    private static Distributor distributor = new Distributor(COUNT);
    private static WorkSpace workSpace = new WorkSpace(distributor);
    private static Subject firstSubject = new Subject.Builder(2, 3, 5, 9).build();
    private static Subject secondSubject = new Subject.Builder(1, 6).priority(Priority.LOW).build();
    private static Subject thirdSubject = new Subject.Builder(0, 1, 4, 7, 8).build();
    private static Subject fourthSubject = new Subject.Builder(2, 4).build();
    private static Subject fifthSubject = new Subject.Builder(6).active(false).build();
    private static Subject sixthSubject = new Subject.Builder(0, 1, 3).build();

    @Before
    public void setUp() {
        workSpace.putInSubject(firstSubject);
        workSpace.putInSubject(secondSubject);
        workSpace.putInSubject(thirdSubject);
        workSpace.putInSubject(fourthSubject);
        workSpace.putInSubject(fifthSubject);
        workSpace.putInSubject(sixthSubject);

        workSpace.putOutSubject(thirdSubject);
    }

    @Test
    public void testTotalSubjectsCount() {
        assertEquals(5, workSpace.getSubjects().size());
    }

    @Test
    public void testFirstSubjectObjectsCount() {
        assertTrue(firstSubject.getObjectsCount() == 2 || firstSubject.getObjectsCount() == 3);
    }

    @Test
    public void testSecondSubjectObjectsCount() {
        assertEquals(1, secondSubject.getObjectsCount());
    }

    @Test
    public void testThirdSubjectObjectsCount() {
        assertEquals(0, thirdSubject.getObjectsCount());
    }

    @Test
    public void testFourthSubjectObjectsCount() {
        assertEquals(2, fourthSubject.getObjectsCount());
    }

    @Test
    public void testFifthSubjectObjectsCount() {
        assertEquals(0, fifthSubject.getObjectsCount());
    }

    @Test
    public void testSixthSubjectObjectsCount() {
        assertEquals(2, sixthSubject.getObjectsCount());
    }
}