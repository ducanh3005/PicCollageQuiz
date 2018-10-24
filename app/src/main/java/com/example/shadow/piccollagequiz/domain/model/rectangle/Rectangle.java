package com.example.shadow.piccollagequiz.domain.model.rectangle;

import android.support.annotation.NonNull;

public class Rectangle {
    private int ax;
    private int ay;
    private int bx;
    private int by;

    public Rectangle(int ax, int ay, int bx, int by) {
        this.ax = ax;
        this.ay = ay;
        this.bx = bx;
        this.by = by;
    }

    @NonNull
    public int[] getRect() {
        return new int[]{ax, ay, bx, by};
    }

    public boolean intersecting(Rectangle rectangle) {
        return isXIntersecting(rectangle) && isYIntersecting(rectangle);
    }

    private boolean isXIntersecting(Rectangle rectangle) {
        return rectangle.ax <= bx && rectangle.bx >= ax;
    }

    private boolean isYIntersecting(Rectangle rectangle) {
        return rectangle.ay <= by && rectangle.by >= ay;
    }
}
