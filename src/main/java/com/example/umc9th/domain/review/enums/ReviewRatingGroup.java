package com.example.umc9th.domain.review.enums;

import java.util.Arrays;

public enum ReviewRatingGroup {

    FIVE(5, 5),
    FOUR(4, 4),
    THREE(3, 3),
    TWO(2, 2),
    ONE(1, 1);

    private final int minInclusive;
    private final int maxInclusive;

    ReviewRatingGroup(int minInclusive, int maxInclusive) {
        this.minInclusive = minInclusive;
        this.maxInclusive = maxInclusive;
    }

    public int getMinInclusive() {
        return minInclusive;
    }

    public int getMaxInclusive() {
        return maxInclusive;
    }

    public static ReviewRatingGroup fromValue(Integer value) {
        if (value == null) {
            return null;
        }
        return Arrays.stream(values())
                .filter(group -> group.minInclusive == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported rating group value: " + value));
    }
}



