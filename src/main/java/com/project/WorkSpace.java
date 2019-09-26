package com.project;

import com.project.distributor.Distributor;
import com.project.entities.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Something like the body of the algorithm.
 * {@code WorkSpace} provides several methods and the most important of them are {@link #putInSubject(Subject)} for
 * injection of the new {@code Subject} into the algorithm and {@link #putOutSubject(Subject)} for removing subjects
 * from the algorithm execution.
 *
 * @author Rinat Abdullin
 * @see Distributor
 * @see Subject
 */
public class WorkSpace {
    private final List<Subject> subjects;
    private final Distributor distributor;

    public WorkSpace(Distributor distributor) {
        this.subjects = new ArrayList<>();
        this.distributor = distributor;
    }

    /**
     * Responsible for subject injection into the algorithm execution
     * @param subject put in the algorithm workflow
     */
    protected void putInSubject(Subject subject) {
        distributor.grabEmptyOrLowPriorityObjects(subject);
        if (subject.getObjectsCount() != subject.getDesiredObjectIds().size()) {
            if (subject.isActive()) {
                distributor.findSolution(subject);
            }
        }

        addSubject(subject);
    }

    /**
     * Responsible for subject removing from the algorithm execution
     * @param subject put out from the algorithm workflow
     */
    protected void putOutSubject(Subject subject) {
        distributor.cleanUpObjects(subject);
        removeSubject(subject);
    }

    private void addSubject(Subject subject) {
        subjects.add(subject);
    }

    private void removeSubject(Subject subject) {
        subjects.remove(subject);
    }

    protected List<Subject> getSubjects() {
        return subjects;
    }
}