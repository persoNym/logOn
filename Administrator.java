/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import java.util.*;
import java.io.*; 
import java.security.*;
import java.util.logging.*;


public class Administrator { 
    

private static String[] menu = {"1. New User","2. Log in",
"3. Change Password","4. Log off","5. List users","6. Find User", "7. Quit"}; 
ArrayList<Person> userList = new ArrayList();
Person currentUser = null;
String UID;
Scanner sc = new Scanner(System.in);
PrintWriter pw = new PrintWriter(System.out); 


public Administrator () throws FileNotFoundException, IOException {
   /* Using the FileDemo program as an example, remove the Scanner and PrintWriter for the user
    data file. Replace them by an ObjectOutputStream and an ObjectInputStream.
    In the Admin constructor, remove the loop that reads the input file one line at a time.
    Replace it by a 1-line call on readObject(), and cast the result of the read operation to
    ArrayList<User>. Store the cast pointer in your ArrayList variable.*/
    doMenu();
    newUser();
        Person newPerson = null;
    try{ //create file
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.dat"));
        // Create a file of Users and prepare to read some values from it 
        newUser();
        Object GetName = ois.readObject();
        Object GetID = ois.readObject();
        Object GetPassword = ois.readObject();
        boolean add = userList.add(newPerson);
        System.out.println(GetName + " " + GetID + " " + GetPassword + " ");
        System.out.println("Input sucessful.");
        ois.close(); //close stream
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }    catch (FileNotFoundException e) {
        System.err.println("FileNotFoundException: File Users.txt does not exist " + e);
                                    }
    try{
        
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.dat"));
        // open the input files as an output file
        //print the data from it
        oos.writeObject(userList);
        oos.close(); //close stream
        }
    catch(IOException io) {
    System.out.println("IOException: Problem reading file Users.txt. Throw to main " + io);
    throw io;
                          }

}
    
    
public void doMenu()  {
    
    
    for(;;){
            System.out.println("LOGON MENU");
            System.out.println("-------------------------------------");
                 for(int i = 0; i < menu.length; i++) {
                    System.out.println(menu[i] +" ");
                 }
            System.out.println("Please select option 1,2,3,4,5,6 or 7 from the menu");
            int choice = sc.nextInt();
                if(choice < 1 || choice > 7) {
                     System.out.printf("Invalid Choice: %s\n", choice);
                     break;
            }
                
    
    switch(choice) {
        
        case 1: newUser(); break;
            
        case 2: logIn(); break; //log in method
            
        case 3: changePassword(); break; //change password method
            
	case 4: logOff(); break; //log off method
            
        case 5: displayUsers(); break; 
            
        case 6: findUser(); break; //find user method
            
        case 7: break; //quit
            
                    }
    if(choice == 7) { //quits the loop
        System.out.println("Now Exiting Program");
        break;
    }
    
               }
            
     //users written to "Users.txt" via the  writeUsers()
         writeUsers(); //calls the function that writes the information to a .dat
         System.exit(0); //aborts program
                    
    
}
    /*--------------------------------------------------------------------------------------*/
    
        public void newUser()  {
        
        System.out.println("Please input your full name:");
        sc.nextLine();
        String Name = sc.nextLine();
        System.out.println("Plese input a user id. UID must be one string of characters:");
        String ID = sc.next();
        String Password = getPassword();
        Person newPerson = new Person(Name, ID, Password);
        userList.add(newPerson);
        if(currentUser == null) {
           currentUser = userList.get(0);
        }
        
        
        }
    
