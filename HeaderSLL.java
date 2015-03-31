/**
 * Implementation of singly linked list with a dummy header.
 * @author Jasper Bingham
 * @version 10/10/13
 */
public class HeaderSLL<T> implements CS10LinkedList<T> {
  // Instance variables.
  private Element<T> currentPred;    // current position in the list
  private Element<T> head;       // head of list
  
  /**
   * A private class inner representing the elements in the list.
   */
  private static class Element<T> {
    private T data;         // reference to data stored in this element
    private Element<T> next;   // reference to next item in list
    
    /**
     * Constructor for a linked list element, given an object.
     * @param obj the data stored in the element.
     */
    public Element(T obj) {
      next = null;          // no element after this one, yet
      data = obj;           // OK to copy reference, since obj references an immutable object
    }

    /**
     * @return the String representation of a linked list element.
     */
    public String toString() {
      return data.toString();
    }
  }

  /**
   * Constructor to create an empty singly linked list.
   */
  public HeaderSLL() {
    clear();
  }

  /**
   * Removes all elements from this list.
   */
  public void clear() {
    head = new Element<T>(null); //dummy header
    currentPred = head; //currentPred set to dummy header
  }

  /**
   * Inserts a new element with given object reference, after the current position. 
   * If there is no current element, inserts at the front of the list.
   * Makes the new element the current position.
   * @param obj the object to be inserted.
   */
  public void add(T obj) {
    Element<T> x = new Element<T>(obj);   // allocate a new element
    // There are two distinct cases, depending on whether the new element
    // is to be the new head.
    if (hasCurrent()) {
      	x.next = currentPred.next.next;  // fix the next reference for the new element
      	currentPred.next.next = x;
      	currentPred = currentPred.next; //move currentPred forward
      }
    else {
    	currentPred = head; //keep currentPred the same
    	x.next = currentPred.next; //fix next reference
    	currentPred.next = x;
    }
  }

  /**
   * Removes the current element.  Makes its successor the current position.
   * No current item if the last element is removed.
   */
  public void remove() {
 
    if (!hasCurrent()) {   // check whether current element exists
      System.err.println("No current item");
      return;
    }
    else {
    	currentPred.next = currentPred.next.next; //predecessor's next reference reassigned
    }
  }

  /**
   * Returns the list in String form.
   * @return the String representation of this list.
   */
  public String toString() {
    String result = "";
    
    for (Element<T> x = head.next; x != null; x = x.next) 
      result += x.toString() + "\n"; 
    
    return result;
  }

  /**
   * Finds if an object is within a linke list.
   * If found, sets current to be the element containing the object.
   * If not found, leaves current alone.
   * 
   * @param obj the object to search for
   * @return true it was found, false otherwise.
   */
  public boolean contains(T obj) {
    Element<T> x;
  
    for (x = head; x != null && !x.data.equals(obj); x = x.next) 
      ;
  
    // We dropped out of the loop either because we ran off the end of the list
    // (in which case x == null) or because we found s (and so x != null).
    if (x != null)
      currentPred = x.next;
  
    return x != null;
  }

  /**
   * @return true iff the list is empty.
   */
  public boolean isEmpty() {
    return head.next == null; //is dummy header the only thing in the list?
  }
  
  /**
   * Tests if there is a current item.
   * @return true if there is a valid current
   */
  public boolean hasCurrent() {
    return currentPred.next != null;
  }
  
  /**
   * Tests if there is a next item. 
   * @returns true if there is a next item (current item is not the last item).
   */
  public boolean hasNext() {
    return hasCurrent() && currentPred.next.next != null;
  }
  
  /**
   * Makes the current element be the head of the list.
   * Error if list is empty.
   * @return the object in the head of the list
   */
  public T getFirst() { 
    if(isEmpty()) {
      System.err.println("The list is empty");
      return null;
    }
    currentPred = head;
    return get();
  }
  
  /**
   * Makes the current element be the tail of the list.
   * Error if list is empty.
   * @return the object in the tail of the list.
   */
  public T getLast() {
    if (isEmpty()) {
      System.err.println("The list is empty");
      return null;
    }
    else {
      while(hasNext()) //move until end of list
      {
      	currentPred = currentPred.next;
      }
      return get();
    }
  }

  /**
   * Inserts a new element at the head of a linked list.
   * Makes it the current item.
   * @param obj the element to insert
   */
  public void addFirst(T obj) {
  	Element<T> x = new Element<T>(obj);
  	currentPred = head;
    x.next = currentPred.next;
  	currentPred.next = x;
  }

  /**
   * Inserts a new element at the tail of a linked list.
   * Makes it the current item.
   * @param obj the element to insert
   */
  public void addLast(T obj) {
    if(isEmpty())
      addFirst(obj);
    else {
      getLast();
      add(obj);
    }
  }
  
  /**
   * Error if there is no current item.
   * @return the current item
   */
  public T get() {
    if (hasCurrent()) {
      return currentPred.next.data;
    }
    else {
      System.err.println("No current item");
      return null;
    }

  }
  
  /**
   * Sets the current item's data to obj.  
   * Error if there is no current item.
   * @param obj the new value for current item.
   */
  public void set(T obj) {
    if (hasCurrent())
    	currentPred.next.data = obj;
    else
      System.err.println("No current item");
  }
  
  /**
   * Moves current to the next position.
   * Error if there is no next item.  
   * @return the data in the new current
   */
  public T next() {
    if (hasNext()) {
      currentPred = currentPred.next;
      return currentPred.next.data;
    }
    else {
      System.err.println("No next item");
      return null;
    }
  }
}
