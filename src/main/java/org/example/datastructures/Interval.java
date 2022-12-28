package org.example.datastructures;

import java.util.List;

public class Interval {
    public int min, max;

    public Interval(int start, int end) {
        this.min = start;
        this.max = end;
    }

    public static int[][] toArrayOfArrays(List<Interval> intervals) {
        int[][] result = new int[intervals.size()][2];
        for (int i = 0; i < intervals.size(); i++) {
            Interval currInterval = intervals.get(i);
            int[] currRes = new int[]{currInterval.min, currInterval.max};
            result[i] = currRes;
        }
        return result;
    }

    public int getMin() {
        return min;
    }
}