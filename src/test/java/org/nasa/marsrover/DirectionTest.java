package org.nasa.marsrover;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.nasa.marsrover.Direction.*;

/**
 * @author Mike Dias
 */
public class DirectionTest {

    @Test
    public void testDirectionOrder() {

        assertTrue(N.getLeft() == W);
        assertTrue(N.getRight() == E);

        assertTrue(E.getLeft() == N);
        assertTrue(E.getRight() == S);

        assertTrue(S.getLeft() == E);
        assertTrue(S.getRight() == W);

        assertTrue(W.getLeft() == S);
        assertTrue(W.getRight() == N);

    }

    @Test
    public void testDirectionCoord() {

        // N(0, 1)
        assertTrue(N.getX() == 0);
        assertTrue(N.getY() == 1);

        // E(1, 0)
        assertTrue(E.getX() == 1);
        assertTrue(E.getY() == 0);

        // S(0, -1)
        assertTrue(S.getX() == 0);
        assertTrue(S.getY() == -1);

        // W(-1, 0)
        assertTrue(W.getX() == -1);
        assertTrue(W.getY() == 0);

    }

}