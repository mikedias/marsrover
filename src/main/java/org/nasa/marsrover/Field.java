package org.nasa.marsrover;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mike Dias
 */
public class Field {

    private int id;

    private int width;
    private int height;

    @JsonIgnore
    private Map<Integer, Rover> rovers = new HashMap<>();

    public Field() { }

    public Field(int width, int height) {
        Preconditions.checkArgument(width > 0 && height > 0,
            "Width and height should be greater than zero, but w=%s, h=%s",
            width, height);

        this.width = width;
        this.height = height;
    }

    public Collection<Rover> getRovers() {
        return rovers.values();
    }

    public Rover getRover(int roverId) {
        return rovers.get(roverId);
    }

    public Rover addRover(Rover r) {
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
            .add("width", width)
            .add("height", height)
            .add("rovers", rovers)
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
