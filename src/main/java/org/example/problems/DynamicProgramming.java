package org.example.problems;

//todo revisit all these
//keyword: Contiguous
public class DynamicProgramming {
    public static void main(String[] args) {
//        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        System.out.println(maximumSubArraySlidingWindow(nums));

        int[] numsMaxProd = {2, 3, -2, 4};
        System.out.println(maxProduct(numsMaxProd));
    }

    //Input: nums = [-2,1,-3,4,-1,2,1,-5,4] -> 6
    //can compute with cubic time complexity by adding every subarray
    //can think of this as sliding window.
    //slide left pointer to the right whenever we find a new max continious subarray,
    // removing any negative prefix
    public static int maximumSubArraySlidingWindow(int[] nums) {
        int maxSum = nums[0];
        int currentSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (currentSum < 0) {
                currentSum = 0;
            }
            currentSum += nums[i];
            maxSum = Math.max(currentSum, maxSum);
        }

        return maxSum;
    }


    //keep track of the current max/min products and if the current max > res set res to new curr max
    //triple math.max comparison has to be done because of negative and double negative values
    //Space O(1) time O(n)
    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int currMin = nums[0], currMax = nums[0], result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int tmp = currMax;
            currMax = Math.max(Math.max(currMax * nums[i], currMin * nums[i]), nums[i]);
            currMin = Math.min(Math.min(tmp * nums[i], currMin * nums[i]), nums[i]);
            if (currMax > result) result = currMax;
        }
        return result;
    }
}
