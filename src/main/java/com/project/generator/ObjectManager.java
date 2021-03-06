package com.project.generator;

import com.project.entities.Object;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code ObjectManager} simple class with the only static method tp generate objects.
 * Generates list of objects via {@link #generateObjects(int)} method.
 * @author Rinat Abdullin
 * @see Object
 */
public class ObjectManager {
    /** Generates objects list and returns it to the invoker.
     * @param count describes count of objects need to be created.
     * @return object list.
     */
    public static List<Object> generateObjects(int count) {
        List<Object> objects = new ArrayList<>();
        for (int index = 0; index < count; index++) {
            objects.add(new Object());
        }

        return objects;
    }
}
