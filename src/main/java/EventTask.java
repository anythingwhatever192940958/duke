import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    private LocalDate fromDate;
    private LocalDate toDate;
    private boolean isOnce;

    public EventTask(String description, LocalDate fromDate, LocalDate toDate, boolean isOnce) {
        super("E", description);
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.isOnce = isOnce;
    }

    @Override
    public String getDescription() {
        String onceInfo = isOnce ? " (once within period)" : "";
        return super.getDescription() + " (from: " + formatDate(fromDate) + " to: " + formatDate(toDate) + ")" + onceInfo;
    }

    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedFromDate = fromDate.format(formatter);
        String formattedToDate = toDate.format(formatter);
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + formattedFromDate + " | " + formattedToDate + " | " + (isOnce ? "once" : "");
    }

    private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}