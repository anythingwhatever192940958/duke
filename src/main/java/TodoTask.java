/**
 * Represents a todo task with a description.
 * Extends the abstract Task class.
 */
public class TodoTask extends Task {

    /**
     * Constructs a TodoTask object with the given description.
     *
     * @param description The description of the todo task.
     */
    public TodoTask(String description) {
        super("T", description);
    }

    /**
     * Converts the TodoTask object to a string in save format.
     *
     * @return The string representation of the todo task in save format.
     */
    @Override
    public String toSaveFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
