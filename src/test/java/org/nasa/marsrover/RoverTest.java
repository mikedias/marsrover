package org.nasa.marsrover;

import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.nasa.marsrover.Direction.*;

/**
 * @author Mike Dias
 */
public class RoverTest {

    @Test
    public void goldenTest() {

        Field f = new Field(5, 5);

        Rover r = new Rover(f, 1, 2, N);

        // L M L M L M L M M
        r.turnLeft().move().turnLeft().move().turnLeft().move().turnLeft().move().move();
        assertTrue(r.getX() == 1);
        assertTrue(r.getY() == 3);
        assertTrue(r.getDirection() == N);

        r = new Rover(f, 3, 3, E);

        // M M R M M R M R R M
        r.move().move().turnRight().move().move().turnRight().move().turnRight().turnRight().move();
        assertTrue(r.getX() == 5);
        assertTrue(r.getY() == 1);
        assertTrue(r.getDirection() == E);

    }

    @Test
    public void basicWalk() {

        Field f = new Field(5, 5);
        Rover r = new Rover(f, 0, 0, N);

        r.move();
        assertTrue(r.getX() == 0);
        assertTrue(r.getY() == 1);
        assertTrue(r.getDirection() == N);

        r.move();
        assertTrue(r.getX() == 0);
        assertTrue(r.getY() == 2);
        assertTrue(r.getDirection() == N);

        r.turnRight();
        r.move();
        assertTrue(r.getX() == 1);
        assertTrue(r.getY() == 2);
        assertTrue(r.getDirection() == E);

        r.move();
        assertTrue(r.getX() == 2);
        assertTrue(r.getY() == 2);
        assertTrue(r.getDirection() == E);

        r.turnRight();
        r.move();
        assertTrue(r.getX() == 2);
        assertTrue(r.getY() == 1);
        assertTrue(r.getDirection() == S);

        r.turnRight();
        r.move();
        assertTrue(r.getX() == 1);
        assertTrue(r.getY() == 1);
        assertTrue(r.getDirection() == W);

        r.turnLeft();
        r.move();
        assertTrue(r.getX() == 1);
        assertTrue(r.getY() == 0);
        assertTrue(r.getDirection() == S);

        try {
            r.move();
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }

    }

    @Test
    public void testRoverPreconditions() {

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Field f = new Field(5, 5);
        try {
            f.addRover(new Rover(f, -1, -1, N));
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        assertThat(validator.validate(new Rover(null, 0, 0, null)), hasSize(1));

        Rover r = new Rover(f, 1, 2, N);
        assertThat(validator.validate(r), hasSize(0));
        f.addRover(r);

    }

}