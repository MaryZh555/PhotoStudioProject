package com.maryzh555.photo_studio.enums;

public enum PhotoPaperType {
    STANDARD("4x6 inches", 2),
    LARGE("5x7 inches", 5),
    PROFESSIONAL("8x10 inches", 10);

    private final String sizeInInches;
    private final int costPerCopy;

    PhotoPaperType(String size, int costPerCopy) {
        this.sizeInInches = size;
        this.costPerCopy = costPerCopy;
    }

    public String getSizeInInches() {
        return sizeInInches;
    }

    public int getCostPerCopy() {
        return costPerCopy;
    }
}

