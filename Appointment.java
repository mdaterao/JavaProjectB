import java.io.IOException;
import java.util.Scanner;

/**
 * This class represents appointments consisting of a purpose, location, a date,
 * a start time, and an end time.
 */
public class Appointment extends Task {

   // Location of appointment
   private String location;

   // Date of appointment
   private String date;

   // Start time of appointment
   private Time startTime;
   private Time endTime;

   /**
    * Default Appointment class constructor.
    */
   public Appointment() {
      // Call Task constructor
      super();
      // Initialize default Appointment characteristics
      this.location = "";
      this.date = "";
      this.startTime = new Time();
      this.endTime = new Time();
   }

   /**
    * Read appointment details from user input using Scanner.
    * 
    * @param scnr input
    * @throws IOException if there are are input errors
    */
   @Override
   public void read(Scanner scnr) throws IOException {
      // Call read method of superclass Task
      super.read(scnr);

      // Prompt user to enter date
      System.out.print("enter date: ");
      this.date = scnr.nextLine();

      // Prompt user to enter start time
      try {
         System.out.print("enter when [hh:mm a/p]: ");
         String startTimeString = scnr.nextLine();
         this.startTime = new Time(startTimeString);
      } catch (BadTimeException e) {
         // reset time if invalid
         this.startTime = new Time(0, 0);
      }

      // Prompt user to enter end time
      try {
         System.out.print("enter end time [hh:mm a/p]]: ");
         String endTimeString = scnr.nextLine();
         this.endTime = new Time(endTimeString);
      } catch (BadTimeException e) {
         // reset time if invalid
         this.endTime = new Time(0, 0);
      }

      // Prompt user to enter location
      System.out.print("enter where: ");
      this.location = scnr.nextLine();

   }

   /**
    * Format appointment information into a string .
    * 
    * @return a formatted string with the appointment information
    */
   @Override
   public String toString() {
      return super.toString() + ", " + this.date + ", " + this.startTime + " - "
            + this.endTime + ", " + this.location;
   }

}