package com.catalina.arrayList;

public class Person {
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();

        System.out.println("G了");
    }

    @Override
    public boolean equals(Object obj) { // 重写equals地址 自定义两个对象怎样才算相等
        if (obj == null) return false;
        if (obj instanceof Person) {
            Person person = (Person) obj;
            return this.age == person.age;
        }
        return false;
    }
}
