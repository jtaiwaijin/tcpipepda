package view;
import java.awt.*;
import javax.swing.*;
public class MainGUI extends JFrame {
    private StudentGUI g1;
    private AdminGUI g2;
    private Button[] b;
    public MainGUI(){
        b = new Button[3];
        b[0] = new Button("Student");
        b[1] = new Button("Admin");
        b[2] = new Button("Exit");
        setLayout(new FlowLayout());
        add(b[0]);
        add(b[1]);
        add(b[2]);
        setLocation(300,200);
        setSize(150,100);
        g1 = new StudentGUI();
        g2 = new AdminGUI();
        g1.setGUI(this);
        g2.setGUI(this);
        setVisible(true);
    }
    public Button[] getButton(){
        return b;
    }
    public StudentGUI getStudent(){
        return g1;
    }
    public AdminGUI getAdmin(){
        return g2;
    }
}
