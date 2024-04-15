import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start date, end date, and optional "once within period" flag.
 * Extends the abstract Task class.
 */
public class EventTask extends Task {
    private LocalDate fromDate;
    private LocalDate toDate;
    private boolean isOnce;

    /**
     * Constructs an EventTask object with the given description, start date, end date, and "once within period" flag.
     *
     * @param description The description of the event task.
     * @param fromDate    The start date of the event task.
     * @param toDate      The end date of the event task.
     * @param isOnce      True if the event occurs only once within the period, false otherwise.
     */
    public EventTask(String description, LocalDate fromDate, LocalDate toDate, boolean isOnce) {
        super("E", description);
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.isOnce = isOnce;
    }

    /**
     * Gets the description of the event task along with its start and end dates.
     *
     * @return The formatted description with the start and end dates, and "once within period" info if applicable.
     */
    @Override
    public String getDescription() {
        String onceInfo = isOnce ? " (once within period)" : "";
        return super.getDescription() + " (from: " + formatDate(fromDate) + " to: " + formatDate(toDate) + ")" + onceInfo;
    }

    /**
     * Converts the EventTask object to a string in save format.
     *
     * @return The string representation of the event task in save format.
     */
    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedFromDate = fromDate.format(formatter);
        String formattedToDate = toDate.format(formatter);
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + formattedFromDate + " | " + formattedToDate + " | " + (isOnce ? "once" : "");
    }

    /**
     * Formats the given LocalDate object into a string with "dd-MM-yyyy" format.
     *
     * @param date The LocalDate object to format.
     * @return The formatted date string.
     */
    private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
