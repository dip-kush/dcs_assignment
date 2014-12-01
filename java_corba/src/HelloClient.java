import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import HelloApp.Hello;
import HelloApp.HelloHelper;

public class HelloClient
{
  static Hello helloImpl;

  public static void main(String args[])
    {
	  String fn,ln,phoneno,email,city,state;
  	
  	BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
  	
  	
     
      try{
        // create and initialize the ORB
        ORB orb = ORB.init(args, null);

        // get the root naming context
        org.omg.CORBA.Object objRef = 
            orb.resolve_initial_references("NameService");
        // Use NamingContextExt instead of NamingContext. This is 
        // part of the Interoperable naming Service.  
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
 
        // resolve the Object Reference in Naming
        String name = "Hello";
        helloImpl = HelloHelper.narrow(ncRef.resolve_str(name));

        System.out.println("Obtained a handle on server object: " + helloImpl);
        
        
        while(1==1){       
            System.out.println("Enter the Choice\n 1. Find 2. Insert 3. Delete");
            int ch=Integer.parseInt(inp.readLine());
            switch (ch) {
            case 1:
            {    
            System.out.println("Enter the email id of the person");
            email = inp.readLine();
            System.out.println(helloImpl.getRecord(email));
     //       System.out.println(obj.getRecord(email));

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
            System.out.println(helloImpl.insertRecord(fn,ln,phoneno,email,city,state));

            break;
            } 
        
           case 3: {  
             System.out.println("Enter the phone number to be deleted");
             phoneno=inp.readLine();
             System.out.println(helloImpl.deleteRecord(phoneno));

       //     System.out.println(obj.deleteRecord(phoneno));
              break;
           }
             }
           }
        //System.out.println(helloImpl.getRecord(""));
        //System.out.println(helloImpl.sayHi());
        //helloImpl.shutdown();

        } catch (Exception e) {
          System.out.println("ERROR : " + e) ;
          e.printStackTrace(System.out);
          }
    }

}
