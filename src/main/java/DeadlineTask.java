public class DeadlineTask extends Task {
    public DeadlineTask(String description, String by) {
        super("D", description + " (by: " + by + ")");
    }

    @Override
    public String toSaveFormat() {
        String[] tokenized = description.split("\\(by: ");
        if (tokenized.length >= 2) {
            String desc = tokenized[0].trim();
            String by = tokenized[1].substring(0, tokenized[1].length() - 1).trim();
            return "D | " + (isDone ? "1" : "0") + " | " + desc + " | " + by;
        } else {
            return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + "N/A";
        }
    }
}
