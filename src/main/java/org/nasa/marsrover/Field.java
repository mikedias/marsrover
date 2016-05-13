package org.nasa.marsrover;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mike Dias
 */
public class Field {

    private int id;

    @Min(value = 1, message = "Width should be greater than zero")
    private int width;

    @Min(value = 1, message = "Height should be greater than zero")
    private int height;

    @JsonIgnore
    private Map<Integer, Rover> rovers = new HashMap<>();

    public Field() { /* serialization proposes */ }

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Collection<Rover> getRovers() {
        return rovers.values();
    }

    public Rover getRover(int roverId) {
        return rovers.get(roverId);
    }

    public Rover addRover(@NotNull Rover r) {
        Preconditions.checkArgument(this.isInsideBounds(r.getX(), r.getY()),
            "The rover initial position should be inside the field. %s, x=%s, y=%s",
            this.toString(), r.getX(), r.getY());

        return rovers.put(r.getId(), r);
    }

    public Rover removeRover(int roverId) {
        return rovers.remove(roverId);
    }

    public boolean isInsideBounds(int x, int y) {
        return x >= 0 && y >= 0 && x <= width && y <= height;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("id", id)
            .add("width", width)
            .add("height", height)
            .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return getId() == field.getId();
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(getId());
    }

    // --- getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
