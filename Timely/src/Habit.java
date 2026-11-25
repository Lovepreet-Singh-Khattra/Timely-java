public class Habit {

    private int id;
    private String habitName;
    private String lastCompletedDate;

    public Habit(int id, String habitName, String lastCompletedDate) {
        this.id = id;
        this.habitName = habitName;
        this.lastCompletedDate = lastCompletedDate;
    }

    public int getId() {
        return id;
    }

    public String getHabitName() {
        return habitName;
    }

    public String getLastCompletedDate() {
        return lastCompletedDate;
    }
}