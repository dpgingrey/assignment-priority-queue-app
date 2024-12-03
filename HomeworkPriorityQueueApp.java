import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HomeworkPriorityQueueApp {
    private PriorityQueue<Homework> priorityQueue;

    public HomeworkPriorityQueueApp(){
        priorityQueue = new PriorityQueue<>(new Comparator<Homework>() {
            @Override
            public int compare(Homework h1, Homework h2) {
                return h1.dueDate.compareTo(h2.dueDate);
            }
        });
    }

    public void addAssignment(String homeworkName, String dueDate, String notes){
        Homework homework = new Homework(homeworkName, dueDate, notes);
        priorityQueue.add(homework);
        System.out.println("\n============================================");
        System.out.println("Your assignment was added!");
        System.out.println("============================================");
        System.out.println(homework + "\n");
    }

    public boolean isValidDate(String date){
        try{
            LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            return true;
        } catch(DateTimeParseException e){
            return false;
        }
    }

    public void deleteAssignment(String name){
        Homework homeworkToRemove = null;
        for(Homework homework : priorityQueue){
            if(homework.assignmentName.equals(name)){
                homeworkToRemove = homework;
                break;
            }
        }

        if(homeworkToRemove != null){
            priorityQueue.remove(homeworkToRemove);
            System.out.println("\n============================================");
            System.out.println("This assignment was removed:");
            System.out.println("============================================");
            System.out.println(homeworkToRemove + "\n");

        }else{
            System.out.println("\n" + "Assignment with the name '"+ name + "' was not found.\n");
        }
    }

    public void lookupAssignment(String searchName){
        boolean found = false;
        System.out.println("\n============================================");
        System.out.println("Searching for assignments with: '" + searchName + "'");
        System.out.println("============================================");

        for(Homework homework : priorityQueue){
            if(homework.assignmentName.toLowerCase().contains(searchName.toLowerCase())){
                System.out.println(homework + "\n");
                found = true;
            }
        }

        if(!found){
            System.out.println("No assignments including: '" + searchName + "'\n");
        }
    }

    public void displayAllAssignments(){
        if(priorityQueue.isEmpty()){
            System.out.println("\nNo assignments to display.\n");
            return;
        }

        System.out.println("\n============================================");
        System.out.println("All assignments below");
        System.out.println("============================================");
        for(Homework homework : priorityQueue){
            System.out.println(homework);
            System.out.println();
        }
    }

    public void appMain(){
        Scanner kb = new Scanner(System.in);

        System.out.println("============================================");
        System.out.println("Welcome to Dana's Assignment Organizer!");
        System.out.println("============================================\n");
        while(true){
            System.out.println("============================================");
            System.out.println("Main choice menu");
            System.out.println("============================================");
            System.out.println("1. Add Assignment:");
            System.out.println("2. Delete Assignment:");
            System.out.println("3. Lookup Assignment:");
            System.out.println("4. Display All Assignments:");
            System.out.println("5. Exit app");
            System.out.print("Choose option: ");
            int choice = kb.nextInt();
            kb.nextLine();

            if(choice == 1){   // Add assignment choice
                System.out.print("\nEnter assignment name: ");
                String assignmentName = kb.nextLine();

                String dueDate = "";
                boolean isValidDate = false;

                while(!isValidDate){
                    System.out.print("Enter due date (MM/DD/YYYY): ");
                    dueDate = kb.nextLine();
                    isValidDate = isValidDate(dueDate);

                    if(!isValidDate){
                        System.out.println("Not a valid date. Enter a valid date below.");
                    }
                }

                System.out.print("Enter assignment notes (or press Enter to skip): ");
                String notes = kb.nextLine();
                addAssignment(assignmentName, dueDate, notes);
            }else if(choice == 2){
                System.out.print("\nEnter the name of the assignment to delete: ");
                String deleteName = kb.nextLine();
                deleteAssignment(deleteName);
            }else if(choice == 3){
                System.out.print("\nEnter name of the assignment to lookup: ");
                String lookupName = kb.nextLine();
                lookupAssignment(lookupName);
            }else if(choice == 4){
                displayAllAssignments();
            }else if(choice == 5){
                System.out.println("\nExiting app.");
                kb.close();
                return;
            }else{
                System.out.println("Invalid choice option. Choose again.");
            }
        }
    }

    public static void main(String[] args){
        HomeworkPriorityQueueApp app = new HomeworkPriorityQueueApp();
        app.appMain();
    }
}
