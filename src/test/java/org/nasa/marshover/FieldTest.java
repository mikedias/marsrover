package org.nasa.marshover;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Mike Dias
 */
public class FieldTest {

    @Test
    public void testFieldBounds() throws Exception {

        Field f = new Field(5, 5);

        assertTrue(f.isInsideBounds(0, 0));
        assertTrue(f.isInsideBounds(2, 2));
        assertTrue(f.isInsideBounds(5, 5));

        assertFalse(f.isInsideBounds(-1, -1));
        assertFalse(f.isInsideBounds(0, -1));
        assertFalse(f.isInsideBounds(-1, 0));
        assertFalse(f.isInsideBounds(6, 6));
        assertFalse(f.isInsideBounds(0, 6));
        assertFalse(f.isInsideBounds(6, 0));

    }

}