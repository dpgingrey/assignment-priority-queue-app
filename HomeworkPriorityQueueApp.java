import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class HomeworkPriorityQueueApp {
    //holds Homework objects sorted by due date
    private PriorityQueue<Homework> priorityQueue;

    /**
     * Comparator sorts Homework objects by due date in the priority queue.
     */
    public HomeworkPriorityQueueApp(){
        priorityQueue = new PriorityQueue<>(new Comparator<Homework>() {
            @Override
            public int compare(Homework h1, Homework h2) {
                return h1.getDueDate().compareTo(h2.getDueDate());
            }
        });
    }

    /**
     *  Makes sure priority queue is always sorted by due date.
     */
    public void resortQueue() {
        List<Homework> sortedList = new ArrayList<>(priorityQueue);

        sortedList.sort(new Comparator<Homework>() {
            @Override
            public int compare(Homework h1, Homework h2) {
                return h1.getDueDate().compareTo(h2.getDueDate());
            }
        });

        priorityQueue.clear();              //clear queue
        priorityQueue.addAll(sortedList);   //add newly sorted queue back
    }

    /**
     * Adds assignment to priority queue and sorts.
     *
     * @param homeworkName
     * @param dueDate
     * @param notes
     */
    public void addAssignment(String homeworkName, String dueDate, String notes){
        Homework homework = new Homework(homeworkName, dueDate, notes);
        priorityQueue.add(homework);
        resortQueue();
        System.out.println("\n============================================");
        System.out.println("Your assignment was added!");
        System.out.println("============================================");
        System.out.println(homework + "\n");
    }

    /**
     * Checks if param date is valid.
     *
     * @param date of Homework
     * @return true if valid, false if not valid
     */
    public boolean isValidDate(String date){
        try{
            LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            return true;
        } catch(DateTimeParseException e){
            return false;
        }
    }

    /**
     * Deletes assignment from priority queue.
     *
     * @param name of Homework
     */
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

    /**
     * Looks up assignment by name and displays it if it is in the priority queue.
     *
     * @param searchName name of Homework to lookup
     */
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

    /**
     * Displays all assignments in priority queue.
     */
    public void displayAllAssignments(){
        if(priorityQueue.isEmpty()){
            System.out.println("\nNo assignments to display.\n");
            return;
        }

        System.out.println("\n============================================");
        System.out.println("All assignments below");
        System.out.println("============================================");
        for(Homework homework : priorityQueue){
            System.out.println(homework + "\n");
        }
    }

    /**
     * Main menu of app, allows user to choose options.
     */
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

            if(choice == 1){ 
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
