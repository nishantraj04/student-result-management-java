// ============================================================
// ResultManager.java
// Manages a collection of students — add, find, list, etc.
// CONCEPT: This is a MANAGER/SERVICE class. It does not hold
//          student data itself — it manages a list of Student objects.
//          Separation of concerns: Student knows about ONE student,
//          ResultManager knows about ALL students.
// ============================================================

import java.util.ArrayList;   // ArrayList: a resizable array — grows as you add items

public class ResultManager {

    // ArrayList of Student objects — this is our "database" for this program
    // CONCEPT: ArrayList<Student> means "a list that can only hold Student objects"
    //          This is Generics — Java's way of making type-safe collections.
    private ArrayList<Student> students;

    // Constructor — initializes the empty list
    public ResultManager() {
        students = new ArrayList<>();
    }

    // Add a new student to the list
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("  ✓ Student \"" + student.getName() + "\" added successfully.");
    }

    // Find a student by roll number
    // Returns the Student object if found, null if not found
    // CONCEPT: Returning null is a common Java pattern to signal "not found"
    public Student findByRollNumber(int rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber() == rollNumber) {
                return s;   // Found — return immediately
            }
        }
        return null;        // Loop finished without finding — return null
    }

    // Check if a roll number is already taken (to prevent duplicates)
    public boolean rollNumberExists(int rollNumber) {
        return findByRollNumber(rollNumber) != null;
        // If findByRollNumber returns non-null, the student exists → true
    }

    // Print a summary list of all students
    public void printAllStudents() {
        if (students.isEmpty()) {
            System.out.println("  No students found.");
            return;
        }

        System.out.println("\n  ╔═══════╦══════════════════════╦════════════╦═════════╦════════╗");
        System.out.println("  ║ Roll# ║ Name                 ║ Percentage ║  Grade  ║ Result ║");
        System.out.println("  ╠═══════╬══════════════════════╬════════════╬═════════╬════════╣");

        for (Student s : students) {
            System.out.printf("  ║ %-5d ║ %-20s ║ %8.2f%% ║  %-5s  ║ %-6s ║%n",
                s.getRollNumber(),
                s.getName(),
                s.getPercentage(),
                s.getGrade(),
                s.hasPassed() ? "PASS" : "FAIL");
        }

        System.out.println("  ╚═══════╩══════════════════════╩════════════╩═════════╩════════╝");
        System.out.println("  Total students: " + students.size());
    }

    // Print class statistics — topper, average, pass count
    public void printClassStats() {
        if (students.isEmpty()) {
            System.out.println("  No student data available.");
            return;
        }

        int passCount = 0;
        double totalPercentage = 0;
        Student topper = students.get(0);   // Start assuming first student is topper

        for (Student s : students) {
            if (s.hasPassed()) passCount++;
            totalPercentage += s.getPercentage();

            // Update topper if this student has higher percentage
            if (s.getPercentage() > topper.getPercentage()) {
                topper = s;
            }
        }

        double classAverage = totalPercentage / students.size();
        double passPercentage = ((double) passCount / students.size()) * 100;

        System.out.println("\n  ── Class Statistics ──────────────────────────");
        System.out.printf("  Total Students  : %d%n", students.size());
        System.out.printf("  Passed          : %d (%.1f%%)%n", passCount, passPercentage);
        System.out.printf("  Failed          : %d%n", students.size() - passCount);
        System.out.printf("  Class Average   : %.2f%%%n", classAverage);
        System.out.printf("  Class Topper    : %s (%.2f%%)%n",
                topper.getName(), topper.getPercentage());
        System.out.println("  ──────────────────────────────────────────────");
    }

    // Getter — how many students are stored
    public int getStudentCount() {
        return students.size();
    }
}
