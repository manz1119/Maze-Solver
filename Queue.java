/*  Michael Manzanares
    masc0363
*/

import java.util.Iterator;

public class Queue<E> implements Iterable {
    LinearListADT<E> QueueList;
    
    public Queue() {
        QueueList = new LinearList<E>();
       }
    public void enqueue(E obj) {
        QueueList.addLast(obj);
       }
    public E dequeue() {
        return QueueList.removeFirst();
       }
    public int size() {
        return QueueList.size();
       }
    public boolean isEmpty() {
        return QueueList.isEmpty();
       }
    public E peek() {
        return QueueList.peekFirst();
       } 
    public boolean contains(E obj) {
        return QueueList.contains(obj);
       }
    public void makeEmpty() {
        QueueList.clear();
       }
    public boolean remove(E obj) {    //  Changed from boolean to E.
        if(QueueList.remove(obj) != null) {
            return true;
        }
        return false;
       }
    //  Returns an iterator of the elements in the queue.  
    //  The elements must be in the same sequence as dequeue would return them. 
    public Iterator<E> iterator() {
        return QueueList.iterator();
       }
   }