package com.geccocrawler.gecco.demo.mysql;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SomeDemo {

    public static void main(String[] args) {
        Random r = new Random();
        int low = 10;
        int high = 10000;
        int result = r.nextInt(high-low) + low;

        String a="hello";
        int sum =0;
        a.chars().forEach(System.out::println);
         sum=  a.chars().reduce(0,(x,y)->x+y);
        System.out.println(sum);
        System.out.println(result);

        Integer id = Integer.valueOf(String.valueOf(sum)+String.valueOf(result));
        System.out.println(id);
    }
}
