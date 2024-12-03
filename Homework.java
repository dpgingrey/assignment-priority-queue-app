import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Homework {
    String assignmentName;
    LocalDate dueDate;
    String assignmentNotes;

    public Homework(String name, String date, String notes){
        assignmentName = name;
        assignmentNotes = notes.isEmpty() ? "No notes" : notes;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        dueDate = LocalDate.parse(date, formatter);

    }

    @Override
    public String toString(){
        return "Assignment: " + assignmentName +
                "\nDue date: " + dueDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) +
                "\nNotes: " + assignmentNotes;
    }
}
