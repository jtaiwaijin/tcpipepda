package view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class AdminGUI extends JFrame implements ActionListener {
    private MainGUI g;
    private Button b1, b2;
    public AdminGUI(){
        b1 = new Button("Create Student");
        b2 = new Button("Logout");
        b2.addActionListener(this);
        setLayout(new FlowLayout());
        add(b1);
        add(b2);
        setLocation(700,200);
        setSize(150,100);
    }
    public void setGUI(MainGUI x){
        g = x;
    }
    public Button getButton(){
        return b1;
    }
    public void actionPerformed(ActionEvent e){
        g.setVisible(true);
        setVisible(false);
    }
}
