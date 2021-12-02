package rahmet.qa.tests;

public enum Platform {
    SONY("Sony"),
    NINTENDO("Nintendo"),
    XBOX("Xbox");

    private final String platform;

    Platform(String platform) {
        this.platform = platform;
    }

    public String getPlatform() {
        return platform;
    }
}
