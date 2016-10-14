/*  Michael Manzanares
    masc0363
*/
import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class LinearList<E> implements LinearListADT<E> {

    Node<E> header, trailer;
    int currentSize;
    long modificationCounter;
    
    public LinearList() {
        header = trailer = null;
        currentSize = 0;
        modificationCounter = 0;
       }
    public class Node<E> {
        Node<E> previous;
        E data;
        Node<E> next;
        
        public Node(E data) {
            this.data = data;
            previous = next = null;
           }
       }
    public boolean addFirst(E data) {
        Node<E> newNode = new Node(data);
        if(isEmpty()) header = trailer = newNode;           
        else {
            newNode.next = header;
            header.previous = newNode;
            header = newNode;
           }
        currentSize++;
        modificationCounter++;
        return true;
       }
    //  Adds a new element to the end of the list.
    public boolean addLast(E data) {
        Node<E> newNode = new Node(data);
        if(isEmpty()) header = trailer = newNode;
        else {
            newNode.previous = trailer;
            trailer.next = newNode;
            trailer = newNode;
           }
        currentSize++;
        modificationCounter++;
        return true;
       }
    public E removeFirst() {
        if(isEmpty()) return null;
        E temp = header.data;
        header = header.next;
        //  If you just removed the last node.
        if(header == null) trailer = null;
        
        currentSize--;
        modificationCounter++;
        return temp;
       }
    public E removeLast() {
        if(isEmpty()) return null;
        E temp = trailer.data;
        trailer = trailer.previous;
        //  If you just removed the last node.
        if(header == null) trailer = null;
        else{
            trailer.next = null;
            currentSize--;
            modificationCounter++;
           }
        return temp;
       }
    public E remove(E data) {
        Node<E> current = header, previous = null;
        while(current != null && ((Comparable<E>)data).compareTo(current.data) != 0) {
            previous = current;
            current = current.next;
           }
        if(current==null) return null;
        if(current==header) return removeFirst(); 
        if(current==trailer) return removeLast();
        else {
            previous.next = current.next;
            currentSize--;
            modificationCounter++;
           }    
        return trailer.data;
       }
    public E peekFirst() {
        if(isEmpty()) return null;
        return header.data;
       }
    public E peekLast() {
        if(isEmpty()) return null;
        return trailer.data;
       }

    public boolean contains(E data) {
        Node<E> temp = header;
        while(!isEmpty()) {
            if(temp.data != null && ((Comparable<E>) data).compareTo(temp.data) == 0) 
                return true;
            else {
                temp = temp.next;
               }
           }
        return false;
       }
    public E find(E data) {
        if(contains(data)) 
            return data;
        return null;
       }
    public void clear() {
        header = trailer = null;
        currentSize = 0;
        modificationCounter++;
       }
    public boolean isEmpty() {
        if(currentSize == 0) 
            return true;
        else    return false;
       }
    public boolean isFull() {
        return false;
       }
    public int size() {
        return currentSize;
       }

    public Iterator<E> iterator() {
        return new ListIteratorHelper();
       }
    
    class ListIteratorHelper implements Iterator<E> {
        Node<E> iterPtr;
        long modCounter;
        
        public ListIteratorHelper() {
            iterPtr = header;
            modCounter = modificationCounter;
           }
        public boolean hasNext() {
            if(modCounter != modificationCounter)
              throw new ConcurrentModificationException();
            return iterPtr != null;
           }
        public E next() {
            if(!hasNext())
                throw new NoSuchElementException();
            E temp = iterPtr.data;
            iterPtr = iterPtr.next;
            return temp;       
           }
        public void remove() {
            throw new UnsupportedOperationException();
           }
       }
   }