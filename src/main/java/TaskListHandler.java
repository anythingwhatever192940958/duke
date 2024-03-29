import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TaskListHandler {
    private static final String FILE_PATH = "./data/duke.csv";

    public static void saveTaskList(List<Task> taskList) {
        createNewDirectory(FILE_PATH);

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
        createNewDirectory(FILE_PATH);

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
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            // Invalid format, cannot create task
            return null;
        }

        String taskType = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        switch (taskType) {
            case "T":
                task = new TodoTask(description);
                break;
            case "D":
                if (parts.length >= 4) {
                    String by = parts[3].trim();
                    task = new DeadlineTask(description, by);
                }
                break;
            case "E":
                if (parts.length >= 4) {
                    String from = parts[3].trim();
                    String to = parts[4].trim();
                    task = new EventTask(description, from, to);
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

    private static void createNewDirectory(String filePath) {
        Path path = Paths.get(filePath).getParent();
        if (path != null && !Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                System.out.println("Error creating directory: " + e.getMessage());
            }
        }
    }
}
