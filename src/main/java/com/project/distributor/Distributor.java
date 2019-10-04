package com.project.distributor;

import com.project.entities.Object;
import com.project.generator.ObjectManager;
import com.project.entities.Subject;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * {@code Distributor} class implemented for the fair distribution of objects among the subjects.
 *
 * @author Rinat Abdullin
 */
public class Distributor {
    private final List<Object> objects;

    public Distributor(int objectsCount) {
        this.objects = ObjectManager.generateObjects(objectsCount);
    }

    /**
     * For test purpose only
     * @param objects test object list
     */
    protected Distributor(List<Object> objects) {
        this.objects = objects;
    }

    public void findSolution(Subject subject) {
        Set<Subject> contenders;
        while (true) {
            contenders = findContenders(subject);
            if (!contenders.isEmpty()) {
                Subject theBiggest = getTheBiggestSubject(contenders);
                if (theBiggest.getObjectsCount() - subject.getObjectsCount() > 1) {
                    for (Integer desiredObjectId : subject.getDesiredObjectIds()) {
                        if (isIdNotValid(desiredObjectId)) {
                            continue;
                        }

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
            } else {
                break;
            }
        }
    }

    protected Set<Subject> findContenders(Subject subject) {
        Set<Subject> contenders = new HashSet<>();
        for (Integer desiredObjectId : subject.getDesiredObjectIds()) {
            if (isIdNotValid(desiredObjectId)) {
                continue;
            }

            Object object = objects.get(desiredObjectId);
            if (!object.isFree() && !object.getOwner().equals(subject)) {
                Subject owner = object.getOwner();
                contenders.add(owner);
            }
        }

        return contenders;
    }

    protected Subject getTheBiggestSubject(Set<Subject> subjects) {
        return subjects.stream()
                .max(Comparator.comparingInt(Subject::getObjectsCount))
                .orElse(new Subject.Builder().build());
    }

    public void grabEmptyOrLowPriorityObjects(Subject subject) {
        for (Integer desiredObjectId : subject.getDesiredObjectIds()) {
            if (isIdNotValid(desiredObjectId)) {
                continue;
            }

            Object object = objects.get(desiredObjectId);
            if (object.isFree()) {
                subject.addObject(object);
            } else if (subject.getPriority().isMoreImportant(object.getOwner().getPriority()) && subject.isActive()) {
                Subject owner = object.getOwner();
                owner.removeObject(object);
                subject.addObject(object);
            }
        }
    }

    public void cleanUpObjects(Subject subject) {
        for (Object object : subject.getObjects()) {
            subject.removeObject(object);
        }
    }

    private boolean isIdNotValid(Integer id) {
        return id >= objects.size();
    }
}
