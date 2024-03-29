public class EventTask extends Task {
    public EventTask(String description, String from, String to) {
        super("E", description + " (from: " + from + " to: " + to + ")");
    }

    @Override
    public String toSaveFormat() {
        String[] tokenized = description.split("\\(from: | to: ");
        if (tokenized.length >= 3) {
            String desc = tokenized[0].trim();
            String from = tokenized[1].trim();
            String to = tokenized[2].substring(0, tokenized[2].length() - 1).trim();
            return "E | " + (isDone ? "1" : "0") + " | " + desc + " | " + from + " | " + to;
        } else {
            return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + "N/A" + " | " + "N/A";
        }
    }
}
