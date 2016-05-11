package org.nasa.marsrover;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

/**
 * @author Mike Dias
 */
public class Field {

    private int width;
    private int height;

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

    // --- getters and setters

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
