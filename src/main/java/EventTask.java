import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    private LocalDate fromDate;
    private LocalDate toDate;

    public EventTask(String description, LocalDate fromDate, LocalDate toDate) {
        super("E", description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (from: " + formatDate(fromDate) + " to: " + formatDate(toDate) + ")";
    }

    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedFromDate = fromDate.format(formatter);
        String formattedToDate = toDate.format(formatter);
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + formattedFromDate + " | " + formattedToDate;
    }

    private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
