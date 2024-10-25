import java.util.Scanner;
import java.io.IOException;

/** This class represents objects with specific tasks and deadlines.
*/
public class DueDo extends Task {   

   //private data members
   private String date;
   private Time due;

   /** Prompt for and read a DueDo description, deadline date and time
       from user input, handling invalid Time inputs gracefully.
       @param in the input source
       @throws IOException if other input errors
   */
   // THIS METHOD IS COMPLETE and should not be changed!
   @Override
   public void read(Scanner in) throws IOException {
      super.read(in);
      System.out.print("enter date: ");
      this.date = in.nextLine();
      System.out.print("enter when [hh:mm a/p]: ");
      due = new Time(in.nextLine());
   }

   /** Get all the inherited data (formatted like a Task) as
       well as the deadline date and time, separated by commas.
       @return the full description
   */
   @Override
   public String toString() {
      return super.toString() + ", " + this.date + ", " + due.toString();
   }
}
