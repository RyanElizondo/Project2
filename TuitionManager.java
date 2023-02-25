import java.util.Scanner;
/**
 * RosterManager is a class that manages a roster of students. It allows the user to add, remove, and print students.
 * It also allows the user to print students by school, major, and standing.
 * @Author: Alex Virilli, Ryan Elizondo-Fallas
 */
public class TuitionManager {

    private boolean isInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException n) {
            return false;
        }
    }

    private boolean isValidToAdd(String[] line) {
        Date date = new Date(line[3]);
        if (date.isValid()) {
            if (date.isSixteen()) {
                if (Major.CS.getMajorIndex(line[4]) != -1) {
                    if (isInt(line[5])) {
                        if (Integer.parseInt(line[5]) >= 0) {
                            return true;
                        } else {
                            System.out.println("Credits completed invalid: cannot be negative!");
                        }
                    } else {
                        System.out.println("Credits completed invalid: not an integer!");
                    }
                } else {
                    System.out.println("Major code invalid: " + line[4]);
                }
            } else {
                System.out.println("DOB invalid: " + date.toString() + " younger than 16 years old.");
            }
        } else {
            System.out.println("DOB invalid: " + date.toString() + " not a valid calendar date!");
        }
        return false;
    }

    private boolean changeStudentMajor(String line[], Roster roster, Major majors[]){
        int majorIndex = Major.CS.getMajorIndex(line[4]);
        if(majorIndex==-1){
            System.out.println("Major code invalid: " + line[4]);
            return false;
        }
        Date date = new Date(line[3]);
        Profile profile = new Profile(line[2],line[1],date);
        Major major = majors[majorIndex];
        NonResident finder = new NonResident(profile, Major.CS, 20);
        if(!roster.contains(finder)){
            System.out.println(profile.toString() + " is not in the roster.");
            return false;
        }
        Student student = roster.getStudent(finder);
        student.setMajor(major);
        System.out.println(profile.toString() + " major changed to " + major.toString());
        return true;
    }

    private boolean removeStudent(String line[]){
        Date date = new Date(line[3]);
        Profile profile = new Profile(line[2],line[1],date);
        NonResident pointer = new NonResident(profile, Major.CS, 0);
        if(roster.remove(pointer)){
            System.out.println(profile.toString() + " removed from the roster.");
            return true;
        } else {
            System.out.println(profile.toString() + " is not in roster.");
            return false;
        }
    }

    private Resident makeResident(Profile profile, Major major, int creditCompleted, int scholarship) {
        Resident resident = new Resident(profile, major, creditCompleted, scholarship);
        return resident;
    }

    private NonResident makeNonResident(Profile profile, Major major, int creditCompleted) {
        NonResident nonResident = new NonResident(profile, major, creditCompleted);
        return nonResident;
    }

    private International makeInternational(Profile profile, Major major, int creditCompleted, boolean isStudyAbroad) {
        International international = new International(profile, major, creditCompleted, isStudyAbroad);
        return international;
    }

    private TriState makeTriState(Profile profile, Major major, int creditCompleted, String state) {
        TriState triState = new TriState(profile, major, creditCompleted, state);
        return triState;
    }

    private boolean addResident(String line[]){
        if(!isValidToAdd(line)) return false;

        Major pointer = Major.CS;
        int creditComp = Integer.parseInt(line[5]);
        Date date = new Date(line[3]);
        Profile profile = new Profile(line[2],line[1],date);
        Resident resident = makeResident(profile,majors[pointer.getMajorIndex(line[4])],creditComp,0);
        if(!roster.contains(resident)){
            roster.add(resident);
            System.out.println(profile.toString() + " added to the roster.");
            return true;
        } else {
            System.out.println(profile.toString() + " is already in the roster.");
            return false;
        }
    }

    private boolean addNonResident(String line[]){
        if(!isValidToAdd(line)) return false;

        Major pointer = Major.CS;
        int creditComp = Integer.parseInt(line[5]);
        Date date = new Date(line[3]);
        Profile profile = new Profile(line[2],line[1],date);
        NonResident nonResident = makeNonResident(profile,majors[pointer.getMajorIndex(line[4])],creditComp);
        if(!roster.contains(nonResident)){
            roster.add(nonResident);
            System.out.println(profile.toString() + " added to the roster.");
            return true;
        } else {
            System.out.println(profile.toString() + " is already in the roster.");
            return false;
        }
    }

    private boolean addTriState(String line[]) {
        if(!isValidToAdd(line)) return false;

        Major pointer = Major.CS;
        int creditComp = Integer.parseInt(line[5]);
        String state = line[6];
        Date date = new Date(line[3]);
        Profile profile = new Profile(line[2],line[1],date);
        TriState triState = makeTriState(profile,majors[pointer.getMajorIndex(line[4])],creditComp,state);
        if(!roster.contains(triState)) {
            roster.add(triState);
            System.out.println(profile.toString() + " added to the roster.");
            return true;
        } else {
            System.out.println(profile.toString() + " is already in the roster.");
            return false;
        }
    }

    private boolean addInternational(String line[]){
        if(!isValidToAdd(line)) return false;

        boolean isStudyAbroad = false;
        if(line.length == 6){
            isStudyAbroad = false;
        } else {
            if(line[6].equalsIgnoreCase("false")) isStudyAbroad = false;
            if(line[6].equalsIgnoreCase("true")) isStudyAbroad = true;
        }
        Major pointer = Major.CS;
        int creditComp = Integer.parseInt(line[5]);
        Date date = new Date(line[3]);
        Profile profile = new Profile(line[2],line[1],date);
        International international = makeInternational(profile,majors[pointer.getMajorIndex(line[4])],creditComp,isStudyAbroad);
        if(!roster.contains(international)){
            roster.add(international);
            System.out.println(profile.toString() + " added to the roster.");
            return true;
        } else {
            System.out.println(profile.toString() + " is already in the roster.");
            return false;
        }
    }

    private boolean giveScholarship(String line[]){
        Date date = new Date(line[3]);
        Profile profile = new Profile(line[2],line[1],date);
        NonResident finder = new NonResident(profile, Major.CS, 20);
        if(!roster.contains(finder)){
            System.out.println(profile.toString() + " is not in the roster.");
            return false;
        }
        Student student = roster.getStudent(finder);
        if((!(student instanceof Resident))){
            System.out.println(profile.toString() + " is not eligible for a schlarship.");
            return false;
        }
        int scholarship = Integer.parseInt(line[4]);
        ((Resident) student).setScholarship(scholarship);
        System.out.println(profile.toString() + " awarded $" + scholarship);
        return true;
    }

    private boolean enroll(String line[]){
        EnrollStudent enrollStudent = new EnrollStudent(new Profile(line[2], line[1], new Date(line[3])),Integer.parseInt(line[4]));
        enrollment.add(enrollStudent);
        System.out.println(enrollStudent.toString() + " enrolled");
        return true;
    }

    private boolean drop(String line[]){
        EnrollStudent enrollStudent = new EnrollStudent(new Profile(line[2], line[1], new Date(line[3])),Integer.parseInt(line[4]));
        enrollment.remove(enrollStudent);
        System.out.println(enrollStudent.toString() + " dropped from enrollment");
        return true;
    }

    Scanner sc = new Scanner(System.in);
    Roster roster = new Roster();
    Enrollment enrollment = new Enrollment();
    EnrollStudent enrollStudent = new EnrollStudent();
    Major[] majors = Major.values();

    void run() {
        System.out.println("Tuition Manager is running...");
        while (true) {
            String command = sc.nextLine(); String[] line = command.split("\\s+");
            if (line.length == 0) continue; else if (line[0].trim().length() == 0) continue;
            if (line[0].equals("AR")) { /*add a new resident student to the roster*/addResident(line);
            } else if(line[0].equals("AN")) { /*add a new nonresident student to the roster*/addNonResident(line);
            } else if(line[0].equals("AT")) { /*add a new tristate student to the roster*/addTriState(line);
            } else if(line[0].equals("AI")) { /*add a new international student to the roster*/addInternational(line);
            } else if(line[0].equals("S")) { /*award student a scholarship*/ giveScholarship(line);
            } else if(line[0].equals("E")) { /*enroll a student*/ enroll(line);
            } else if(line[0].equals("D")) { /*drop a student from enrollment list*/ drop(line);
            } else if(line[0].equals("PE")) { /*display current enrollment list*/enrollment.print();
            } else if(line[0].equals("PT")) { //display tuition due based on credits enrolled with correct format

            } else if(line[0].equals("SE")) { /*semester end*/roster.printCompleted();
            } else if (line[0].equals("R")) { /*remove a student*/ removeStudent(line);
            } else if (line[0].equals("P")) { /*display roster sorted by last name, first name, and DOB*/ roster.print();
            } else if (line[0].equals("PS")) { /*display roster sorted by standing*/roster.printByStanding();
            } else if (line[0].equals("PC")) { /*display roster sorted by school and major*/roster.printBySchoolMajor();
            } else if (line[0].equals("L")) { /*list student in a specified school*/roster.printSchool(line[1]);
            } else if (line[0].equals("C")) { /*change a students major */ changeStudentMajor(line,roster,majors);
            } else if (line[0].equals("Q")) { /*terminate run*/System.out.println("Tuition Manager terminated."); break;
            } else { System.out.println(line[0] + " is an invalid command!");
            }
        }
    }
}
