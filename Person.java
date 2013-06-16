/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

/**
 *
 * @author nezu
 */
public class Person {
         
         //private members
         private String Name;
         private String ID;
         private String Password;
         
         
     
    public Person() {}

    Person(String Name, String ID, String Password) { //constructor
        
         this.Name = Name;
    
         this.ID = ID;
     
         this.Password = Password;
         
    }

      //public Accessors
        
        public String getName() {return Name;}
        public String getID() {return ID;}
        public String getPassword() {return Password;}
        public void setPassword(String x) {this.Password = x;} //mutator for changing password
        
        @Override
   public String toString()
   {
       return "[" + " User ID is: " + ID + " Name is: " + Name + "]";
   }
    
        
   
    
}
