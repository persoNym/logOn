/*
@name   Victoria C
@project security logon 5
 */
package users;

import java.io.*;
import java.security.NoSuchAlgorithmException;



public class Users implements Serializable {
    

    
    public static void main(String[] args) {
     /*In main(), add a catch block for NoSuchAlgorithmException Print an error comment
    about â€œSHA-1 not availableâ€ on the stream System.err, then abort.
    Write a second catch block to handle all possible exceptions. Print an error comment saying
    that the exception was caught in main. Then execute: e.printStackTrace(); */
        System.out.println("Security java program by Victoria");
       java.util.Date date = new java.util.Date();
       System.out.println("Last acessed:" + date.toGMTString());
       try{
       Administrator AdministratorInstance = new Administrator();
            AdministratorInstance.doMenu();
       }
      
      catch (IOException io) { 
        System.out.println("General I/O exception: " + io.getMessage() );
        io.printStackTrace();
        System.exit(-1);
      }

         
     
        }
    
}
