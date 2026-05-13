// ============================================================
// Student.java
// Represents a single student with their subjects and marks.
// CONCEPT: This is a MODEL class — it holds data and logic
//          related to ONE student only. Nothing else.
// ============================================================

public class Student {

    // --- FIELDS (the data a Student holds) ---
    private String name;        // Student's full name
    private int rollNumber;     // Unique ID for each student
    private String[] subjects;  // Array of subject names
    private int[] marks;        // Marks for each subject (same index = same subject)
    private int totalSubjects;  // How many subjects this student has

    // --- CONSTANTS ---
    // 'static final' means this value belongs to the CLASS, not any object,
    // and it never changes. Think of it as a global rule.
    public static final int MAX_MARKS = 100;   // Max marks per subject
    public static final int PASS_MARKS = 35;   // Minimum to pass each subject

    // --- CONSTRUCTOR ---
    // A constructor runs when you do: new Student(...)
    // It sets up the object's initial state.
    public Student(String name, int rollNumber, String[] subjects) {
        this.name = name;                          // 'this.name' = the field, 'name' = the parameter
        this.rollNumber = rollNumber;
        this.subjects = subjects;
        this.totalSubjects = subjects.length;
        this.marks = new int[totalSubjects];       // Create an empty marks array of the right size
    }

    // --- METHODS ---

    // Set the mark for a specific subject (by index)
    public void setMark(int subjectIndex, int mark) {
        // Validate: index must be valid, mark must be between 0 and MAX_MARKS
        if (subjectIndex >= 0 && subjectIndex < totalSubjects
                && mark >= 0 && mark <= MAX_MARKS) {
            marks[subjectIndex] = mark;
        } else {
            System.out.println("  [Error] Invalid subject index or mark value.");
        }
    }

    // Calculate and return total marks across all subjects
    public int getTotalMarks() {
        int total = 0;
        for (int mark : marks) {   // Enhanced for-loop: reads as "for each mark in marks"
            total += mark;
        }
        return total;
    }

    // Calculate percentage: (total / max possible) * 100
    public double getPercentage() {
        int maxPossible = MAX_MARKS * totalSubjects;
        return ((double) getTotalMarks() / maxPossible) * 100;
        // (double) cast is needed so Java does decimal division, not integer division
    }

    // Determine letter grade based on percentage
    public String getGrade() {
        double percentage = getPercentage();
        if (percentage >= 90) return "A+";
        else if (percentage >= 80) return "A";
        else if (percentage >= 70) return "B+";
        else if (percentage >= 60) return "B";
        else if (percentage >= 50) return "C";
        else if (percentage >= 35) return "D";
        else return "F";
    }

    // Check if student has passed ALL subjects
    public boolean hasPassed() {
        for (int mark : marks) {
            if (mark < PASS_MARKS) return false;  // Fail if any subject is below pass marks
        }
        return true;
    }

    // Print the student's full result report card to the console
    public void printReportCard() {
        System.out.println("\n  ╔══════════════════════════════════════════╗");
        System.out.println("  ║             STUDENT REPORT CARD          ║");
        System.out.println("  ╠══════════════════════════════════════════╣");
        System.out.printf("  ║  Name       : %-27s║%n", name);
        System.out.printf("  ║  Roll No.   : %-27d║%n", rollNumber);
        System.out.println("  ╠══════════════════════════════════════════╣");
        System.out.printf("  ║  %-15s  %-10s  %-10s║%n", "Subject", "Marks", "Status");
        System.out.println("  ╠══════════════════════════════════════════╣");

        // Print each subject with mark and pass/fail status
        for (int i = 0; i < totalSubjects; i++) {
            String status = (marks[i] >= PASS_MARKS) ? "Pass" : "FAIL";
            System.out.printf("  ║  %-15s  %-10d  %-10s║%n", subjects[i], marks[i], status);
        }

        System.out.println("  ╠══════════════════════════════════════════╣");
        System.out.printf("  ║  Total Marks : %-26d║%n", getTotalMarks());
        System.out.printf("  ║  Percentage  : %-25.2f%%║%n", getPercentage());
        System.out.printf("  ║  Grade       : %-26s║%n", getGrade());
        System.out.printf("  ║  Result      : %-26s║%n", hasPassed() ? "PASS" : "FAIL");
        System.out.println("  ╚══════════════════════════════════════════╝");
    }

    // --- GETTERS ---
    // These give read-only access to private fields.
    // CONCEPT: Encapsulation — fields are private, access is controlled via methods.
    public String getName()     { return name; }
    public int getRollNumber()  { return rollNumber; }
}
