// Todo inherit Task
public class TodoTask extends Task {
    public TodoTask(String description) {
        super("T", description);
    }

    @Override
    public String toSaveFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}