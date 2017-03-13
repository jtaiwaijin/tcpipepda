package model;
import java.io.Serializable;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
public class Student implements Serializable {
    private String name;
    private char gender;
    private int password;
    private Account bank;
    private static DecimalFormat df = new DecimalFormat("#0.00");
    
    public Student(String x1, char x2, int y, double z){
        name = x1;
        gender = x2;
        password = y;
        bank = new Account(z);
    }
    public boolean Login(int x){
        int pin = x;
        if(password == pin){
            return true;
        }
        return false;
    }
    public int getPin(){
        return password;
    }
    public char getGender(){
        return gender;
    }
    public String getName(){
        return name;
    }
    public void deposit(double x){
        bank.deposit(x);
    }
    public void withdraw(double x){
        bank.withdraw(x);
    }
    public String printing(){
        return name+" ("+gender+") : Current Balance is RM"+df.format(bank.getBalance())+"!";
    }
    public void update(String x, int y){
        name = x;
        password = y;
    }
    public String getBalance(){
        return df.format(bank.getBalance());
    }
}
