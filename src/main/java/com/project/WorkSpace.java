package com.project;

import java.util.ArrayList;
import java.util.List;

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

    /** Starts execution of the application workflow
     * @param objectsCount count of objects should be created
     * @param subjectsCount count of subjects should be created
     * @param steps count of algorithm steps
     */
    public void startApplication(int objectsCount, int subjectsCount, int steps) {
        generateObjects(objectsCount);
    }

    /** Fills up objects list with instances
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