    //--------------------------------------------------------------------------------------
        //log in method
        public void logIn()  {
            System.out.println("login screen");
            System.out.println("ID: ");
            String id = sc.next();
            System.out.println("Password: ");
            String Password = getPassword();
            for(int i = 0; i < userList.size(); i++) {
            if(id.equals(userList.get(i).getID()) && Password.equals(userList.get(i).getPassword())) {
               id = userList.get(i).getID();
               Password = userList.get(i).getPassword();
               currentUser = userList.get(i);
               UID = userList.get(i).getID();
               return;
            }
            }
                System.out.println("Login Failed.");
                return;
            
            }
            

        
    //--------------------------------------------------------------------------------------
        //change password method
        public void changePassword() {
            if (currentUser.equals(userList.get(0))) {
                System.out.println("Enter password");
                String password = getPassword();
                System.out.println("Enter the ID of the person you wish the change");
                String ID = sc.next();
                for( int i = 0; i < userList.size(); i++) {
                   if(userList.get(i).getID().equals(ID) && userList.get(i).getPassword().equals(password)) {
                       System.out.println("Enter new password");
                       password = getPassword();
                       userList.get(i).setPassword(password);
                       return;
                   }
                  
                }
                System.out.println("UID doesn't exist");
            }
            else {
            System.out.println("Enter password");
                String password = getPassword();
                currentUser.setPassword(password);
                System.out.println("Enter new password");
                password = getPassword();
                userList.get(userList.indexOf(currentUser)).setPassword(password);
                return;
        }
           
        }
        
    //--------------------------------------------------------------------------------------

        //log off method
       //Logout is a 2-line function: Set the activeUser to null and the activeUID to -1.
        public void logOff() {
            currentUser = null;
            UID = null;
        }

    //--------------------------------------------------------------------------------------
     public void displayUsers() 
    {//must be restricted to System admin which will be the first person who is created
        if (!currentUser.equals(userList.get(0))) {
            System.out.println("You do not have root authority to access");
            return;}
        for(Object i: userList) {
            String toString = i.toString();
            System.out.println(toString);
        }
       
        
    }
//----------------------------------------------------------------------------------------
     private void findUser(/*String logIn()*/) {
         System.out.println("Enter user ID");
         String ID = sc.next();
         for (int i = 0; i < userList.size(); i++) {
             if(userList.get(i).getID().equals(ID)) {
                System.out.println("User ID is:" + userList.get(i).getID());
                 return;
             }
         }
         System.out.println("User ID does not exist");
     }
    /* findUser( String logon) locates the user with the given logon name in the collection of
    Users and returns the index where it was found. Return -1 if the logon is not in the collec-
    tion. Use ArrayList.get( int k ), ArrayList.size(), String.equals(), and an ordi-
    nary for loop. When the loop finds the desired user, the loop variable, k, will be the UID of
    that user. */

//----------------------------------------------------------------------------------------
       //get password method
        private String getPassword() 
    {
        String hash = null;
    try {
            Scanner scan = new Scanner(System.in);
            // Create a new password
            System.out.print("Input your password : ");
            hash = new String( computeHash( scan.next() ) );
               
            // Check passwords as for login
            String inputHash;
            for (;;) {
                System.out.print("Re-enter your password : " );
                inputHash = new String( computeHash( scan.next() ) );
                if (hash.equals(inputHash)) break;
                System.out.println("Incorrect password");
                return null;
            }
            System.out.println("Password Input Sucessful");
          }
          catch (NoSuchAlgorithmException al){
               System.err.println("SHA-1 algorithm not available");
               al.printStackTrace();
               System.exit(1);
          }
          catch (Exception except) {
              System.err.println("General Exception" + except);
          }
        return hash;
    }
  
//----------------------------------------------------------------------------------------
          //compute Hash
        public static byte[] computeHash( String x )   
     throws NoSuchAlgorithmException  
     {
          MessageDigest d = MessageDigest.getInstance("SHA-1");
          d.update(x.getBytes());
          return  d.digest();
     }
//----------------------------------------------------------------------------------------
     //write users to file
     public void writeUsers() {
         
        try{
            
            
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.dat"));
            for(Person i: userList) {
            oos.writeObject("       ");
            oos.writeObject("Name: ");
            oos.writeObject(i.getName() + " ");
            oos.writeObject("UID:");
            oos.writeObject(i.getID() + " ");
            oos.writeObject("Password:");
            oos.writeObject(i.getPassword() + " ");
            }
      oos.close();
      System.out.print("Information has been saved to users.dat\n");
         
         }
        catch(FileNotFoundException fnf) {
            System.err.println("FileNotFoundException: File Users.txt does not exist " + fnf);
        }
        catch(IOException eyeoh) {
            System.err.println("IOException: Error writing to Users.txt " + eyeoh);
        }
     }
     
            
     
//----------------------------------------------------------------------------------------

    
   


  
}
    
     
    
    

