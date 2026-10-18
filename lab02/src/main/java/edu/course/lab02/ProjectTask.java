
package edu.course.lab02;

import java.util.Objects;

public class ProjectTask {

    private final String id;
    private final String title;

    private TaskStatus status;
    private int estimatedHours;

    public ProjectTask(
            String id,
            String title,
            TaskStatus status,
            int estimatedHours
    ) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Идентификатор не может быть пустым");
        }

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Название задачи не может быть пустым");
        }

        this.status = Objects.requireNonNull(status, "Статус не может быть null");

        if (estimatedHours < 0) {
            throw new IllegalArgumentException("Оценка трудоемкости не может быть отрицательной");
        }

        this.id = id;
        this.title = title;
        this.estimatedHours = estimatedHours;
    }

    public void changeStatus(TaskStatus newStatus) {
        this.status = Objects.requireNonNull(newStatus, "Новый статус не может быть null");
    }

    public boolean isCompleted() {
        return status == TaskStatus.COMPLETED;
    }

    public void increaseEstimatedHours(int hours) {
        if (hours <= 0) {
            throw new IllegalArgumentException("Количество часов для увеличения должно быть положительным");
        }

        this.estimatedHours = Math.addExact(this.estimatedHours, hours);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public int getEstimatedHours() {
        return estimatedHours;
    }
}
