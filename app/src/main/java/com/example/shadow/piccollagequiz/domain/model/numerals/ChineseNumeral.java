package com.example.shadow.piccollagequiz.domain.model.numerals;

import android.support.annotation.NonNull;

public class ChineseNumeral {
    private final static int MAX_UNIT = 1000;
    private final static int UNIT_DIVIDER = 10;

    private int value;

    enum NUM {
        Zero("零"), One("一"), Two("二"), There("三"), Four("四"), Five("五"), Six("六"),
        Seven("七"), Eight("八"), Nine("九");

        private String value;

        NUM(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }

    enum UNIT {
        Ten("十"), Hundred("百"), Thousand("千");

        private String unit;

        UNIT(String unit) {
            this.unit = unit;
        }

        public String unit() {
            return unit;
        }
    }


    public ChineseNumeral(int value) {
        this.value = value;
    }

    @NonNull
    @Override
    public String toString() {
        return parse(value, MAX_UNIT).toString();
    }

    private StringBuilder parse(int value, int currUnit) {
        if (value == 0 || currUnit == 0) {
            return new StringBuilder();
        }
        if (value / currUnit >= 1 && value >= 1) {
            int num = value / currUnit;
            return new StringBuilder().append(NUM.values()[num].value()).append(parseUnit(currUnit)).append(parse(value - num * currUnit, currUnit / UNIT_DIVIDER));
        }
        return parse(value, currUnit / UNIT_DIVIDER);
    }

    private String parseUnit(int currUnit) {
        switch (currUnit) {
            case 1000:
                return UNIT.Thousand.unit();
            case 100:
                return UNIT.Hundred.unit();
            case 10:
                return UNIT.Ten.unit();
            default:
                return "";
        }
    }
}
