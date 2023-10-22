package evidencia22.utils;

import java.util.NoSuchElementException;
import java.util.Optional;

public class LinkedList<E> implements ILinkedList<E> {
    private Optional<Node<E>> root;
    private int size;

    public LinkedList(){
        root = Optional.empty();
        size = 0;
    }

    @Override
    public void add(E e) {
        Node<E> newNode = new Node<>(e);

        if(root.isEmpty()){
            root = Optional.of(newNode);
        }
        else{
            Optional<Node<E>> currentNode = root;

            while(currentNode.get().next.isPresent()){
                currentNode = currentNode.get().next;
            }

            currentNode.get().next = Optional.of(newNode);
        }

        increaseSize();
    }

    public void addFirst(E e){
        Node<E> newNode = new Node<>(e);

        if(root.isEmpty()){
            root = Optional.of(newNode);
        }
        else{
            newNode.next = root;
            root = Optional.of(newNode);
        }

        increaseSize();
    }

    @Override
    public void update(E oldValue, E newValue) {
        Optional<Node<E>> currentNode = root;

        while (currentNode.isPresent() && !currentNode.get().data.equals(oldValue)) {
            currentNode = currentNode.get().next;
        }

        if (currentNode.isPresent()) {
            currentNode.get().data = newValue;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void remove(E e) {
        if (root.isEmpty()) {
            throw new NoSuchElementException();
        }

        if (root.get().data.equals(e)) {
            root = root.get().next;
            decreaseSize();
            return;
        }

        Optional<Node<E>> currentNode = root;
        Optional<Node<E>> prevNode = Optional.empty();

        while (currentNode.isPresent()) {
            if (currentNode.get().data.equals(e)) {
                prevNode.get().next = currentNode.get().next;
                decreaseSize();
                return;
            }
            prevNode = currentNode;
            currentNode = currentNode.get().next;
        }

        throw new NoSuchElementException();
    }

    @Override
    public Optional<E> get(int index) {
        if(index < 0 || index >= size ){
            throw new IndexOutOfBoundsException();
        }

        int currentIndex = 0;
        Optional<Node<E>> currentNode = root;

        while(currentIndex != index){
            currentNode = currentNode.get().next;
            ++currentIndex;
        }

        return Optional.of(currentNode.get().data);
    }

    @Override
    public int getSize() {
        return size;
    }

    private void increaseSize(){
        ++size;
    }

    private void decreaseSize(){
        --size;
    }

    @Override
    public boolean contains(E t) {
        Optional<Node<E>> currentNode = root;

        while(!currentNode.get().data.equals(t) && currentNode.get().next.isPresent()){
            currentNode = currentNode.get().next;
        }

        if(currentNode.get().data.equals(t)){
            return true;
        }
        return false;

    }
}