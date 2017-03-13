package data;
import java.io.*;
import java.util.*;
import model.*;
public class Database {
    private static Database source = new Database();
    private Database(){}
    private List<Student> data = new ArrayList<Student>();
    public static Database getDatabase(){
        return source;
    }
    public List readData(){
        Student s;
        try{
            ObjectInputStream objectInputFile = new ObjectInputStream(new FileInputStream("Student.dat"));
            data = (List)(objectInputFile.readObject());
            System.out.println("Reading data!");
            Iterator student = data.iterator();
            while(student.hasNext()){
                s = (Student)student.next();
                System.out.println(s.getName()+"\t"+s.getPin()+"\t"+"\t"+s.getBalance());
            }
            objectInputFile.close();
        } catch(Exception e){
            System.out.println("Input error!");
            /*Student backup = new Student ("James", 'M', 12345, 100.00);
            data.add(backup);*/
        }      
        return data;
    }
    public void writeData(){
        try{
            ObjectOutputStream objectOutputFile = new ObjectOutputStream(new FileOutputStream("Student.dat"));
            objectOutputFile.writeObject(data);
            objectOutputFile.close();
        } catch(Exception e){
            System.out.println("Output error!");
        }        
    }
}
