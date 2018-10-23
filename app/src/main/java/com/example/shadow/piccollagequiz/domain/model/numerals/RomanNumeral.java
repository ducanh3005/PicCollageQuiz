package com.example.shadow.piccollagequiz.domain.model.numerals;

public class RomanNumeral {
    private final String symbols;

    public enum SYMBOL {
        I(1), V(5), X(10), L(50), C(100), D(500), M(1000);
        private final int value;

        SYMBOL(int i) {
            value = i;
        }

        private int value() {
            return value;
        }
    }

    public RomanNumeral(String symbols) {
        this.symbols = symbols;
    }

    public int toInt() {
        int result = 0;
        char[] charSymbols = symbols.toCharArray();
        for (int pos = 0; pos < charSymbols.length; pos++) {
            if (isSubtractiveNotation(pos, charSymbols)) {
                result += getSubtractiveNotation(pos, ++pos, charSymbols);
            } else {
                result += SYMBOL.valueOf(String.valueOf(charSymbols[pos])).value();
            }
        }
        return result;
    }

    int getSubtractiveNotation(int start, int end, char[] charSymbols) {
        int left = SYMBOL.valueOf(String.valueOf(charSymbols[start])).value();
        int right = SYMBOL.valueOf(String.valueOf(charSymbols[end])).value();
        return right - left;
    }

    boolean isSubtractiveNotation(int start, char[] charSymbols) {
        if (start + 1 >= charSymbols.length) return false;
        int left = SYMBOL.valueOf(String.valueOf(charSymbols[start])).value();
        int right = SYMBOL.valueOf(String.valueOf(charSymbols[start + 1])).value();
        return left < right;
    }
}
