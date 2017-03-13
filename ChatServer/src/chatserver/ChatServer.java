package chatserver;
import data.*;
import java.io.*;
import java.net.*;
import model.*;
import java.util.*;
public class ChatServer {
    private int port = 2000;
    private Student s = null;
    private Database source;
    private List<Student> data;
    public static void main(String[] args) {
        ChatServer cs = new ChatServer();
        cs.go();
    }
    public void go() {
        this.port = port;
        ServerSocket serverSocket = null;
        Socket communicationSocket;    
        try {
            System.out.println("Attempting to start server...");
            serverSocket = new ServerSocket(port);
        } catch (IOException e) { 
            System.out.println("Error starting server: Could not open port "+port);
            e.printStackTrace();
            System.exit(-1);
        }
        System.out.println ("Started server on port "+port);
        source = Database.getDatabase();
        data = source.readData();
        while (true) {
            try {
                communicationSocket = serverSocket.accept();
                Connection con = new Connection(communicationSocket);
                con.start();
            } catch (Exception e) { 
                System.out.println("Unable to spawn child socket.");
                e.printStackTrace();
            } 
        }
    }
    class Connection {
        private Socket communicationSocket = null;
        private OutputStreamWriter out = null;
        private BufferedReader in = null;
        public Connection(Socket s) {
            communicationSocket = s;
        }        
        public void start() {
            OutputStream socketOutput = null;
            InputStream socketInput = null;
            try {
                socketOutput = communicationSocket.getOutputStream();
                out = new OutputStreamWriter(socketOutput);
                socketInput = communicationSocket.getInputStream();
                in = new BufferedReader(new InputStreamReader(socketInput));
                String input = null;
                while((input = in.readLine()) != null) {
                    System.out.println(input);
                    sendMessage("Received: "+input+"!\n");
                    if(input.equals("S")){
                        String username = in.readLine();
                        System.out.println(username);
                        sendMessage("Received: "+username+"!\n");
                        String password = in.readLine();
                        System.out.println(password);
                        sendMessage("Received: "+password+"!\n");
                        for(int i=0; i<data.size(); i++){
                            if(username.equals(((Student)(data.get(i))).getName())){
                                s = (Student)(data.get(i));
                                break;
                            }
                        }
                        if(s!=null){
                            if(s.Login(Integer.parseInt(password))){
                                sendMessage("Welcome!\n");
                            } else{
                                sendMessage("Login Failed!\n");
                            }
                        } else{
                            sendMessage("Login Failed!\n");
                        }
                    } else if(input.equals("R")){
                        String a = in.readLine();
                        System.out.println(a);
                        sendMessage("Received: "+a+"!\n");
                        int c = Integer.parseInt(in.readLine());
                        System.out.println(c);
                        sendMessage("Received: "+c+"!\n");
                        char b = in.readLine().charAt(0);
                        System.out.println(b);
                        sendMessage("Received: "+b+"!\n");
                        double d = Double.parseDouble(in.readLine());
                        System.out.println(d);
                        sendMessage("Received: "+d+"!\n");
                        sendMessage("Register user "+a+ "("+ b+ ") with password "+c+" and opening balance of "+d+" !\n");
                        Student s = new Student(a, b, c, d);
                        data.add(s);
                    } else if(input.equals("D")){
                        double d = Double.parseDouble(in.readLine());
                        System.out.println(d);
                        sendMessage("Received: "+d+"!\n");
                        s.deposit(d);
                        sendMessage("Transaction Successful!\n");
                        sendMessage("New balance is RM"+s.getBalance()+"!\n");
                    } else if(input.equals("W")){
                        double d = Double.parseDouble(in.readLine());
                        System.out.println(d);
                        sendMessage("Received: "+d+"!\n");
                        s.withdraw(d);
                        sendMessage("Transaction Successful!\n");
                        sendMessage("New balance is RM"+s.getBalance()+"!\n");
                    } else if(input.equals("P")){
                        sendMessage("Check Balance!\n");
                        sendMessage(s.printing() +"\n");
                    } else if(input.equals("U")){
                        String a = in.readLine();
                        System.out.println(a);
                        sendMessage("Received: "+a+"!\n");
                        int b = Integer.parseInt(in.readLine());
                        System.out.println(b);
                        sendMessage("Received: "+b+"!\n");
                        s.update(a, b);
                        sendMessage("Transaction Successful!\n");
                        sendMessage("New Username is ("+s.getName()+") with Password ("+s.getPin()+")!\n");
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if(in != null) in.close();
                    if(out != null) out.close();
                    communicationSocket.close();
                    source.writeData();
                    System.out.println("DONE!");            
                } catch(Exception e) { 
                    e.printStackTrace(); 
                }
            }
        }
        public void sendMessage(String message) {
            try {
                out.write(message);
                out.flush();
            } catch(Exception e) { 
                e.printStackTrace(); 
            }
        }
    }
}

