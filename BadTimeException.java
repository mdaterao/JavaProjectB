/** BadTimeException class extends Java's RuntimeException class.
*/
public class BadTimeException extends RuntimeException {
   
   /** Default constructor with default message.
   */
   public BadTimeException() {
      super("Invalid time of day");
   }
   
   /** Constructor with custom error message.
   *   @param message custom error message
   */
   public BadTimeException(String message) {
      super(message);
   }
}