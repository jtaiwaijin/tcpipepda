package model;
public class Admin {
    public static boolean Login(int y){
        if(y==12345){
            return true;
        }
        return false;
    }
    public static Student createStudent(String a, char b, int c, double d){
        return new Student(a,b,c,d);
    }
}
