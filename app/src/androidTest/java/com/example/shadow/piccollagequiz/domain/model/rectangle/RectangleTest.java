package com.example.shadow.piccollagequiz.domain.model.rectangle;

import org.junit.Test;

import static org.junit.Assert.*;

public class RectangleTest {

    @Test
    public void intersecting() {
        Rectangle rect1 = new Rectangle(0,0,10,10);
        Rectangle rect2 = new Rectangle(0,0,5,5);
        assertTrue(rect1.intersecting(rect2));
    }

    @Test
    public void overlapping() {
        Rectangle rect1 = new Rectangle(0,0,10,10);
        Rectangle rect2 = new Rectangle(0,0,10,10);
        assertTrue(rect1.intersecting(rect2));
    }


    @Test
    public void not_intersecting() {
        Rectangle rect1 = new Rectangle(10,10,20,20);
        Rectangle rect2 = new Rectangle(0,0,5,5);
        assertFalse(rect1.intersecting(rect2));
    }
}