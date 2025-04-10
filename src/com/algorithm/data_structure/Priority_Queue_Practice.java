package com.algorithm.data_structure;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 練習使用 PriorityQueue
 * 內部沒有完整排序，每次 poll 出來的值 都會是最大或是最小，取決於排序規則
 */
public class Priority_Queue_Practice {

    static class Person {
        Integer age;
        Integer peepee_wait_time;

        public Person (Integer age, Integer peepee_wait_time) {
            this.age = age;
            this.peepee_wait_time = peepee_wait_time;
        }
    }

    static class MyComp implements Comparator<Person> {
        @Override
        public int compare (Person person1, Person person2) {
            return person1.peepee_wait_time - person2.peepee_wait_time;   // low -> high
        }
    }

    public static void main (String[] args) {
        // default
        Integer[] nums = new Integer[]{4, 3, 1, 5, 2};
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for ( int i = 0 ; i < nums.length ; i++ ) {
            priorityQueue.add(nums[i]);
        }

        while ( true ) {
            if ( priorityQueue.size() == 0 ) break;

            Integer num_min = priorityQueue.poll();
            System.out.print(num_min + " ");
        }

        // custom
        Person[] people = new Person[]{
                new Person(19, 60),
                new Person(28, 45),
                new Person(48, 32),
                new Person(60, 19),
                new Person(33, 200)
        };

        PriorityQueue<Person> personPriorityQueue = new PriorityQueue<>(people.length, new MyComp());
        for ( int i = 0 ; i < people.length ; i++ ) {
            personPriorityQueue.add(people[i]);
        }

        System.out.println();

        while ( true ) {
            if ( personPriorityQueue.size() == 0 ) break;

            Person person = personPriorityQueue.poll();
            System.out.print(person.peepee_wait_time + " ");
        }
    }
}
