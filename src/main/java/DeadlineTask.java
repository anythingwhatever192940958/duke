import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * Extends the abstract Task class.
 */
public class DeadlineTask extends Task {
    private LocalDate byDate;

    /**
     * Constructs a DeadlineTask object with the given description and deadline date.
     *
     * @param description The description of the task.
     * @param byDate      The deadline date of the task.
     */
    public DeadlineTask(String description, LocalDate byDate) {
        super("D", description);
        this.byDate = byDate;
    }

    /**
     * Gets the description of the task along with the deadline date.
     *
     * @return The formatted description with the deadline date.
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " (by: " + formatDate(byDate) + ")";
    }

    /**
     * Formats the given LocalDate object into a string.
     *
     * @param date The LocalDate object to format.
     * @return The formatted date string.
     */
    private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /**
     * Converts the DeadlineTask object to a string in save format.
     *
     * @return The string representation of the task in save format.
     */
    @Override
    public String toSaveFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + formatDate(byDate);
    }
}
