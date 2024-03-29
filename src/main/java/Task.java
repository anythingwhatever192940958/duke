import java.util.List;

public abstract class Task {
    protected String type; // Added task type
    protected String description;
    protected boolean isDone;

    public Task(String type, String description) {
        this.type = type;
        this.description = description;
        this.isDone = false;
    }

    public Task(String type, String description, boolean isDone) {
        this.type = type;
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void printTaskInfo(List<Task> taskList) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("[" + this.getType() + "][" + this.getStatusIcon() + "] " + this.getDescription());
        System.out.println("Now you have " + taskList.size() + (taskList.size() == 1 ? " task" : " tasks") + " in the list.");
        System.out.println("____________________________________________________________");
    }

    public abstract String toSaveFormat();
}
