package model;
import java.io.Serializable;
public class Account implements Serializable {
    private double balance;
    public Account(double x){
        balance = x;
    }
    public void deposit(double x){
        balance = balance + x;
    }
    public void withdraw(double x){
        balance = balance - x;
    }
    public double getBalance(){
        return balance;
    }
}
