import java.util.ArrayList;
/**
 * heap:3
 *
 * @author carissa
 * @version 6/22/24
 */
public class Heap<T extends Comparable<T>> implements PriorityQueue<T>
{
    ArrayList<T> list;
    public Heap() {
        list = new ArrayList<T>();
    }

    // returns the logical size of the priority queue
    public int size() {
        return list.size();
    }

    // tests if this priority queue is empty
    public boolean empty() {
        return list.isEmpty();
    }

    // adds an item to the priority queue
    public T add(T element) {
        list.add(element);
        reheapUp(element);
        return element;
    }

    private void reheapUp(T element) {
        int hole = size() - 1;
        while(element.compareTo(list.get((hole-1)/2)) > 0 && hole > 0) {
            list.set(hole, list.get((hole-1)/2));
            hole = (hole-1)/2;
        }
        list.set(hole,element);
    }

    // looks at the object at the front of this priority queue
    // without removing it from the priority queue
    public T peek() throws PQUnderflowException {
        if(empty()) {
            throw new PQUnderflowException("you silly goose, it's empty!");
        }
        return list.get(0);
    }

    // removes the object at the front of this priority queue 
    // and returns that object as the value of this function
    public T remove() throws PQUnderflowException {
        if(empty()) {
            throw new PQUnderflowException("you silly goose, it's empty!");
        }
        T hold = list.get(0);
        T toMove = list.remove(size()-1);
        if(!empty()) { //make sure its not the last removal
            reheapDown(toMove);
        }
        return hold;
    }

    private void reheapDown(T element) {
        int hole = 0; //root location
        int newHole = newHole(hole, element);
        while(newHole != hole) {
            list.set(hole, list.get(newHole));
            hole = newHole;
            newHole = newHole(hole,element);
        }
        list.set(hole,element);
    }

    private int newHole(int hole, T element) {
        int left = 2*hole+1;
        int right = 2*hole+2;
        if(right < size()) {
            if(element.compareTo(list.get(left)) < 0 && element.compareTo(list.get(right)) > 0) 
                return left;
            else if(element.compareTo(list.get(left)) > 0 && element.compareTo(list.get(right)) < 0)
                return right;
            else if(element.compareTo(list.get(left)) < 0 && element.compareTo(list.get(right)) < 0)
                return (list.get(left).compareTo(list.get(right)) < 0)? right : left;
            //if true return right, if false, return left
        }
        return hole;
    }

    // removes all of the elements from this priority queue
    public void clear() {
        list.clear();
    }

    public String toString() {
        return list.toString();
    }
}
