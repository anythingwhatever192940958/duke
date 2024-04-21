import java.util.List;

/**
 * Represents a task with a type, description, and completion status.
 * This is an abstract class extended by specific task types such as TodoTask, DeadlineTask, and EventTask.
 */
public abstract class Task {
    protected String type; // Added task type
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the given type and description.
     *
     * @param type        The type of the task (e.g., "T" for Todo, "D" for Deadline, "E" for Event).
     * @param description The description of the task.
     */
    public Task(String type, String description) {
        this.type = type;
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representing the completion status of the task.
     *
     * @return The completion status icon ("X" if done, " " if not done).
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Prints information about the task to the console.
     *
     * @param taskList The list of tasks.
     */
    public void printTaskInfo(List<Task> taskList) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("[" + this.getType() + "][" + this.getStatusIcon() + "] " + this.getDescription());
        System.out.println("Now you have " + taskList.size() + (taskList.size() == 1 ? " task" : " tasks") + " in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Converts the Task object to a string in save format.
     *
     * @return The string representation of the task in save format.
     */
    public abstract String toSaveFormat();
}
