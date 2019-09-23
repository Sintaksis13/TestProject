package com.project;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rinat Abdullin
 * @since 23.09.2019
 */
public class WorkSpace {
    private final List<Object> objects = new ArrayList<>();
    private final List<Subject> subjects = new ArrayList<>();

    /** Starts execution of the application workflow
     * @param objectsCount count of objects should be created
     * @param subjectsCount count of subjects should be created
     * @param steps count of algorithm steps
     */
    public void startApplication(int objectsCount, int subjectsCount, int steps) {
        fillUpObjects(objectsCount);
        //possibly this method is unnecessary because subjects should be added manually
        fillUpSubjects(subjectsCount);
    }

    /** Fills up objects list with instances
     * @param count describes count of objects need to be created
     * @return object list filled up
     */
    protected List<Object> fillUpObjects(int count) {
        for (int index = 0; index < count; index++) {
            objects.add(new Object(index));
        }

        return objects;
    }

    /** Fills up subjects list with instances
     * @param count describes count of subjects need to be created
     * @return subject list filled up
     */
    protected List<Subject> fillUpSubjects(int count) {
        for (int index = 0; index < count; index++) {
            subjects.add(new Subject());
        }

        return subjects;
    }
}