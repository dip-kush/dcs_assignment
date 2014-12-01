import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import HelloApp.Hello;
import HelloApp.HelloHelper;
import HelloApp.HelloPOA;

import com.mysql.jdbc.Statement;

class HelloImpl extends HelloPOA {
	private ORB orb;
  
    String result=null;
	public ResultSet rs;
  
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	public static final String DB_URL = "jdbc:mysql://localhost/mybook";

	   //  Database credentials
	public static final String PASS = "dynamickool";
	public static final String USER = "root";
	public static final String MYDB = "mybook";

	


  public void setORB(ORB orb_val) {
    orb = orb_val; 
  }

@Override
public String getRecord(String email) {
	
	Connection conn = null;
	    Statement stmt = null;
	    
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);

	      //STEP 4: Execute a query
	 //     System.out.println("Creating database...");
	 //     stmt = conn.createStatement();
	      System.out.println(email);
	      
	      
	     PreparedStatement state = conn.prepareStatement("select * from phonebook where email=?");
      state.setString(1, email);
      
      try{
    	  rs = state.executeQuery(); 	     
      }
      catch(SQLException e){
    	  return "Record Not Found";
      }
      
      if(rs==null)
    	 return "Record Not Found";
       while(rs.next()){
    	   result = rs.getString("phoneno");
       }
       System.out.println("Database created successfully...");
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");

	   
    if (result==null)
    		return "Record Not Found";
    return result;

}

@Override
public String insertRecord(String fn, String ln, String phn, String email,
		String city, String state) {
	Statement stmt = null;
	Connection conn = null;
	
 	   try{
  	      //STEP 2: Register JDBC driver
  	      Class.forName("com.mysql.jdbc.Driver");

  	      //STEP 3: Open a connection
  	      System.out.println("Connecting to database...");
  	      conn = DriverManager.getConnection(DB_URL, USER, PASS);

  	      //STEP 4: Execute a query
  	 //     System.out.println("Creating database...");
  	 //     stmt = conn.createStatement();
  	      
  	      
  	     PreparedStatement state2 = conn.prepareStatement("insert into phonebook values(?,?,?,?,?,?)");
 	      state2.setString(1, fn);
 	     state2.setString(2, ln);
 	    state2.setString(3, phn);
 	   state2.setString(4, email);
 	  state2.setString(5, city);
 	 state2.setString(6, state);
 	   
 	      try{
 	      state2.executeUpdate() ;	
 	      }
 	      catch(SQLException e){
 	    	  System.out.print("Sorry Record could not be inserted");
 	      }
 	       System.out.println("Database created successfully...");
  	   }catch(SQLException se){
  	      //Handle errors for JDBC
  	      se.printStackTrace();
  	   }catch(Exception e){
  	      //Handle errors for Class.forName
  	      e.printStackTrace();
  	   }finally{
  	      //block used to close resources
  	      try{
  	         if(stmt!=null)
  	            stmt.close();
  	      }catch(SQLException se2){
  	      }// nothing we can do
  	      try{
  	         if(conn!=null)
  	            conn.close();
  	      }catch(SQLException se){
  	         se.printStackTrace();
  	      }//end finally try
  	   }//end try
  	   System.out.println("Goodbye!");

  	   
     	
      return "values inserted";
}

@Override
public String deleteRecord(String phn) {
	// TODO Auto-generated method stub
	Statement stmt = null;
	Connection conn = null;
	
 	   try{
  	      //STEP 2: Register JDBC driver
  	      Class.forName("com.mysql.jdbc.Driver");

  	      //STEP 3: Open a connection
  	      System.out.println("Connecting to database...");
  	      conn = DriverManager.getConnection(DB_URL, USER, PASS);

  	      //STEP 4: Execute a query
  	 //     System.out.println("Creating database...");
  	 //     stmt = conn.createStatement();
  	      
  	      
  	     PreparedStatement state3 = conn.prepareStatement("delete from  phonebook where phoneno = ?");
 	      state3.setString(1, phn);
 	   
 	      int f = state3.executeUpdate();
 	      if(f==0)
 	    	  return "Record Does not exist";
 	        System.out.println("Database created successfully...");
  	   }catch(SQLException se){
  	      //Handle errors for JDBC
  	      se.printStackTrace();
  	   }catch(Exception e){
  	      //Handle errors for Class.forName
  	      e.printStackTrace();
  	   }finally{
  	      //finally block used to close resources
  	      try{
  	         if(stmt!=null)
  	            stmt.close();
  	      }catch(SQLException se2){
  	      }// nothing we can do
  	      try{
  	         if(conn!=null)
  	            conn.close();
  	      }catch(SQLException se){
  	         se.printStackTrace();
  	      }//end finally try
  	   }//end try
  	   System.out.println("Goodbye!");

  	   
     	
      return "values deleted";

	
   }
    
  // implement sayHello() method
    
  
}


public class HelloServer {

  public static void main(String args[]) {
    try{
      // create and initialize the ORB
      ORB orb = ORB.init(args, null);

      // get reference to rootpoa & activate the POAManager
      POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      rootpoa.the_POAManager().activate();

      // create servant and register it with the ORB
      HelloImpl helloImpl = new HelloImpl();
      helloImpl.setORB(orb); 

      // get object reference from the servant
      org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImpl);
      Hello href = HelloHelper.narrow(ref);
          
      // get the root naming context
      // NameService invokes the name service
      org.omg.CORBA.Object objRef =
          orb.resolve_initial_references("NameService");
      // Use NamingContextExt which is part of the Interoperable
      // Naming Service (INS) specification.
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

      // bind the Object Reference in Naming
      String name = "Hello";
      NameComponent path[] = ncRef.to_name( name );
      ncRef.rebind(path, href);

      System.out.println("HelloServer ready and waiting for instructions ...");

      // wait for invocations from clients
      orb.run();
    } 
        
      catch (Exception e) {
        System.err.println("ERROR: " + e);
        e.printStackTrace(System.out);
      }
          
      System.out.println("HelloServer Exiting ...");
        
  }
}
 

