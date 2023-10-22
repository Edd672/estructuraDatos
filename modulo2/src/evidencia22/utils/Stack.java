package evidencia22.utils;

import java.util.Optional;

public class Stack <E>{
    private LinkedList<E> stack;

    public Stack(){
        stack = new LinkedList<>();
    }

    public void push(E elmtToPush){
        stack.addFirst(elmtToPush);
    }

    public Optional<E> peek(){
        return stack.get(stack.getSize() - 1);
    }

    public Optional<E> pop(){
        Optional<E> popped = stack.get(0);
        stack.remove(popped.get());
        return popped;
    }

    public int getSize() {
        return stack.getSize();
    }

    public boolean isEmpty(){
        return !(stack.getSize() > 0);
    }
}