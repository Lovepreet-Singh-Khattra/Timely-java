import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static UserService userService = new UserService();
    static TaskService taskService = new TaskService();
    static HabitService habitService = new HabitService();
    static DailyNoteService noteService = new DailyNoteService();

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n========== TIMELY ==========");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Enter choice: ");
            int choice = readNumber();

            switch (choice) {

                case 1:
                    userService.register();
                    break;

                case 2:
                    User user = userService.login();

                    if (user != null) {
                        userMenu(user);
                    }
                    break;

                case 3:
                    System.out.println("Thank you for using Timely!");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void userMenu(User user) {

        while (true) {

            System.out.println("\n========== TIMELY DASHBOARD ==========");
            System.out.println("Welcome, " + user.getName());

            System.out.println("\n1. Dashboard");
            System.out.println("2. Add Task");
            System.out.println("3. View All Tasks");
            System.out.println("4. Update Task");
            System.out.println("5. Delete Task");
            System.out.println("6. Mark Task as Completed");
            System.out.println("7. Add Habit");
            System.out.println("8. View Habits");
            System.out.println("9. Mark Habit Completed Today");
            System.out.println("10. Show Habits Completed Today");
            System.out.println("11. Add Daily Note");
            System.out.println("12. View Daily Notes");
            System.out.println("13. Update Daily Note");
            System.out.println("14. Delete Daily Note");
            System.out.println("15. Daily Motivation");
            System.out.println("16. View Profile");
            System.out.println("17. Update Profile");
            System.out.println("18. Logout");

            System.out.print("\nEnter choice: ");
            int choice = readNumber();

            switch (choice) {

                case 1:
                    taskService.showDashboard(user.getId());
                    break;

                case 2:
                    taskService.addTask(user.getId());
                    break;

                case 3:
                    taskService.viewTasks(user.getId());
                    break;

                case 4:
                    taskService.updateTask(user.getId());
                    break;

                case 5:
                    taskService.deleteTask(user.getId());
                    break;

                case 6:
                    taskService.markCompleted(user.getId());
                    break;

                case 7:
                    habitService.addHabit(user.getId());
                    break;

                case 8:
                    habitService.viewHabits(user.getId());
                    break;

                case 9:
                    habitService.markCompletedToday(user.getId());
                    break;

                case 10:
                    habitService.showCompletedToday(user.getId());
                    break;

                case 11:
                    noteService.addNote(user.getId());
                    break;

                case 12:
                    noteService.viewNotes(user.getId());
                    break;

                case 13:
                    noteService.updateNote(user.getId());
                    break;

                case 14:
                    noteService.deleteNote(user.getId());
                    break;

                case 15:
                    showMotivation();
                    break;

                case 16:
                    userService.viewProfile(user);
                    break;

                case 17:
                    userService.updateProfile(user);
                    break;

                case 18:
                    System.out.println("Logged out successfully!");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void showMotivation() {

        String[] quotes = {
            "Small steps every day lead to big results.",
            "Your future depends on what you do today.",
            "Stay consistent and keep improving.",
            "Do something today that your future self will thank you for.",
            "Success starts with self-discipline.",
            "Progress is better than perfection.",
            "Every day is a new opportunity to improve.",
            "Focus on your goals, not your distractions.",
            "Great things take time. Keep going.",
            "Believe in yourself and keep learning."
        };

        int index = (int) (Math.random() * quotes.length);

        System.out.println("\n----- DAILY MOTIVATION -----");
        System.out.println(quotes[index]);
    }

    public static int readNumber() {

        while (true) {

            try {
                return Integer.parseInt(sc.nextLine());

            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}