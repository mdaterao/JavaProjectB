import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This program has menu options to manipulate a personal planner, including:
 * add a Task, DueDo, or Appointment to the planner (getting input from the
 * user), as well as search for items, and display all items. It adds exception
 * handling to the PlannerDriver.java file.
 *
 * @author Monal Daterao
 *
 *         JHED: mdatera1
 *
 *         1/19/2024
 */
public class ProjectD {
   private static Scanner keyboard = new Scanner(System.in);

   public static void main(String[] args) {
      int choice = 1;
      Planner p = new Planner();
      Task t;
      DueDo d;
      Appointment a;
      String s;
      int item;

      while (choice != 0) {
         choice = menu();
         try {
            switch (choice) {
               case 0:
                  break;
               case 1:
                  t = new Task();
                  t.read(keyboard);
                  p.add(t);
                  break;
               case 2:
                  d = new DueDo();
                  d.read(keyboard);
                  p.add(d);
                  break;
               case 3:
                  a = new Appointment();
                  a.read(keyboard);
                  p.add(a);
                  break;
               case 4:
                  System.out.println(
                        "there are " + p.size() + " items in the planner");
                  break;
               case 5:
                  p.display();
                  break;
               case 6:
                  System.out.print("what to search for? ");
                  try {
                     s = keyboard.nextLine();
                     System.out.println(p.find(s));
                  }
                  catch (NoSuchElementException ioe) {
                     System.err.println(ioe);
                     System.err.println("search cancelled");
                  }
                  break;
               case 7:
                  System.out.print("enter item number: ");
                  item = keyboard.nextInt();
                  keyboard.nextLine();
                  if (item < p.size()) {
                     ((Task) p.get(item + 1)).toggleDone();
                  }
                  else {
                     System.err.println("invalid index: " + item);
                  }
                  break;
               default:
                  System.err.println("invalid choice!");
            }
         } catch (IOException exception) {
            System.err.println("Error reading input: " 
                                + exception.getMessage());
         } catch (NumberFormatException exception) {
            System.err.println("Invalid input format: " 
                                + exception.getMessage());
         }
      }
   }

   /**
    * Print a menu of operations for the user.
    * 
    * @return the option chosen
    */
   public static int menu() {
      int choice;
      System.out.println(); // spacing for readability
      System.out.println("0) quit");
      System.out.println("1) add to-do item");
      System.out.println("2) add due item");
      System.out.println("3) add appointment");
      System.out.println("4) planner size");
      System.out.println("5) display items");
      System.out.println("6) search (use [ ] or [x] to filter)");
      System.out.println("7) toggle item done");
      do {
         System.out.print("  enter choice -> ");
         try {
            choice = Integer.parseInt(keyboard.nextLine());
         } catch (NumberFormatException e) {
            System.err.println(e);
            choice = -1;
         } catch (NoSuchElementException ioe) {
            System.err.println(ioe);
            choice = -1;
         }
      } while (choice == -1);
      return choice;
   }

}
