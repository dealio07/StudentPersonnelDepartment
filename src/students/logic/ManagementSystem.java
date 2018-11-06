package students.logic;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

public class ManagementSystem {

    private List<Group> groups;
    private Collection<Student> students;

    // Static variable for Singleton Pattern
    private static ManagementSystem instance;

    // closed constructor
    private ManagementSystem() {
        loadGroups();
        loadStudents();
    }

    // Method getInstance - checks, if static variable is initialized
    // (creates it if needed) and returns it
    public static synchronized ManagementSystem getInstance() {
        if (instance == null) {
            instance = new ManagementSystem();
        }
        return instance;
    }

    // Method creates two groups and puts them into collection for groups
    public void loadGroups() {
        // Checking, if our list already created
        if (groups == null) {
            groups = new ArrayList<Group>();
        } else {
            groups.clear();
        }
        Group g = null;

        createAndAddGroup(1, "First (I)", "Dr. Freeman", "Making dogs from people");

        createAndAddGroup(2, "Second (II)", "Prof. McMuffin", "Making people from dogs");
    }

    private void createAndAddGroup(int id, String groupName, String curator, String speciality) {
        Group g;
        g = new Group();
        g.setGroupId(id);
        g.setNameGroup(groupName);
        g.setCurator(curator);
        g.setSpeciality(speciality);
        groups.add(g);
    }

    // Method creates several students and puts them into collection
    public void loadStudents() {
        if (students == null) {
            // Using collection, which automatically sorting own elements
            students = new TreeSet<Student>();
        } else {
            students.clear();
        }

        Student s = null;
        Calendar c = Calendar.getInstance();

        // Second group
        createAndAddStudent(c, 1, "Bobby", "Jack", "Foster", 'M',
                1990, Calendar.APRIL, 20, 2, 2006);

        createAndAddStudent(c, 2, "Natale", "Brian", "Smith", 'F',
                1990, Calendar.JULY, 10, 2, 2006);

        // First Group
        createAndAddStudent(c, 3, "Peter", "Andrew", "Jones", 'M',
                1991, Calendar.APRIL, 12, 1, 2006);

        createAndAddStudent(c, 4, "Julie", "Monty", "Miller", 'F',
                1991, Calendar.AUGUST, 19, 1, 2006);
    }

    private void createAndAddStudent(Calendar c, int id, String firstName, String patronymic, String surName, char sex,
                                     int yearOfBirth, int month, int date, int groupId, int educationYear) {
        Student s = new Student();
        s.setStudentId(id);
        s.setFirstName(firstName);
        s.setPatronymic(patronymic);
        s.setSurName(surName);
        s.setSex(sex);
        c.set(yearOfBirth, month, date);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(groupId);
        s.setEducationYear(educationYear);
        students.add(s);
    }

    // Getting list of groups
    public List<Group> getGroups() {
        return groups;
    }

    // Getting list of all students
    public Collection<Student> getAllStudents() {
        return students;
    }

    // Getting list of students for concrete group
    public Collection<Student> getStudentsFromGroup(Group group, int year) {
        Collection<Student> l = new TreeSet<Student>();
        for (Student si : students) {
            if (si.getGroupId() == group.getGroupId() && si.getEducationYear() == year) {
                l.add(si);
            }
        }
        return l;
    }

    // Moving students from one group with one year of study into another group with another year
    public void moveStudentsToGroup(Group oldGroup, int oldYear, Group newGroup, int newYear) {
        for (Student si : students) {
            if (si.getGroupId() == oldGroup.getGroupId() && si.getEducationYear() == oldYear) {
                si.setGroupId(newGroup.getGroupId());
                si.setEducationYear(newYear);
            }
        }
    }

    // Remove all students from concrete group
    public void removeStudentsFromGroup(Group group, int year) {
        students.removeIf(student -> student.getGroupId() == group.getGroupId() && student.getEducationYear() == year);
    }

    // Adding student
    public void insertStudent(Student student) {
        students.add(student);
    }

    // Updating data about student
    public void updateStudent(Student student) {
        Student updStudent = null;
        for (Student si : students) {
            if (si.getStudentId() == student.getStudentId()) {
                updStudent = si;
                break;
            }
        }
        if (updStudent != null) {
            updStudent.setFirstName(student.getFirstName());
            updStudent.setPatronymic(student.getPatronymic());
            updStudent.setSurName(student.getSurName());
            updStudent.setSex(student.getSex());
            updStudent.setDateOfBirth(student.getDateOfBirth());
            updStudent.setGroupId(student.getGroupId());
            updStudent.setEducationYear(student.getEducationYear());
        }
    }

    // Removing student
    public void deleteStudent(Student student) {
        Student delStudent = null;
        for (Student si : students) {
            if (si.getStudentId() == student.getStudentId()) {
                delStudent = si;
                break;
            }
        }
        if (delStudent != null)
            students.remove(delStudent);
    }

    // Changing charset for readability
    public static void printString(Object s) {
        try {
            System.out.println(new String(s.toString().getBytes("windows-1251"), "windows-1251"));
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
    }

    public static void printString() {
        System.out.println();
    }
}