package org.elsys.health_tracker.entity;

public enum QualityStatus {
    GOOD("Good"),
    BAD("Bad"),
    NEUTRAL("Neutral");

    private final String displayName;

    QualityStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
