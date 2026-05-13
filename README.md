# Student Result Management System

A console-based Java application to manage student marks, generate report cards, and display class statistics.

## Features
- Add students with unique roll numbers
- Enter subject-wise marks with validation
- Generate formatted report cards with grade and pass/fail status
- View all students in a summary table
- Class statistics: topper, average, pass percentage

## How to Run

```bash
cd src
javac *.java
java Main
```

## Concepts Used
- Object-Oriented Programming (Classes, Objects, Encapsulation)
- ArrayList for dynamic data storage
- Input validation and exception handling
- Static constants and methods
- Console formatting with printf

## Project Structure
```
StudentResultSystem/
└── src/
    ├── Main.java           # Entry point, menu, user input
    ├── Student.java        # Student model with grade logic
    └── ResultManager.java  # Manages collection of students
```

## Sample Output
```
╔══════════════════════════════════════════╗
║             STUDENT REPORT CARD          ║
╠══════════════════════════════════════════╣
║  Name       : Nishant Raj               ║
║  Roll No.   : 101                       ║
╠══════════════════════════════════════════╣
║  Subject          Marks      Status     ║
╠══════════════════════════════════════════╣
║  Mathematics      85         Pass       ║
║  Physics          78         Pass       ║
║  Chemistry        91         Pass       ║
║  English          74         Pass       ║
║  CS               88         Pass       ║
╠══════════════════════════════════════════╣
║  Total Marks : 416                      ║
║  Percentage  : 83.20%                   ║
║  Grade       : A                        ║
║  Result      : PASS                     ║
╚══════════════════════════════════════════╝
```
