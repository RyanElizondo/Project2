import java.util.Scanner;
import java.io.FileNotFoundException;
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
    Scanner sc = new Scanner(System.in); Roster roster = new Roster(); Major[] majors = Major.values(); Checker check = new Checker();
    void run() throws FileNotFoundException {
        System.out.println("Tuition Manager is running...");
        while (true) {
            String command = sc.nextLine(); String[] line = command.split("\\s+");
            if(line.length==0) continue;
            else if(line[0].trim().length()==0) continue;
            if(line[0].equals("LS")){ //load student roster from external file
                Scanner externalFile = new Scanner(new File(line[1]));
            } else if(line[0].equals("AR") || line[0].equals("AN") || line[0].equals("AT") || line[0].equals("AI")){ //Adding students
                Date date = new Date(line[3]);
                if(check.isValidToAdd(date, line)){
                    Profile profile = new Profile(line[2],line[1],date); Student student = check.makeStudent(profile,majors[Major.CS.getMajorIndex(line[4])],Integer.parseInt(line[5]));
                    if (!roster.contains(student)) { roster.add(student); System.out.println(profile.toString() + " added to the roster.");
                    } else { System.out.println(profile.toString() + " is already in the roster."); }}
            } else if (line[0].equals("E")){ //enroll a student with the number of credits
            } else if (line[0].equals("D")){ //drop a student from enrollment list
            } else if(line[0].equals("S")){ //award a scholarship
            } else if(line[0].equals("PE")){ //display current enrollment list
            } else if(line[0].equals("PT")){ //display the tuition based on credits enrolled
            } else if(line[0].equals("SE")){ /*display students with 120 credits */ roster.printCompleted();
            } else if(line[0].equals("Q")){ /*terminate run*/ System.out.println("Tuition Manager terminated."); break;
            } else { System.out.println(line[0] + " is an invalid command!");}
        }
    }
}
