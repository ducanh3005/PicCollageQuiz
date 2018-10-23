package com.example.shadow.piccollagequiz.domain.model.numerals;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChineseNumeralTest {

    @Test
    public void testToString() {
        assertEquals("一千",new ChineseNumeral(1000).toString());
        assertEquals("三百九十四",new ChineseNumeral(394).toString());
        assertEquals("一千九百",new ChineseNumeral(1900).toString());
    }
}