import java.util.StringTokenizer;
import java.text.DecimalFormat;

/**
 * Time class for manipulating times of day.
 * 
 * @author Joanne Selinski
 */
public class Time {

   /** Format to print hours and minutes with two digits. */
   public static final DecimalFormat TIMEFMT = new DecimalFormat("00");

   // Time is stored in military format
   private int hours; // 0-23 valid, inclusive
   private int minutes; // 0-59 valid, inclusive

   /**
    * Default constructor for Time class.
    */
   public Time() {
      hours = 0;
      minutes = 0;
   }

   /**
    * Time constructor that takes an input string.
    * 
    * @param timeString the time string to parse
    */
   public Time(String timeString) throws BadTimeException {

      StringTokenizer tok = new StringTokenizer(timeString, " :apAP");

      try {
         hours = Integer.parseInt(tok.nextToken());
         minutes = Integer.parseInt(tok.nextToken());
      } catch (NumberFormatException e) {
         System.out.println(
               "Not possible to convert this string in a numeric type");
      }
      char meridian = timeString.toLowerCase().charAt(timeString.length() - 1);

      if (meridian == 'p' && hours != 12) {
         hours += 12;
      }
      if (meridian == 'a' && hours == 12) {
         hours = 0;
      }

      if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
         throw new BadTimeException(
               "Invalid hours or minutes in the time string");
      }
   }

   /**
    * Time constructor that takes in the time in military format.
    * 
    * @param hrs  the hour in military format
    * @param mins the minutes past the hour
    * @throws BadTimeException if invalid hours or minutes
    */
   public Time(int hrs, int mins) throws BadTimeException {
      hours = hrs;
      minutes = mins;
      if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
         throw new BadTimeException(
               "Invalid hours or minutes in military format");
      }

   }

   /**
    * Method to return the minutes.
    * 
    * @return the minutes past the hour
    */
   public int getMinutes() {
      return minutes;
   }

   /**
    * Method to return the hour.
    * 
    * @return the hour (0 <= hour <= 23)
    */
   public int getHours() {
      return hours;
   }

   /**
    * Method to return whether or not the stored time is in the morning.
    * 
    * @return true if morning, false if afternoon/evening
    */
   public boolean isMorning() {
      return hours < 12;
   }

   /**
    * Method to return the time elapsed between two Time objects.
    * 
    * @param t the end time
    * @return a Time object representing the elapsed time between this and the
    *         parameter time
    */
   public Time until(Time t) {
      int tHours = t.getHours();
      int tMins = t.getMinutes();

      if (compareTo(t) == 0) {
         return new Time(); // 0:00 difference
      }
      if (compareTo(t) > 0) {
         tHours += 24;
      }

      int elapsedH = tHours - hours;
      int elapsedM = tMins - minutes;

      if (elapsedM < 0) {
         elapsedH--;
         elapsedM += 60;
      }

      Time ttime = new Time();
      ttime = new Time(elapsedH, elapsedM);
      return ttime;
   }

   /**
    * Method to compare this Time to another Time.
    * 
    * @param t the Time object to compare to this
    * @return 0 if the Times are equal, -1 if this is before t, 1 if this is
    *         after t
    */
   public int compareTo(Time t) {
      if (hours < t.hours || (hours == t.hours && minutes < t.minutes)) {
         return -1;
      } else if (hours > t.hours || (hours == t.hours && minutes > t.minutes)) {
         return 1;
      } else {
         return 0;
      }
   }

   /**
    * Method to convert this Time object to a string.
    * 
    * @return a String representing the time stored by this Time object
    */
   public String toString() {
      return TIMEFMT.format(hours) + ":" + TIMEFMT.format(minutes);
   }

   /**
    * Add minutes to the current time.
    * 
    * @param mins how many minutes to increase
    */
   public void add(int mins) {
      int elapsed = hours * 60 + minutes + mins;
      hours = elapsed / 60;
      minutes = elapsed % 60;
      if (hours >= 24) {
         hours = hours % 24;
      }
   }

   /**
    * Calculate how many minutes between midnight and this time.
    * 
    * @return the number of minutes
    */
   public int elapsed() {
      return hours * 60 + minutes;
   }

}
