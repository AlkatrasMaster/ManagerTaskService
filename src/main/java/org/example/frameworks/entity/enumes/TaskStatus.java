package org.example.frameworks.entity.enumes;

public enum TaskStatus {

    WAITING("В ОЖИДАНИИ"),
    IN_PROGRESS("В ПРОЦЕССЕ"),
    COMPLETED("ЗАВЕРШЕНО");


    private final String description;

    TaskStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
