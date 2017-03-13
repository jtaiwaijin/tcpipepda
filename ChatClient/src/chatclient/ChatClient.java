package chatclient;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import view.*;
public class ChatClient {
    private Socket connection = null;
    private BufferedReader serverIn = null;
    private PrintStream serverOut = null;
    private MainGUI frame;
    private StudentGUI sFrame;
    private AdminGUI aFrame;
    private Button[] first;
    private Button admin, deposit, withdraw;
    public static void main(String[] args) {
        ChatClient c = new ChatClient();
        c.doConnect();
    }    
    public ChatClient() {
        frame = new MainGUI();
        first = frame.getButton();
        sFrame = frame.getStudent();
        aFrame = frame.getAdmin();
        admin = aFrame.getButton();
        deposit = sFrame.getDeposit();
        withdraw = sFrame.getWithdraw();
        first[0].addActionListener(new FirstGUI());
        first[1].addActionListener(new FirstGUI());
        first[2].addActionListener(new FirstGUI());
        admin.addActionListener(new FirstGUI());
        deposit.addActionListener(new FirstGUI());
        withdraw.addActionListener(new FirstGUI());        
    }
    private void doConnect() {
        try {
            connection = new Socket("127.0.0.1",2000);
            serverIn = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            serverOut = new PrintStream(connection.getOutputStream());
            RemoteReader t = new RemoteReader();
            t.start();
        } catch(Exception e) {
            System.out.println("Unable to connect to server!");
            e.printStackTrace();
        }
    }
    private class FirstGUI implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==first[0]){
                student();   
            } else if(e.getSource()==first[1]){
                serverOut.println("A");
                String password = JOptionPane.showInputDialog("Enter your password:");
                serverOut.println(password);
            } else if(e.getSource()==first[2]){
                serverOut.println("Client is leaving!");
                System.exit(0);
            } else if(e.getSource()==admin){
                admin();
            } else if(e.getSource()==deposit){
                depsoit();
            } else if(e.getSource()==withdraw){
                withdraw();
            }
        }
    }
    public void student(){
        serverOut.println("S");
        String input = JOptionPane.showInputDialog("Enter your username:");
        serverOut.println(input);
        input = JOptionPane.showInputDialog("Enter your pin:");
        serverOut.println(input);
    }
    public void depsoit(){
        serverOut.println("D");
        String input = JOptionPane.showInputDialog("Enter deposit amount:");
        serverOut.println(input);
    }
    public void withdraw(){
        serverOut.println("W");
        String input = JOptionPane.showInputDialog("Enter withdraw amount:");
        serverOut.println(input);
    }
    public void admin(){
        serverOut.println("C");
        String a = JOptionPane.showInputDialog("Enter student name:");
        serverOut.println(a);
        String b = JOptionPane.showInputDialog("Enter student gender:");
        serverOut.println(b);
        String c = JOptionPane.showInputDialog("Enter student pin:");
        serverOut.println(c);
        String d = JOptionPane.showInputDialog("Enter student balance:");
        serverOut.println(d);        
    }    
    private class RemoteReader {
        private boolean keepListening = true;
        public void start() {
            while(keepListening == true) {
                try {
                    String nextLine = serverIn.readLine();
                    if(nextLine!=null){
                        System.out.println(nextLine);
                        if(nextLine.equals("Welcome!")){
                            sFrame.setVisible(true);
                            frame.setVisible(false);
                        } else if(nextLine.equals("Login Failed!")){
                            JOptionPane.showMessageDialog(null,"Login Failed!");
                        } else if(nextLine.equals("Welcome Admin!")){
                            aFrame.setVisible(true);
                            frame.setVisible(false);
                        } else if(nextLine.equals("Wrong Password!")){
                            JOptionPane.showMessageDialog(null,"Wrong Password!");
                        } else if(nextLine.equals("Transaction Successful!")){
                            JOptionPane.showMessageDialog(null,serverIn.readLine());
                        }
                    }
                } catch(Exception e) {
                    keepListening = false;
                    System.out.println("Error while reading from server.");
                    e.printStackTrace();
                }
            }
        }
    }
}

