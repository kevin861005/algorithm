package com.algorithm.leetcode;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/course-schedule/description/">course-schedule</a>
 */
public class leetcode0207 {

    Map<Integer, List<Integer>> map = new HashMap<>();
    Set<Integer> done = new HashSet<>();

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int pre = prerequisites[i][1];

            List<Integer> list = map.getOrDefault(course, new ArrayList<>());
            list.add(pre);
            map.put(course, list);
        }

        for (int i = 0; i < numCourses; i++) {
            Set<Integer> set = new HashSet<>();

            boolean is_finishable = check_finishable(i, set);

            if ( ! is_finishable ) return false;
        }

        return true;
    }

    private boolean check_finishable (int numCourse, Set<Integer> set) {
        if ( done.contains(numCourse) ) return true;

        if ( set.contains(numCourse) ) return false;    // loop detected
        set.add(numCourse);

        List<Integer> list = map.get(numCourse);
        if ( list != null ) {
            for ( Integer pre : list ) {
                boolean is_finishable = check_finishable(pre, set);
                if ( ! is_finishable ) return false;
            }
        }

        set.remove(numCourse);
        done.add(numCourse);
        return true;
    }
}
