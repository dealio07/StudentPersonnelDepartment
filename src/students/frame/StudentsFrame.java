package students.frame;

import students.logic.Group;
import students.logic.ManagementSystem;
import students.logic.Student;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Vector;

public class StudentsFrame extends JFrame {

    ManagementSystem ms = ManagementSystem.getInstance();
    private JList grpList;
    private JList stdList;
    private JSpinner spYear;

    public StudentsFrame() {
        // Setting up layout for al client side of the form
        getContentPane().setLayout(new BorderLayout());

        // Setting up the top panel, where data input field will be
        JPanel top = new JPanel();
        // Setting up layout for top panel
        top.setLayout(new FlowLayout(FlowLayout.LEFT));

        top.add(new JLabel("Year:"));
        // Making spinner-field
        SpinnerModel sm = new SpinnerNumberModel(2006, 1900, 2100, 1);
        spYear = new JSpinner(sm);
        top.add(spYear);

        // Setting up the bottom panel and it's layout
        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());

        // Setting up left panel for group list output
        JPanel left = new JPanel();
        // Adding layout and border around panel
        left.setLayout(new BorderLayout());
        left.setBorder(new BevelBorder(BevelBorder.RAISED));

        // Getting list of groups
        Vector<Group> gr = new Vector<Group>(ms.getGroups());
        left.add(new JLabel("Groups:"), BorderLayout.NORTH);

        // Creating virtual list and adding it to the scrolling panel
        grpList = new JList(gr);
        left.add(new JScrollPane(grpList), BorderLayout.CENTER);

        // Setting up right panel for students output
        JPanel right = new JPanel();
        right.setLayout(new BorderLayout());
        right.setBorder(new BevelBorder(BevelBorder.RAISED));

        // Getting list of students
        Vector<Student> st = new Vector<Student>(ms.getAllStudents());
        right.add(new JLabel("Students:"), BorderLayout.NORTH);
        // Creating virtual list and adding it to the scrolling panel
        stdList = new JList(st);
        right.add(new JScrollPane(stdList), BorderLayout.CENTER);

        // Adding lists with groups and students to the bottom panel
        bottom.add(left, BorderLayout.WEST);
        bottom.add(right, BorderLayout.CENTER);

        // Adding top and bottom panels to the form
        getContentPane().add(top, BorderLayout.NORTH);
        getContentPane().add(bottom, BorderLayout.CENTER);

        // Setting up form bounds
        setBounds(100, 100, 600, 400);
    }

    public static void main(String[] args) {
        // Launching frame from event-dispatching thread
        SwingUtilities.invokeLater(() -> {
            StudentsFrame sf = new StudentsFrame();
            sf.setDefaultCloseOperation(EXIT_ON_CLOSE);
            sf.setVisible(true);
        });
    }

}
