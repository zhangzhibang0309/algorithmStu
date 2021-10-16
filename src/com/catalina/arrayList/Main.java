package com.catalina.arrayList;

import javax.xml.bind.Element;

public class Main {
    public static void main(String[] args) {
        int arr[] = new int[]{1, 2, 3};
        // 数组缺点，不能动态add remove
        // 所以我们自己写个动态数组 取名为arraylist

        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person(19,"zz"));
        persons.add(new Person(21,"bb"));
        persons.add(new Person(99,"dd"));
        persons.clear();
        System.gc(); // 提醒垃圾回收

//        System.out.println(persons);
    }
}
