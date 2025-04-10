package com.algorithm.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 枚舉法實作
 * 透過搜尋所有國家與國家間的飛行時數，找出符合要求時數的路線
 */
public class Enumeration_TravelPlan {
    Integer[][] hours;

    /**
     * 國家總數
     */
    String[] country_remained = new String[]{"NP", "IS", "CA", "UK", "US"};

    /**
     * 建立國家到國家間的飛行時數
     */
    private void build_hour_table () {
        // NP: 0 (North Pole)
        // IS: 1
        // CA: 2
        // UK: 3
        // US: 4

        this.hours = new Integer[5][5];

        hours[0][0] = 0;    // NP -> NP
        hours[0][1] = 14;    // NP -> IS
        hours[0][2] = 15;    // NP -> CA
        hours[0][3] = 17;    // NP -> UK
        hours[0][4] = 16;    // NP -> US

        hours[1][0] = 14;    // IS -> NP
        hours[1][1] = 0;    // IS -> IS
        hours[1][2] = 24;    // IS -> CA
        hours[1][3] = 8;    // IS -> UK
        hours[1][4] = 36;    // IS -> US

        hours[2][0] = 15;    // CA -> NP
        hours[2][1] = 24;    // CA -> IS
        hours[2][2] = 0;    // CA -> CA
        hours[2][3] = 34;    // CA -> UK
        hours[2][4] = 4;    // CA -> US

        hours[3][0] = 17;    // UK -> NP
        hours[3][1] = 8;    // UK -> IS
        hours[3][2] = 34;    // UK -> CA
        hours[3][3] = 0;    // UK -> UK
        hours[3][4] = 30;    // UK -> US

        hours[4][0] = 16;    // US -> NP
        hours[4][1] = 36;    // US -> IS
        hours[4][2] = 4;    // US -> CA
        hours[4][3] = 30;    // US -> UK
        hours[4][4] = 0;    // US -> US
    }

    public Integer get_hour (String start, String end) {
        Integer x = get_index(start);
        Integer y = get_index(end);

        return this.hours[x][y];
    }

    private Integer get_index (String str) {
        if ( "NP".equals(str) ) return 0;
        if ( "IS".equals(str) ) return 1;
        if ( "CA".equals(str) ) return 2;
        if ( "UK".equals(str) ) return 3;
        if ( "US".equals(str) ) return 4;

        return null;
    }

    /**
     * Enumeration
     */
    List<String> route = new ArrayList<>();
    public void enumeration (int hour_constraint) {
        String start_country = "NP";

        route.add(start_country);

        country_remained[0] = null;

        enumeration_recursion(hour_constraint);
    }

    private void enumeration_recursion (int hour_constraint) {
        if ( route.size() == 5 ) {
            int hour_total = get_hour_total();

            if ( hour_total < hour_constraint ) {
                print_result(hour_total, route);
            }
            else {
                System.out.print("[X]: ");

                print_result(hour_total, route);
            }

            return;
        }

        /**
         * step 1: just pick any child to continue each round
         */
        for ( int i = 0 ; i < country_remained.length ; i++ ) {
            if ( country_remained[i] == null ) continue;

            String country_next = country_remained[i];

            // take out
            route.add(country_next);
            country_remained[i] = null;

            // next round
            enumeration_recursion(hour_constraint);

            // give back
            route.remove(country_next);
            country_remained[i] = country_next;
        }
    }

    private void print_result (int hour_total, List<String> route) {
        for ( int i = 0 ; i < route.size() ; i++ ) {
            System.out.print(route.get(i));

            if ( i + 1 == route.size() ) break;

            System.out.print("->");
        }

        System.out.println(" : " + hour_total);
    }

    private int get_hour_total () {
        if ( route.isEmpty() ) return 0;

        int hour_total = 0;

        String start = route.get(0);
        String end = null;
        for ( int i = 1 ; i < route.size() ; i++ ) {
            end = route.get(i);

            hour_total += get_hour(start, end);

            start = end;
        }

        return hour_total;
    }

    public static void main (String[] args) {
        Enumeration_TravelPlan tp = new Enumeration_TravelPlan();
        tp.build_hour_table();;

        int hour_constraint = 60;
        tp.enumeration(hour_constraint);
    }
}
