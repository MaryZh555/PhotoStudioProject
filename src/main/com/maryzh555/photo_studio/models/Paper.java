package main.com.maryzh555.photo_studio.models;

import main.com.maryzh555.photo_studio.enums.PhotoPaperType;

/**
 * @author by Zhang M. on 18.04.2023.
 */
public class Paper {

    private int qty;

    private PhotoPaperType type;

    public Paper(int qty, PhotoPaperType type) {
        this.qty = qty;
        this.type = type;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public PhotoPaperType getType() {
        return type;
    }

    public void setType(PhotoPaperType type) {
        this.type = type;
    }
}
