/**
 * Implementation of a circular, doubly linked list for the Josephus game.
 * @author Jasper Bingham
 * @version 10/10/13
 */
public class JosephusDLL<T> {
  private Element<T> current;  // current position in the list
  
  /**
   * A private inner class representing the elements in the list.
   */
  private static class Element<T> {
    private T data;                  // reference to data stored in this element
    private Element<T> next;            // reference to next item in list
    private Element<T> previous;        // reference to previous item in list
    
    /**
     * Constructor for a linked list element, given an object.
     * @param obj the data in the element.
     */
    public Element(T obj) {
      next = previous = null;   // no element before or after this one, yet
      data = obj;               // OK to copy reference, since obj references an immutable object
    }

    /**
     *  @return the String representation of a linked list element.
     */
    public String toString() {
      return data.toString();
    }
  }

  /**
   * Constructor for a circular, doubly linked list.
   * Makes an empty list.
   */
  public JosephusDLL() {
    clear();
  }

  /**
   * Removes all elements from this list.
   */
  public void clear() {
  	current = new Element<T>(null); //new element with no data
  	current.previous = current; //these references make new element only
    current.next = current;			//one in the list
  }

  /**
   * Inserts a new element with given object reference, after the current position. 
   * If there is no current element, inserts at the front of the list.
   * Makes the new element the current position.
   * @param obj the object to be inserted.
   */
  public void add(T obj) {
    Element<T> x = new Element<T>(obj);     // allocate a new element
    //if list is empty, makes element the only one in the list
    if(isEmpty())
    {
    	current = x;
    	current.previous = current;
      current.next = current;
    }
    // Otherwise, splice in the new element.
    else
    {
      x.next = current.next;
      x.previous = current;
      current.next.previous = x;
      current.next = x;
      current = x; // new element is current position
    }                       
  }

  /**
   * Removes the current element.  Makes its successor the current position.
   * No current item if the last element is removed.
   */
  public void remove() {
    if (hasCurrent()) {          
      // Splice out the current element.
      current.previous.next = current.next;
      current.next.previous = current.previous;       
      current = current.next;           // make successor the new current
    }
    else 
      System.err.println("No current item");
  }


  /**
   * @return true iff the list is empty.
   */
  public boolean isEmpty() {
    return !hasCurrent();
  }
  
  /**
   * @return true if there is a valid current
   */
  public boolean hasCurrent() {
    return current.data != null;
  }
  
  /** 
   * @returns true if there is a next item (current item is not the last item).
   */
  public boolean hasNext() {
    return hasCurrent() && current.next != current;
  }
  
  /**
   * Error if there is no current item.
   * @return the current item
   */
  public T get() {
    if (hasCurrent()) 
      return current.data;
    else {
      System.err.println("No current item");
      return null;
    }
  }
  
  /**
   * Moves current to the next position.
   * Error if there is no next item.  
   * @return the data in the new current
   */
  public T next() {
    if (hasNext()) { 
      current = current.next;
      return current.data;
    }
    else {
      System.err.println("No next item");
      return null;
    }
  }
}
