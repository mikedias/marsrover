package org.nasa.marsrover;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

/**
 * @author Mike Dias
 */
public class Field {

    private int id;

    private int width;
    private int height;

    public Field() { }

    public Field(int width, int height) {
        Preconditions.checkArgument(width > 0 && height > 0,
            "Width and height should be greater than zero, but w=%s, h=%s",
            width, height);

        this.width = width;
        this.height = height;
    }

    public boolean isInsideBounds(int x, int y) {
        return x >= 0 && y >= 0 && x <= width && y <= height;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
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
