package com.project;

import java.util.*;

/**
 * @author Rinat Abdullin
 * @since 23.09.2019
 */
public class WorkSpace {
    private static final int MAX_OBJECTS = 10;
    private final List<Object> objects;
    private final List<Subject> subjects;

    public WorkSpace() {
        this.subjects = new ArrayList<>();
        this.objects = generateObjects(MAX_OBJECTS);
    }

    /**
     * For test purposes only
     * @param subjects list of subjects
     */
    public WorkSpace(List<Subject> subjects) {
        this.subjects = subjects;
        this.objects = generateObjects(MAX_OBJECTS);
    }

    /**
     * Responsible for the subject injection into the algorithm execution
     * @param subject subject
     * @return subject that was put in
     */
    public Subject putInSubject(Subject subject) {
        if (isDesiredObjectsEmpty(subject)) {
            setObjectsToSubject(subject);
        } else {
            grabEmptyObjects(subject);
            if (!subject.isLazy()) {
                findSolution(subject);
            }
        }

        if (subject.getObjectsCount() > 0) {
            addSubject(subject);
        }

        return subject;
    }

    public static void main(String[] args) {
        WorkSpace workSpace = new WorkSpace();
        Subject subject = new Subject.Builder(2, 3, 5, 9).build();
        workSpace.putInSubject(subject);

        subject = new Subject.Builder(0, 1, 4, 6, 7, 8).build();
        workSpace.putInSubject(subject);

        subject = new Subject.Builder(2, 4, 6).build();
        workSpace.putInSubject(subject);
    }

    protected void findSolution(Subject subject) {
        Set<Subject> contenders;
        while (true) {
            contenders = findContenders(subject);
            Subject theBiggest = contenders.stream()
                    .max(Comparator.comparingInt(Subject::getObjectsCount))
                    .orElse(new Subject.Builder().build());
            if (theBiggest.getObjectsCount() - subject.getObjectsCount() > 1) {
                for (Integer desiredObjectId : subject.getDesiredObjectIds()) {
                    Object desiredObject = objects.get(desiredObjectId);
                    if (theBiggest.getObjects().contains(desiredObject)) {
                        theBiggest.removeObject(desiredObject);
                        subject.addObject(desiredObject);
                        break;
                    }
                }
            } else {
                break;
            }
        }
    }

    protected Set<Subject> findContenders(Subject subject) {
        Set<Subject> contendersAndObjectsCount = new HashSet<>();
        for (Integer desiredObjectId : subject.getDesiredObjectIds()) {
            Object object = objects.get(desiredObjectId);
            if (!object.isFree() && !object.getOwner().equals(subject)) {
                Subject owner = object.getOwner();
                contendersAndObjectsCount.add(owner);
            }
        }

        return contendersAndObjectsCount;
    }

    protected void grabEmptyObjects(Subject subject) {
        for (Integer desiredObjectId : subject.getDesiredObjectIds()) {
            Object object = objects.get(desiredObjectId);
            if (object.isFree()) {
                subject.addObject(object);
            }
        }
    }

    protected boolean isDesiredObjectsEmpty(Subject subject) {
        boolean allEmpty = true;
        List<Integer> desiredObjectIds = subject.getDesiredObjectIds();
        for (Integer objectId : desiredObjectIds) {
            Object object = objects.get(objectId);
            if (!object.isFree()) {
                allEmpty = false;
                break;
            }
        }

        return allEmpty;
    }

    protected void setObjectsToSubject(Subject subject) {
        for (Integer desiredObjectId : subject.getDesiredObjectIds()) {
            Object desiredObject = objects.get(desiredObjectId);
            subject.addObject(desiredObject);
        }
    }

    /**
     *
     * @param subject subject
     * @return subject that was put out
     */
    public Subject putOutSubject(Subject subject) {

        return subject;
    }

    /** Fills up object list with instances
     * @param count describes count of objects need to be created
     * @return object list filled up
     */
    protected List<Object> generateObjects(int count) {
        List<Object> objects = new ArrayList<>();
        for (int index = 0; index < count; index++) {
            objects.add(new Object(index));
        }

        return objects;
    }

    protected Subject addSubject(Subject subject) {
        subjects.add(subject);
        return subject;
    }

    protected Subject removeSubject(Subject subject) {
        subjects.remove(subject);
        return subject;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public List<Object> getObjects() {
        return objects;
    }
}