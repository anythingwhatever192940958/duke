import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TaskListHandler {
    private static final String FILE_PATH = "./data/duke.csv";

    public static void saveTaskList(List<Task> taskList) {
        createNewDirectory();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : taskList) {
                writer.write(task.toSaveFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public static void loadTaskList(List<Task> taskList) {
        createNewDirectory();

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            // File doesn't exist, nothing to load
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = createTaskFromSaveFormat(line);
                if (task != null) {
                    taskList.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    private static Task createTaskFromSaveFormat(String line) {
        Task task = null;
        String[] tokenized = line.split(" \\| ");
        if (tokenized.length < 3) {
            // Invalid format, cannot create task
            return null;
        }

        String taskType = tokenized[0].trim();
        boolean isDone = tokenized[1].trim().equals("1");
        String description = tokenized[2].trim();

        switch (taskType) {
            case "T":
                task = new TodoTask(description);
                break;
            case "D":
                if (tokenized.length >= 4) {
                    String by = tokenized[3].trim();
                    LocalDate byDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    task = new DeadlineTask(description, byDate);
                }
                break;
            case "E":
                if (tokenized.length >= 5) {
                    String from = tokenized[3].trim();
                    String to = tokenized[4].trim();
                    LocalDate fromDate = LocalDate.parse(from, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    LocalDate toDate = LocalDate.parse(to, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    task = new EventTask(description, fromDate, toDate);
                }
                break;
            default:
                // Invalid task type
                break;
        }

        if (task != null) {
            if (isDone) {
                task.markAsDone();
            } else {
                task.markAsUndone();
            }
        }

        return task;
    }

    private static void createNewDirectory() {
        Path path = Paths.get(TaskListHandler.FILE_PATH).getParent();
        if (path != null && !Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                System.out.println("Error creating directory: " + e.getMessage());
            }
        }
    }
}
