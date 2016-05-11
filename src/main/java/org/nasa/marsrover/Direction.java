package org.nasa.marsrover;

/**
 * @author Mike Dias
 */
public enum Direction {

    N(0, 1), E(1, 0), S(0, -1), W(-1, 0);

    static {
        N.left = W;
        N.right = E;

        E.left = N;
        E.right = S;

        S.left = E;
        S.right = W;

        W.left = S;
        W.right = N;
    }

    private Direction right;
    private Direction left;

    private int x;
    private int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Direction getRight() {
        return right;
    }

    public Direction getLeft() {
        return left;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
