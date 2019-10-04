package com.project.generator;

import com.project.entities.Object;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ObjectManagerTest {
    @Test
    public void testGenerateObjects() {
        int count = 10;
        List<Object> objects = ObjectManager.generateObjects(count);
        assertEquals(count, objects.size());
    }
}