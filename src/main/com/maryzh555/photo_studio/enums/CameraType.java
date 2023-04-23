package main.com.maryzh555.photo_studio.enums;

public enum CameraType {
    NIKON_D850("Nikon D850"),
    CANON_EOS_5D_MARK_IV("Canon EOS 5D Mark IV"),
    SONY_ALPHA_A9("Sony Alpha A9");

    private final String cameraName;

    private CameraType(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getCameraName() {
        return cameraName;
    }
}
