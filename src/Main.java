// ============================================================
// Main.java — Student Result Management System
// Entry point of the program. Handles the menu and user input.
// CONCEPT: main() is where Java starts executing your program.
//          This class ONLY handles UI/input — it delegates all
//          real work to ResultManager and Student.
// ============================================================

import java.util.Scanner;  // Scanner reads user input from the keyboard

public class Main {

    // Scanner is shared across the whole class — declared at class level
    // 'static' means it belongs to the class, not any specific object
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        ResultManager manager = new ResultManager();

        // Hardcoded subjects for this demo — every student has the same subjects
        String[] subjects = {"Mathematics", "Physics", "Chemistry", "English", "CS"};

        System.out.println("\n  ╔══════════════════════════════════════════╗");
        System.out.println("  ║   STUDENT RESULT MANAGEMENT SYSTEM       ║");
        System.out.println("  ╚══════════════════════════════════════════╝");

        boolean running = true;

        // Main loop — keeps the program alive until user exits
        while (running) {
            printMenu();
            int choice = readInt("  Enter choice: ");

            // Switch-case: cleaner than multiple if-else for menu choices
            switch (choice) {
                case 1 -> addStudent(manager, subjects);
                case 2 -> enterMarks(manager);
                case 3 -> viewReport(manager);
                case 4 -> manager.printAllStudents();
                case 5 -> manager.printClassStats();
                case 6 -> {
                    System.out.println("\n  Exiting. Goodbye!");
                    running = false;
                }
                default -> System.out.println("  Invalid choice. Try again.");
            }
        }

        sc.close();  // Always close Scanner when done to free resources
    }

    // Print the main menu options
    static void printMenu() {
        System.out.println("\n  ──────────────────────────────────────────");
        System.out.println("  1. Add New Student");
        System.out.println("  2. Enter Marks for a Student");
        System.out.println("  3. View Student Report Card");
        System.out.println("  4. View All Students");
        System.out.println("  5. Class Statistics");
        System.out.println("  6. Exit");
        System.out.println("  ──────────────────────────────────────────");
    }

    // Handle adding a new student
    static void addStudent(ResultManager manager, String[] subjects) {
        System.out.println("\n  ── Add New Student ───────────────────────");

        System.out.print("  Enter student name: ");
        String name = sc.nextLine().trim();  // .trim() removes accidental spaces

        int rollNumber = readInt("  Enter roll number: ");

        // Check for duplicate roll number before adding
        if (manager.rollNumberExists(rollNumber)) {
            System.out.println("  [Error] Roll number " + rollNumber + " already exists.");
            return;  // Exit the method early
        }

        Student student = new Student(name, rollNumber, subjects);
        manager.addStudent(student);
    }

    // Handle entering marks for an existing student
    static void enterMarks(ResultManager manager) {
        System.out.println("\n  ── Enter Marks ───────────────────────────");

        if (manager.getStudentCount() == 0) {
            System.out.println("  No students added yet. Add a student first.");
            return;
        }

        int rollNumber = readInt("  Enter roll number: ");
        Student student = manager.findByRollNumber(rollNumber);

        if (student == null) {
            System.out.println("  [Error] Student not found.");
            return;
        }

        System.out.println("  Entering marks for: " + student.getName());
        System.out.println("  (Enter marks out of " + Student.MAX_MARKS + ", min to pass: " + Student.PASS_MARKS + ")");

        // These are the subjects — hardcoded to match what's in main()
        String[] subjects = {"Mathematics", "Physics", "Chemistry", "English", "CS"};

        for (int i = 0; i < subjects.length; i++) {
            int mark = readInt("  " + subjects[i] + ": ");
            student.setMark(i, mark);  // Set mark at index i
        }

        System.out.println("  ✓ Marks saved successfully.");
    }

    // Handle viewing a specific student's report card
    static void viewReport(ResultManager manager) {
        System.out.println("\n  ── View Report Card ──────────────────────");

        int rollNumber = readInt("  Enter roll number: ");
        Student student = manager.findByRollNumber(rollNumber);

        if (student == null) {
            System.out.println("  [Error] Student not found.");
            return;
        }

        student.printReportCard();
    }

    // --- UTILITY METHOD ---
    // Reads an integer from user input, handles invalid input gracefully
    // CONCEPT: Defensive programming — never trust raw user input
    static int readInt(String prompt) {
        while (true) {   // Loop until we get a valid integer
            System.out.print(prompt);
            try {
                // nextLine() reads a full line, Integer.parseInt() converts it to int
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                // Thrown when the string can't be parsed as an integer
                System.out.println("  [Error] Please enter a valid number.");
            }
        }
    }
}
