package com.example.shadow.piccollagequiz.domain.model.roman_numerals;

import android.support.annotation.NonNull;

public class ChineseNumeral {
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
        return parse(value).toString();
    }

    private StringBuilder parse(int value) {
        StringBuilder result = new StringBuilder();

        if (value / 1000 >= 1) {
            int num = value / 1000;
            return result.append(NUM.values()[num].value()).append(UNIT.Thousand.unit()).append(parse(value - num * 1000));
        }

        if (value / 100 >= 1) {
            int num = value / 100;
            return result.append(NUM.values()[num].value()).append(UNIT.Hundred.unit()).append(parse(value - num * 100));
        }

        if (value / 10 >= 1) {
            int num = value / 10;
            return result.append(NUM.values()[num].value()).append(UNIT.Ten.unit()).append(parse(value - num * 10));
        }

        if (value >= 1) {
            return new StringBuilder(NUM.values()[value].value());
        }
        return new StringBuilder();
    }
}
