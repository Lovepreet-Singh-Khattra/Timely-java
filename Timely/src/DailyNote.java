public class DailyNote {

    private int id;
    private String noteDate;
    private String noteText;

    public DailyNote(int id, String noteDate, String noteText) {
        this.id = id;
        this.noteDate = noteDate;
        this.noteText = noteText;
    }

    public int getId() {
        return id;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public String getNoteText() {
        return noteText;
    }
}