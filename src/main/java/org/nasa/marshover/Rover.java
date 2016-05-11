package org.nasa.marshover;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

/**
 * @author Mike Dias
 */
public class Rover {

    private Field field;
    private int x;
    private int y;
    private Direction direction;

    public Rover(Field f, int x, int y, Direction direction) {
        // todo reject invalid initial position
        this.field = f;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void move() {
        int newX = x + direction.getX();
        int newY = y + direction.getY();

        Preconditions.checkState(field.isInsideBounds(newX, newY),
            "Movement is out of the bounds. Bounds: [0 to %s, 0 to %s], Movement: [%s, %s]",
            field.getWidth(), field.getHeight(), newX, newY);

        x = newX;
        y = newY;

    }

    public void turnLeft() {
        this.direction = direction.getLeft();
    }

    public void turnRight() {
        this.direction = direction.getRight();
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("field", field)
            .add("x", x)
            .add("y", y)
            .add("direction", direction)
            .toString();
    }

    // ---- getters and setters

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
