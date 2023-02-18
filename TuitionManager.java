import java.util.Scanner;
/**
 * TuitionManager.java
 * Reads in commands from the user and executes them. Allows the user to add students, enroll students, drop students,
 * award scholarships, and display the tuition of students.
 * @Author: Alex Virilli, Ryan Elizondo-Fallas
 *
 * NOT finished just the skeleton
 */
public class TuitionManager {
    Scanner sc = new Scanner(System.in);
    void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Tuition Manager is running...");
        while (true) {
            String command = sc.nextLine(); String[] line = command.split("\\s+");
            if(line.length==0) continue;
            else if(line[0].trim().length()==0) continue;
            if(line[0].equals("LS")){ //load student roster from external file
            } else if(line[0].equals("AR") || line[0].equals("AN") || line[0].equals("AT") || line[0].equals("AI")){ //Adding students
            } else if (line[0].equals("E")){ //enroll a student with the number of credits
            } else if (line[0].equals("D")){ //drop a student from enrollment list
            } else if(line[0].equals("S")){ //award a scholarship
            } else if(line[0].equals("PE")){ //display current enrollment list
            } else if(line[0].equals("PT")){ //display the tuition based on credits enrolled
            } else if(line[0].equals("SE")){ //display students with 120 credits+
            } else if(line[0].equals("Q")){ /*terminate run*/ System.out.println("Tuition Manager terminated."); break;
            } else { System.out.println(line[0] + " is an invalid command!");}
        }
    }
}