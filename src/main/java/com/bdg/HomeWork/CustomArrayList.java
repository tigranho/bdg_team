package com.bdg.HomeWork;

import java.util.Arrays;

/**
 * Main class to test ArrayListCustom functionality.
 */
public class CustomArrayList {

    public static void main(String... a) {
        ArrayListCustom<Employee> list = new ArrayListCustom<Employee>();
        list.add(new Employee("1", "Cat"));
        list.add(new Employee("2", "Dog"));
        list.add(new Employee("3", "Horse"));
        list.add(new Employee("4", "Elephant"));
        list.add(new Employee("1", "Bird"));
        list.add(new Employee("2", "Mouse"));
        list.add(null);

        list.display();
        System.out.println("\nelement at index " + 1 + " = " + list.get(1));
        System.out.println("element removed from index " + 1 + " = " + list.remove(1));

        System.out.println("\nlet's display list again after removal at index 1");

        list.display();
    }

    static class Employee {
        private String id;
        private String name;

        /**
         * Employee constructor
         */
        public Employee(String id, String name) { // constructor
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Employee[id=" + id + ", name=" + name + "] ";
        }

    }

    static class ArrayListCustom<E> {

        private static final int INITIAL_CAPACITY = 10;
        private Object elementData[]={};
        private int size = 0;

        /**
         * constructor.
         */
        public ArrayListCustom() {
            elementData = new Object[INITIAL_CAPACITY];
        }

        /**
         * method adds elements in ArrayListCustom.
         */
        public void add(E e) {
            if (size == elementData.length) {
                ensureCapacity(); //increase current capacity of list, make it double.
            }
            elementData[size++] = e;
        }


        /**
         * method returns element on specific index.
         */
        @SuppressWarnings("unchecked")
        public E get(int index) {
            if ( index <0 || index>= size) {  //if index is negative or greater than size of size, we throw Exception.
                throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
            }
            return (E) elementData[index]; //return value on index.
        }


        /**
         * method returns removedElement on specific index.
         * else it throws IndexOutOfBoundException if index is negative or greater than size of size.
         */
        public Object remove(int index) {
            if ( index <0 || index>= size) {  //if index is negative or greater than size of size, we throw Exception.
                throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
            }

            Object removedElement=elementData[index];
            for(int i=index;i<size - 1;i++){
                elementData[i]=elementData[i+1];
            }
            size--;   //reduce size of ArrayListCustom after removal of element.

            return removedElement;
        }


        /**
         * method increases capacity of list by making it double.
         */
        private void ensureCapacity() {
            int newIncreasedCapacity = elementData.length * 2;
            elementData = Arrays.copyOf(elementData, newIncreasedCapacity);
        }

        /**
         * method displays all the elements in list.
         */
        public void display() {
            System.out.print("Displaying list : ");
            for(int i=0;i<size;i++){
                System.out.print(elementData[i]+" ");
            }
        }

    }
}
