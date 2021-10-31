package com.catalina.BST;

import com.catalina.BST.printer.BinaryTrees;

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
        Integer data[] = new Integer[] {
                7,4,9,2,5,8,11,3
        };

        BinarySearchTree<Integer> bst0 = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst0.add(data[i]);
        }
        BinaryTrees.print(bst0);

        BinarySearchTree<Person> bst = new BinarySearchTree<>(new PersonComparator());

        BinarySearchTree<Person> bst2 = new BinarySearchTree<>(new PersonComparator2());
        BinarySearchTree<Person> bst3 = new BinarySearchTree<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });

        BinarySearchTree<Person> bst4 = new BinarySearchTree<>();
    }
}
