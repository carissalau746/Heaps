
/**
 * PQUnderflowException for when priority queue is empty
 *
 * @author Carissa Lau
 * @version 6/22/24
 */
public class PQUnderflowException extends RuntimeException
{
    public PQUnderflowException()
    {
        super();
    }
    
    public PQUnderflowException(String message) {
        super(message);
    }
}
