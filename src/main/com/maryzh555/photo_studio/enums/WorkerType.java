package main.com.maryzh555.photo_studio.enums;

/**
 * @author by Zhang M. on 21.04.2023.
 */
public enum WorkerType {

    PHOTOGRAPHER, CUSTOMER_MANAGER, SUPPLY_MANAGER, HR_MANAGER;

    @Override
    public String toString() {
        return name().replace("_", " ");
    }
}
