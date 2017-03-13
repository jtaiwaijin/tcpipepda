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
    private Button[] first;
    private Button deposit, withdraw, print, update;
    public static void main(String[] args) {
        ChatClient c = new ChatClient();
        c.doConnect();
    }    
    public ChatClient() {
        frame = new MainGUI();
        first = frame.getButton();
        sFrame = frame.getStudent();
        deposit = sFrame.getDeposit();
        withdraw = sFrame.getWithdraw();
        print = sFrame.getPrint();
        update = sFrame.getUpdate();
        first[0].addActionListener(new FirstGUI());
        first[1].addActionListener(new FirstGUI());
        first[2].addActionListener(new FirstGUI());
        deposit.addActionListener(new FirstGUI());
        withdraw.addActionListener(new FirstGUI());
        print.addActionListener(new FirstGUI());
        update.addActionListener(new FirstGUI());
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
                register();
            } else if(e.getSource()==first[2]){
                serverOut.println("Client is leaving!");
                System.exit(0);
            } else if(e.getSource()==deposit){
                deposit();
            } else if(e.getSource()==withdraw){
                withdraw();
            } else if(e.getSource()==print){
                print();
            } else if(e.getSource()==update){
                update();
            }
        }
    }
    public void student(){
        serverOut.println("S");
        String input = JOptionPane.showInputDialog("Enter your Username");
        serverOut.println(input);
        input = JOptionPane.showInputDialog("Enter your PIN");
        serverOut.println(input);
    }
    public void register(){
        serverOut.println("R");
        String input = JOptionPane.showInputDialog("Enter your Username");
        serverOut.println(input);
        input = JOptionPane.showInputDialog("Enter your Pin");
        serverOut.println(input);
        input = JOptionPane.showInputDialog("Enter your Gender");
        serverOut.println(input);
        input = JOptionPane.showInputDialog("Enter your Opening Balance");
        serverOut.println(input);
    }
    public void deposit(){
        serverOut.println("D");
        String input = JOptionPane.showInputDialog("Enter Deposit Amount");
        serverOut.println(input);
    }
    public void withdraw(){
        serverOut.println("W");
        String input = JOptionPane.showInputDialog("Enter Withdrawal Amount");
        serverOut.println(input);
    }
    public void print(){
        serverOut.println("P");
    }
    public void update(){
        serverOut.println("U");
        String input = JOptionPane.showInputDialog("Enter NEW Username");
        serverOut.println(input);
        input = JOptionPane.showInputDialog("Enter NEW Pin");
        serverOut.println(input);
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
                        } else if(nextLine.equals("Wrong Password!")){
                            JOptionPane.showMessageDialog(null,"Wrong Password!");
                        } else if(nextLine.equals("Transaction Successful!")){
                            JOptionPane.showMessageDialog(null,serverIn.readLine());
                        } else if(nextLine.equals("Check Balance!")){
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

