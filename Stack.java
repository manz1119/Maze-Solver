/*  Michael Manzanares
    masc0363
*/

import java.util.Iterator;

public class Stack<E> implements Iterable {
    LinearListADT<E> StackList;
    
    public Stack() {
        StackList = new LinearList<E>();
       }
    //  Inserts the object into the stack.
    public void push(E obj) {
        StackList.addFirst(obj);
       }    
    public E pop() {
        return StackList.removeFirst();
       }
    public int size() {
        return StackList.size();
       } 
    public boolean isEmpty() {
        return StackList.isEmpty();
       }
    public E peek() {
        return StackList.peekFirst();
       }
    public boolean contains(E obj) {
        return StackList.contains(obj);
       }
    public void makeEmpty() {
        StackList.clear();
       }
    public E remove(E obj) {
        return StackList.remove(obj);
       }
    public Iterator<E> iterator() {
        return StackList.iterator();
       }
   }