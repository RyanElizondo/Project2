import org.junit.Test;

import static org.junit.Assert.*;

public class RosterTest {
    Roster roster = new Roster();



    @Test
    public void addInternationalStudent() {
        International student = new International(new Profile("John", "Doe", new Date("1/1/2003")), Major.CS, 12, true);
        assertTrue(roster.add(student));
    }

    @Test
    public void addTriStateStudent() {
        TriState student = new TriState(new Profile("John", "Doe", new Date("1/1/2003")), Major.CS, 12,  "NY");
        assertTrue(roster.add(student));
    }

    @Test
    public void removeInternationalStudent() {
        International student = new International(new Profile("John", "Doe", new Date("1/1/2003")), Major.CS, 12, true);
        roster.add(student);
        assertTrue(roster.remove(student));
    }

    @Test
    public void removeTriStateStudent() {
        TriState student = new TriState(new Profile("John", "Doe", new Date("1/1/2003")), Major.CS, 12,  "NY");
        roster.add(student);
        assertTrue(roster.remove(student));
    }

    @Test
    public void addTriStateFalse() {
        TriState student = new TriState(new Profile("John", "Doe", new Date("1/1/2003")), Major.CS, 12,  "NY");
        roster.add(student);
        assertFalse(roster.add(student));
    }
    @Test
    public void addInternationalFalse() {
        International student = new International(new Profile("John", "Doe", new Date("1/1/2003")), Major.CS, 12, true);
        roster.add(student);
        assertFalse(roster.add(student));
    }

    @Test
    public void removeTriStateFalse() {
        TriState student = new TriState(new Profile("John", "Doe", new Date("1/1/2003")), Major.CS, 12,  "NY");
        assertFalse(roster.remove(student));
    }

    @Test
    public void removeInternationalFalse() {
        International student = new International(new Profile("John", "Doe", new Date("1/1/2003")), Major.CS, 12, true);
        assertFalse(roster.remove(student));
    }
}