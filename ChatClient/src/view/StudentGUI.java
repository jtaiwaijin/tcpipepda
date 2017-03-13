package view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class StudentGUI extends JFrame implements ActionListener {
    private MainGUI g;
    private Button b1, b2, b3, b4, b5;
    public StudentGUI(){
        b1 = new Button("Deposit");
        b2 = new Button("Withdraw");
        b3 = new Button("Print");
        b4 = new Button("Update");
        b5 = new Button("Logout");
        b5.addActionListener(this);
        setLayout(new FlowLayout());
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        setLocation(500,200);
        setSize(200,100);
    }
    public Button getDeposit(){
        return b1;
    }
    public Button getWithdraw(){
        return b2;
    }
    public Button getPrint(){
        return b3;
    }
    public Button getUpdate(){
        return b4;
    }
    public void setGUI(MainGUI x){
        g = x;
    }
    public void actionPerformed(ActionEvent e){
        g.setVisible(true);
        setVisible(false);
    }
}