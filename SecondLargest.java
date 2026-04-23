public class SecondLargest {
    public static int findSecondLargest(int[] arr) {
        int largest = Integer.MIN_VALUE;
        int second  = Integer.MIN_VALUE;

        for (int num : arr) {
            if (num > largest) {
                second  = largest; // old largest becomes second
                largest = num;
            } else if (num > second && num != largest) {
                second = num;
            }
        }

        if (second == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("No second largest element");
        }
        return second;
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 1, 9};
        System.out.println(findSecondLargest(arr)); // 8
    }
}