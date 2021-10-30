package com.catalina.BST;

import java.util.Comparator;

public class Main {
    private static class PersonComparator implements Comparator<Person> {

        @Override
        public int compare(Person e1, Person e2) {
            return e1.getAge() - e2.getAge();
        }
    }

    private static class PersonComparator2 implements Comparator<Person> {

        @Override
        public int compare(Person e1, Person e2) {
            return e2.getAge() - e1.getAge();
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Person> bst = new BinarySearchTree<>(new PersonComparator());
    }
}
