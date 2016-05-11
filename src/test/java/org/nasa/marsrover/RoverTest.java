package org.nasa.marsrover;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.nasa.marsrover.Direction.*;

/**
 * @author Mike Dias
 */
public class RoverTest {

    @Test
    public void basicWalk() throws Exception {

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

    }

}