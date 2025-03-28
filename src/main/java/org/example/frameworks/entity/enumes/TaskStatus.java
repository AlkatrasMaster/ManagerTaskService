package org.example.frameworks.entity.enumes;

public enum TaskStatus {

    WAITING("в ожидании"),
    IN_PROGRESS("в процессе"),
    COMPLETED("завершено");


    private final String description;

    TaskStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
