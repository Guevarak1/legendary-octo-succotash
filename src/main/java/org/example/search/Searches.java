package org.example.search;

public class Searches {
    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
//        searchInRotatedArray(nums);
    }

    public static int linearSearch(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) return i;
        }
        return -1;
    }

    public static int binarySearch(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        //while were not checking past the upper and lower bounds of array
        while (low <= high) {
            //get midpoint
            int mid = (high + low) / 2;
            //if target is at midpoint return midpoint
            if (nums[mid] == target) return mid;
            //if target is greater than midpoint, make your lower bound the midpoint + 1 to cut half the search
            if (target > nums[mid]) low = mid + 1;
                //else target is less than midpoint, make your upper bound the midpoint - 1 to cut half the search
            else high = mid - 1;
        }
        return -1;
    }

    //[4, 5, 6, 7, 0, 1, 2] target = 0;
    public static int searchInRotatedArray(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) return target;
            //are we in the left sorted portion
            if (nums[mid] >= nums[left]) {
                //checks that the target comes after the pivot and
                // checks that the target is less than left most,
                // then its on the right side of our array
                if (target > nums[mid] || target < nums[left])
                    left = mid + 1;
                    //target is less than the middle, but greater than the left
                else right = mid - 1;
                //we are in the right sorted portion of the array
            } else {
                //checks that the target comes before the pivot and
                // checks that the target is greater right most
                // then its on the left side of our array
                if (target < nums[mid] || target > nums[right])
                    right = mid - 1;
                    //else its to our left
                else
                    left = mid + 1;
            }
        }
        return -1;
    }
}
