package org.nasa.marsrover;

import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.bootstrap.GenericBootstrap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
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

        Validator validator = Validation.byDefaultProvider().configure().buildValidatorFactory().getValidator();

        assertThat(validator.validate(new Field(-1, -1)), hasSize(2));
        assertThat(validator.validate(new Field(0, 1)), hasSize(1));
        assertThat(validator.validate(new Field(1, 0)), hasSize(1));
        assertThat(validator.validate(new Field(1, 1)), hasSize(0));

    }

}