package com.example.shadow.piccollagequiz.domain.model.roman_numerals;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RomanNumeralTest {


    @Test
    public void toInt() {
        assertEquals(39, new RomanNumeral("XXXIX").toInt());
        assertEquals(246, new RomanNumeral("CCXLVI").toInt());
        assertEquals(207, new RomanNumeral("CCVII").toInt());
        assertEquals(1066, new RomanNumeral("MLXVI").toInt());
        assertEquals(1776, new RomanNumeral("MDCCLXXVI").toInt());
        assertEquals(1954, new RomanNumeral("MCMLIV").toInt());
        assertEquals(2014, new RomanNumeral("MMXIV").toInt());
        assertEquals(1990, new RomanNumeral("MCMXC").toInt());
        assertEquals(2018, new RomanNumeral("MMXVIII").toInt());
    }

    @Test
    public void getSubtractiveNotation() {
        RomanNumeral romanNumeral = new RomanNumeral("IX");
        char[] test = new char[]{'I','X'};
        assertEquals(9,romanNumeral.getSubtractiveNotation(0,1,test));
    }

    @Test
    public void isSubtractiveNotation() {
        RomanNumeral romanNumeral = new RomanNumeral("IX");
        char[] test = new char[]{'I','X'};
        assertTrue(romanNumeral.isSubtractiveNotation(0,test));
    }
}