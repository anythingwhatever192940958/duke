import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private LocalDate byDate;

    public DeadlineTask(String description, LocalDate byDate) {
        super("D", description);
        this.byDate = byDate;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (by: " + formatDate(byDate) + ")";
    }

    private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @Override
    public String toSaveFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + formatDate(byDate);
    }
}
