import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {

    public ArrayList<Double> grades = new ArrayList<>();

    public void addGrade(double grade) {
        grades.add(grade);
    }

    public double calculateAverage() {
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return grades.size() > 0 ? sum / grades.size() : 0;
    }

    public double findHighest() {
        double highest = Double.MIN_VALUE;
        for (double grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }
        return highest;
    }

    public double findLowest() {
        double lowest = Double.MAX_VALUE;
        for (double grade : grades) {
            if (grade < lowest) {
                lowest = grade;
            }
        }
        return lowest;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentGradeTracker tracker = new StudentGradeTracker();

        System.out.println("Enter student grades. Type 'done' to finish:");

        while (true) {
            System.out.print("Enter grade: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            try {
                double grade = Double.parseDouble(input);
                if (grade < 0 || grade > 100) {
                    System.out.println("Please enter a valid grade between 0 and 100.");
                } else {
                    tracker.addGrade(grade);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number or 'done' to finish.");
            }
        }

        if (tracker.grades.size() > 0) {
            System.out.println("Average grade: " + tracker.calculateAverage());
            System.out.println("Highest grade: " + tracker.findHighest());
            System.out.println("Lowest grade: " + tracker.findLowest());
        } else {
            System.out.println("No grades entered.");
        }

        scanner.close();
    }
}
