package task;

class TaskDefinition {

    private String name;
    private String description;

    TaskDefinition() {
    }

    TaskDefinition(String name, String description) {
        this.name = name;
        this.description = description;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }
}