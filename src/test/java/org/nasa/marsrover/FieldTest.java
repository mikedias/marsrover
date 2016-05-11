package org.nasa.marsrover;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author Mike Dias
 */
public class FieldTest {

    @Test
    public void testFieldBounds() {

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

    @Test
    public void testFieldWidthHeightPreconditions() {

        try {
            new Field(-1, -1);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            new Field(0, 1);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            new Field(1, 0);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        Field f = new Field(1, 1);
        assertTrue(f.getWidth() == 1);
        assertTrue(f.getHeight() == 1);

    }

}