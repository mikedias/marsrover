package org.nasa.marsrover;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

/**
 * @author Mike Dias
 */
public class Rover {

    private int id;

    private Field field;
    private int x;
    private int y;
    private Direction direction;

    public Rover() { }

    public Rover(Field f, int x, int y, Direction d) {
        Preconditions.checkArgument(f != null, "The rover should be landed in some field");
        Preconditions.checkArgument(d != null, "The rover direction cannot be null");

        Preconditions.checkArgument(f.isInsideBounds(x, y),
            "The rover initial position should be inside the field. %s, x=%s, y=%s",
            f.toString(), x, y);

        this.field = f;
        this.x = x;
        this.y = y;
        this.direction = d;
    }

    public void performCmd(char cmd) {
        switch (Character.toUpperCase(cmd)) {
            case 'L': turnLeft(); break;
            case 'R': turnRight(); break;
            case 'M': move(); break;
            default: // invalid command. ignoring
        }
    }

    public Rover move() {
        int newX = x + direction.getX();
        int newY = y + direction.getY();

        Preconditions.checkState(field.isInsideBounds(newX, newY),
            "Movement is out of bounds. Bounds: [0 to %s, 0 to %s], Movement: [%s, %s]",
            field.getWidth(), field.getHeight(), newX, newY);

        x = newX;
        y = newY;

        return this;

    }

    public Rover turnLeft() {
        direction = direction.getLeft();
        return this;
    }

    public Rover turnRight() {
        direction = direction.getRight();
        return this;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rover rover = (Rover) o;
        return getId() == rover.getId();
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(getId());
    }

    // ---- getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
