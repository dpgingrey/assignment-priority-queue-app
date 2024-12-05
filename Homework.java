import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Stores name, date, and notes for a Homework object.
 *
 * @author Dana Gingrey
 * @version 12/04/24
 */

public class Homework {
    String assignmentName;
    LocalDate dueDate;
    String assignmentNotes;

    /**
     * Constructor for Homework.
     *
     * @param name of Homework
     * @param date of Homework
     * @param notes for Homework
     */
    public Homework(String name, String date, String notes){
        assignmentName = name;
        assignmentNotes = notes.isEmpty() ? "No notes" : notes;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        dueDate = LocalDate.parse(date, formatter);
    }

    /**
     * Getter method for the due date of Homework.
     *
     * @return due date of Homework
     */
    public LocalDate getDueDate(){
        return dueDate;
    }
    
    @Override
    public String toString(){
        return "Assignment: " + assignmentName +
                "\nDue date: " + dueDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) +
                "\nNotes: " + assignmentNotes;
    }
}
