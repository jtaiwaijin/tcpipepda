package model;
import java.io.Serializable;
public class Login implements Serializable {
    private int pin;
    public Login(int x){
        pin = x;
    }
    public boolean verifyPin(int x){
        if(pin==x){
            return true;
        } 
        return false;
    }
    public int get(){
        return pin;
    }
}
