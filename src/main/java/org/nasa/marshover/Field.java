package org.nasa.marshover;

import com.google.common.base.Objects;

/**
 * @author Mike Dias
 */
public class Field {

    private int width;
    private int height;

    public Field(int width, int height) {
        // todo reject negatives values
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
