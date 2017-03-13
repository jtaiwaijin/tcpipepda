package model;
import java.io.Serializable;
public class Student implements Serializable {
    private String name;
    private char gender;
    private Login password;
    private Account bank;
    public Student(String x1, char x2, int y, double z){
        name = x1;
        gender = x2;
        password = new Login(y);
        bank = new Account(z);
    }
    public boolean Login(int x){
        if(password.verifyPin(x)){
            return true;
        }
        return false;
    }
    public int getPin(){
        return password.get();
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
    public double getBalance(){
        return bank.getBalance();
    }
}
