import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String FILE_PATH = "./data/duke.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();
        TaskListHandler.loadTaskList(taskList);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "\n");
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Firefly!");
        System.out.println("Type help to view all commands!");
        System.out.println("____________________________________________________________");

        while (true) {
            String userInput = scanner.nextLine().trim();
            try {
                DukeExceptionHandler.handleExceptions(userInput, taskList);
            } catch (DukeException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
                continue;
            }

            String[] tokenized = userInput.split("\\s+", 2);

            // list
            if (userInput.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                int i = 1;
                for (Task task : taskList) {
                    System.out.println(i + ".[" + task.getType() + "][" + task.getStatusIcon() + "] " + task.getDescription());
                    i++;
                }
                System.out.println("____________________________________________________________");
            }

            // todo <string>
            else if (tokenized[0].equals("todo") && tokenized.length > 1) {
                taskList.add(new TodoTask(tokenized[1]));
                taskList.get(taskList.size() - 1).printTaskInfo(taskList);
                TaskListHandler.saveTaskList(taskList);
            }

            // deadline <string> /by <dd-mm-yyyy>
            else if (tokenized[0].equals("deadline") && tokenized.length > 1 && userInput.contains("/by")) {
                String[] deadlineInfo = tokenized[1].split("\\s+/by\\s+", 2);
                LocalDate deadlineDate = LocalDate.parse(deadlineInfo[1], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                taskList.add(new DeadlineTask(deadlineInfo[0], deadlineDate));
                taskList.get(taskList.size() - 1).printTaskInfo(taskList);
                TaskListHandler.saveTaskList(taskList);
            }

            // event <string> /from <dd-mm-yyyy> /to <dd-mm-yyyy>
            else if (tokenized[0].equals("event") && tokenized.length > 1 && userInput.contains("/from") && userInput.contains("/to")) {
                String[] eventInfo = tokenized[1].split("\\s+/from\\s+", 2);
                String[] timeInfo = eventInfo[1].split("\\s+/to\\s+", 2);
                LocalDate startDate = LocalDate.parse(timeInfo[0], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                LocalDate endDate = LocalDate.parse(timeInfo[1], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                taskList.add(new EventTask(eventInfo[0], startDate, endDate));
                taskList.get(taskList.size() - 1).printTaskInfo(taskList);
                TaskListHandler.saveTaskList(taskList);
            }

            // mark <int>
            else if (tokenized[0].equals("mark") && tokenized.length > 1) {
                int markDone = Integer.parseInt(tokenized[1]) - 1;
                String taskType = taskList.get(markDone).getType();
                taskList.get(markDone).markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + taskType + "][X] " + taskList.get(markDone).getDescription());
                System.out.println("____________________________________________________________");
                TaskListHandler.saveTaskList(taskList);
            }

            // unmark <int>
            else if (tokenized[0].equals("unmark") && tokenized.length > 1) {
                int markUndone = Integer.parseInt(tokenized[1]) - 1;
                String taskType = taskList.get(markUndone).getType();
                taskList.get(markUndone).markAsUndone();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" + taskType + "][ ] " + taskList.get(markUndone).getDescription());
                System.out.println("____________________________________________________________");
                TaskListHandler.saveTaskList(taskList);
            }

            // delete <int>
            else if (tokenized[0].equals("delete") && tokenized.length > 1) {
                int taskIndexToDelete = Integer.parseInt(tokenized[1]) - 1;
                Task removedTask = taskList.remove(taskIndexToDelete);
                System.out.println("____________________________________________________________");
                System.out.println("Noted. I've removed this task:");
                System.out.println("[" + removedTask.getType() + "][" + removedTask.getStatusIcon() + "] " + removedTask.getDescription());
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
                TaskListHandler.saveTaskList(taskList);
            }

            else if (tokenized[0].equals("help")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here is a list of valid commands:");
                System.out.println("help     - Displays this page");
                System.out.println("list     - Lists all tasks");
                System.out.println("todo     - Creates a todo task");
                System.out.println("deadline - Creates a deadline task");
                System.out.println("event    - Creates an event task");
                System.out.println("mark     - Marks a task as completed");
                System.out.println("unmark   - Removes a mark from a task");
                System.out.println("delete   - Deletes a task");
                System.out.println("bye      - Ends the session");
                System.out.println("____________________________________________________________");
            }

            // bye
            else if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }
        }
        scanner.close();
    }
}
