package evidencia22.utils;

import java.util.Optional;

public class Queue <E>{
    private LinkedList<E> queue;

    public Queue(){
        queue = new LinkedList<>();
    }

    public void push(E elmtToPush){
        queue.add(elmtToPush);
    }

    public Optional<E> pop(){
        Optional<E> popped = queue.get(0);
        queue.remove(popped.get());
        return popped;
    }

    public Optional<E> peek(){
        return queue.get(0);
    }

    public int size() {
        return queue.getSize();
    }

    public boolean isEmpty(){
        return !(queue.getSize() > 0);
    }

}