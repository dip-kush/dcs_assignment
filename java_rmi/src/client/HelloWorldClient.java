package client;

import java.rmi.Naming;

import server.HelloWorld;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.*;
public class HelloWorldClient {

    public static void main(String[] args) throws Exception {
        /*
         * I don't need to put ip since it is runing in localhost
         * and I don't need to put the port, since it is runing in
         * the default port 1099.
         */
    	int id = 5;
    	//String s = "deepak";
    	String fn,ln,phoneno,email,city,state;
    	
    	BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
    	
    	
        HelloWorld obj = (HelloWorld)Naming.lookup("HelloWorld");
       
       while(1==1){       
        System.out.println("Enter the Choice:\n 1. Find 2. Insert 3. Delete");
        int ch=Integer.parseInt(inp.readLine());
        switch (ch) {
        case 1:
        {    
        System.out.println("Enter the email id of the person");
        email = inp.readLine();
        System.out.println(obj.getRecord(email));

        break;
        }
        case 2: {
       System.out.println("FirstName");
        fn=inp.readLine();
        
        System.out.println("Lastname");
        ln=inp.readLine();
        System.out.println("PhoneNo");
        phoneno=inp.readLine();
        
        System.out.println("email");
        email=inp.readLine();
        System.out.println("city");
        city=inp.readLine();
        System.out.println("state");
        state=inp.readLine();
        
        System.out.println(fn+" "+ln+" "+phoneno+" "+email+" "+city+" "+state);
        System.out.println(obj.insertRecord(fn,ln,phoneno,email,city,state));   
        break;
        } 
    
       case 3: {  
         System.out.println("Enter the phone number to be deleted");
         phoneno=inp.readLine();
        System.out.println(obj.deleteRecord(phoneno));
          break;
       }
         }
       }
    }

}
