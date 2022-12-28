package org.example.problems;

import org.example.datastructures.Interval;

import java.util.*;

public class ArrayProbs {

    public static void main(String[] args) {
        int[] nums = {0, 0, 0};
//        System.out.println(threeSum(nums));

        int[] colors = {2, 0, 2, 1, 1, 0};
//        sortColorsOnePass(colors);

        int[] rotatedArray = {-1, -100, 3, 99};
//        rotateInPlace(rotatedArray, 2);

        List<Interval> meetings = new ArrayList<>();
        meetings.add(new Interval(0, 5));
        meetings.add(new Interval(5, 10));
        meetings.add(new Interval(15, 20));

//        meetingRooms(meetings);
    }

    //**********************
    //EASIES
    //**********************

    //Space O(1) time O(n^2)
    private static int[] twoSumBrute(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int currRes = nums[i] + nums[j];
                if (currRes == target) return new int[]{i, j};
            }
        }
        return new int[]{0, 0};
    }

    //space O(n) time O(n)
    private static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            //target - nums[i] should be potential value needed to sum for target
            if (map.containsKey(target - nums[i]))
                return new int[]{map.get(target - nums[i]), i};
                //store potential target number in map key
            else map.put(nums[i], i);
        }
        return new int[]{0, 0};
    }

    //space O(1) time O(n^2)
    private static int[] twoSumIIBrute(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int curSum = nums[i] + nums[j];
                if (curSum == target) return new int[]{i + 1, j + 1};
                else if (curSum > target) break;
            }
        }
        return new int[]{0, 0};
    }

    //Space O(1) time O(n)
    private static int maxProfit(int[] prices) {
        int leastSoFar = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            if (price < leastSoFar)
                leastSoFar = price;

            int profitIfSoldToday = price - leastSoFar;
            if (profitIfSoldToday > maxProfit)
                maxProfit = profitIfSoldToday;
        }
        return maxProfit;
    }

    //[1,2,3,1] -> true
    //Space O(n) time O(n)
    private static boolean containsDuplicate(int[] nums) {
        Set<Integer> distinctNums = new HashSet<>();
        for (int num : nums) {
            if (!distinctNums.add(num)) {
                return true;
            }
        }
        return false;
    }

    //Space O(1) time O(nlog(n))
    public static int missingNumber(int[] nums) {
        Arrays.sort(nums);
        int counter = 0;
        for (int num : nums) {
            if (num != counter) return counter;
            else counter++;
        }
        if (nums.length == counter) return counter;
        else return -1;
    }

    //Space O(n) time O(n)
    public static int majorityElementHashTable(int[] nums) {
        HashMap<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            if (counter.containsKey(num)) {
                int count = counter.get(num);
                counter.put(num, count + 1);
            } else {
                counter.put(num, 1);
            }
        }

        int majorityKey = 0;
        for (int key : counter.keySet()) {
            int currCount = counter.get(key);
            if (currCount > counter.getOrDefault(majorityKey, -1)) majorityKey = key;
        }

        return majorityKey;
    }

    //space O(1) time O(nlog(n))
    public static int majorityElementSorted(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public static boolean meetingRooms(List<Interval> meetingRooms) {
        meetingRooms.sort(Comparator.comparing(Interval::getMin));
        for (int i = 0; i < meetingRooms.size() - 1; i++) {
            Interval currentTimeSlot = meetingRooms.get(i);
            Interval nextTimeSlot = meetingRooms.get(i + 1);
            if (currentTimeSlot.max > nextTimeSlot.min) return false;
        }
        return true;
    }

    //**********************
    //MEDIUMS
    //**********************

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

    //Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
    //Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        for (Interval i : intervals) {
            //first cases: if new interval is null or current intervals max is less than new intervals min,
            // add current interval
            if (newInterval == null || i.max < newInterval.min) {
                result.add(i);
            }
            //add new interval before current interval since new intervals max comes before current intervals min
            else if (i.min > newInterval.max) {
                result.add(newInterval);
                result.add(i);
                newInterval = null;
            } else {
                //update new interval based on min max of current and new interval
                newInterval.min = Math.min(newInterval.min, i.min);
                newInterval.max = Math.max(newInterval.max, i.max);
            }
        }

        if (newInterval != null)
            result.add(newInterval);
        return result;
    }

    //Input = [1,2,3,4] -> Output = [24,12,8,6]
    //prefix = [1,2,6,24]
    //postfix = [24,24,12,4]
    //prefix sum concept
    //space O(n) time O(n) bc were creating a new array
    public static int[] productExceptSelf(int[] nums) {
        int[] prefixArr = new int[nums.length];
        int[] postfixArr = new int[nums.length];

        int currentVal = 1;
        for (int i = 0; i < nums.length; i++) {
            prefixArr[i] = currentVal * nums[i];
            currentVal = prefixArr[i];
        }

        currentVal = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            postfixArr[i] = currentVal * nums[i];
            currentVal = postfixArr[i];
        }

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int leftNeighbor = i - 1, rightNeighbor = i + 1;
            if (leftNeighbor == -1) result[i] = postfixArr[rightNeighbor];
            else if (rightNeighbor == nums.length) result[i] = prefixArr[leftNeighbor];
            else result[i] = prefixArr[leftNeighbor] * postfixArr[rightNeighbor];
        }
        return result;
    }

    //space O(1) time O(n) bc output array doesnt count
    public static int[] productExceptSelfNoExtraSpace(int[] nums) {
        return null;
    }

    //Space O(n) Time O(n^2)
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int currNum = nums[i];
            int nextPointer = i + 1;
            int reversePointer = nums.length - 1;
            while (nextPointer < reversePointer) {
                int nextNum = nums[nextPointer];
                int reverseNum = nums[reversePointer];

                int resSum = currNum + nextNum + reverseNum;
                if (resSum == 0) {
                    ArrayList<Integer> numsArr = new ArrayList<>();
                    numsArr.add(currNum);
                    numsArr.add(nextNum);
                    numsArr.add(reverseNum);
                    if (!res.contains(numsArr))
                        res.add(numsArr);
                }
                if (resSum > 0)
                    reversePointer--;
                else
                    nextPointer++;
            }
        }
        return res;
    }

    //https://leetcode.com/problems/sort-colors/
    //sort without using Array.sort() aka sort in place
    //[2,0,2,1,1,0] -> [0,0,1,1,2,2]
    public static void sortColors(int[] nums) {
        int zeros = 0;
        int ones = 0;
        int twos = 0;

        for (int num : nums) {
            if (num == 0) zeros++;
            else if (num == 1) ones++;
            else if (num == 2) twos++;
        }

        for (int i = 0; i < zeros; i++) {
            nums[i] = 0;
        }

        for (int i = zeros; i < zeros + ones; i++) {
            nums[i] = 1;
        }

        for (int i = zeros + ones; i < zeros + ones + twos; i++) {
            nums[i] = 2;
        }
    }

    //dont increment iterator when nums[i] == 2 because we might swap out 1 out of place
    //Space O(1) Time O(n)
    public static void sortColorsOnePass(int[] nums) {
        int leftPointer = 0;
        int rightPointer = nums.length - 1;
        int i = 0;
        while (i <= rightPointer) {
            if (nums[i] == 0) {
                swap(nums, i, leftPointer);
                leftPointer++;
                i++;
            } else if (nums[i] == 2) {
                swap(nums, i, rightPointer);
                rightPointer--;
            } else {
                i++;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    //https://leetcode.com/problems/rotate-array/
    public static void rotateExtraSpace(int[] nums, int k) {
        int[] rotatedArr = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (i + k >= nums.length) {
                int rotatedIndex = (i + k) % nums.length;
                rotatedArr[rotatedIndex] = nums[i];
            } else {
                rotatedArr[i + k] = nums[i];
            }
        }

        System.arraycopy(rotatedArr, 0, nums, 0, nums.length);
    }

    //a "trick" reverse array, then reverse everything before arr[k] then everything after arr[k]
    public static void rotateInPlace(int[] nums, int k) {
        int leftPointer = 0;
        int rightPointer = nums.length - 1;
        //reverse
        while (leftPointer < rightPointer) {
            swap(nums, leftPointer, rightPointer);
            leftPointer++;
            rightPointer--;
        }

        leftPointer = 0;
        rightPointer = k - 1;
        while (leftPointer < rightPointer) {
            swap(nums, leftPointer, rightPointer);
            leftPointer++;
            rightPointer--;
        }

        leftPointer = k;
        rightPointer = nums.length - 1;
        while (leftPointer < rightPointer) {
            swap(nums, leftPointer, rightPointer);
            leftPointer++;
            rightPointer--;
        }
    }
}
