/** This class creates objects that are personal planners.
*/
public class Planner implements Collection {

   /** Default maximum size of planner. */
   private static final int MAXSIZE = 1000;
   
   /** Default starting capacity of planner. */
   private static final int INITIALSIZE = 10;
   
   /** Collection of things that must be done. */
   private Task[] todo;
   /** Number of actual tasks in the planner. */
   private int numtasks;

   /** Create a Planner with the default Collection starting size.
   */
   public Planner() {
      todo = new Task[INITIALSIZE];
      numtasks = 0;
   }

   /** Find number of tasks in the planner.
   *  @return number of tasks
   */
   // THIS METHOD IS COMPLETE
   @Override
   public int size() {
      return this.numtasks;
   }

   /** Add an item to the planner, doubling the size of the planner
      if not yet at the maximum size.
      @param o the item to add, must be some type of Task
      @return true if added, false otherwise
   */
   @Override
   public boolean add(Object o) {
      boolean returnBoolean = false;
      if (! (o instanceof Task)) {
         returnBoolean = false;
      }
      else if (o instanceof Task && numtasks < 1000) {
         doubleCapacity();
         todo[numtasks++] = (Task) o;
         returnBoolean = true;
      }
      return returnBoolean;
   }
   
   /** Double the size of the planner and cap at 1000.
   */
   private void doubleCapacity() {
      int doubleSize = todo.length * 2;
      if (doubleSize > MAXSIZE) {
         //Cap planner size at 1000
         doubleSize = MAXSIZE;
      }
      else {
         Task[] newTodo = new Task[doubleSize];
         for (int i = 0; i < numtasks; i++) {
            newTodo[i] = todo[i];
         }
         todo = newTodo;
      } 
   }
   

   // THIS METHOD IS COMPLETE
   @Override
   public void display() {
      for (int i = 0; i < this.numtasks; i++) {
         System.out.printf("(%3d) %s\n", i, this.todo[i]);
      }
   }

   /** Find all items in the Planner that contain the parameter as a substring.
       @param o the target string to find (case sensitive)
       @return a string with all items in the Planner containing o, one per line
               or an empty string if there are none
   */
   @Override
   public Object find(Object o) {
      StringBuilder searchResult = new StringBuilder();
      if (o instanceof String) {
         String targetSearch = (String) o;
         
         
         for (int i = 0; i < numtasks; i++) {
            if (todo[i].toString().contains(targetSearch)) {
               searchResult.append(todo[i]).append("\n");
            }
         }  
      }
      return searchResult.toString();
   }
       


   // THIS METHOD IS COMPLETE
   @Override
   public Object get(int n) {
      if (n > size()) {
         throw new ArrayIndexOutOfBoundsException();
      }
      return this.todo[n - 1];  // adjust for nth numbering
   }

}
