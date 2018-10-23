package com.example.shadow.piccollagequiz.domain.model.rectangle;

public class Rectangle {
    int ax;
    int ay;
    int bx;
    int by;

    public Rectangle(int ax, int ay, int bx, int by) {
        this.ax = ax;
        this.ay = ay;
        this.bx = bx;
        this.by = by;
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
