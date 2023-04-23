package main.com.maryzh555.photo_studio.models;

import main.com.maryzh555.photo_studio.enums.CameraType;

/**
 * @author by Zhang M. on 12.04.2023.
 */
public class Camera {

    CameraType type;

    private boolean isInUse;

    public Camera(CameraType type, boolean isInUse) {
        this.type = type;
        this.isInUse = isInUse;
    }

    public CameraType getType() {
        return type;
    }

    public void setType(CameraType type) {
        this.type = type;
    }

    public boolean isInUse() {
        return isInUse;
    }

    public void setInUse(boolean inUse) {
        isInUse = inUse;
    }
}
