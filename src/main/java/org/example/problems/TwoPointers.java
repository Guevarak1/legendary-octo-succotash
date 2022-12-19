package org.example.problems;

import java.util.Arrays;

public class TwoPointers {
    public static void main(String[] args) {
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        maxArea(heights);
    }

    //array is sorted, so we can traverse backwards to find the target indicies.
    //can move backwards if sum is larger than target and move forward if sum is lesser than target
    private static int[] twoSumIITwoPointer(int[] nums, int target) {
        int right = nums.length - 1;
        int left = 0;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) return new int[]{left + 1, right + 1};
            if (sum > target) right--;
            else left++;
        }
        return new int[]{0, 0};
    }

    //Space O(1) time O(nlog(n))
    private static boolean containsDuplicateSort(int[] nums) {
        Arrays.sort(nums);
        int neighborPtr = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[neighborPtr]) return true;
                //don't forget to move pointer up 1 every iteration
            else neighborPtr++;
        }
        return false;
    }

    //https://leetcode.com/problems/container-with-most-water/
    //left and right pointers will calculate the area inside the rectangle
    // with smaller height being the height and length being the distance from left and right
    //if left height is smaller than right, shift left to the right, else shift right to the left.
    //continue calculating areas and set curr max to max if applicable
    public static int maxArea(int[] heights) {
        int left = 0;
        int right = heights.length - 1;
        int maxArea = 0;

        while (left < right) {
            int leftHeight = heights[left];
            int rightHeight = heights[right];
            int minHeight = Math.min(leftHeight, rightHeight);

            int currArea = (right - left) * minHeight;
            if (currArea > maxArea) maxArea = currArea;
            if (leftHeight < rightHeight) left++;
            else right--;
        }
        return maxArea;
    }

}
