package br.com.dmazine.cracking_the_coding_interview.quicksort;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 26, 7, 14, 3, 7, 12};

        System.out.println("Original:");
        printArray(arr);

        quickSort(arr);

        System.out.println("Sorted:");
        printArray(arr);
    }

    private static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int startIndex, int endIndex) {
        int splitPoint = partition(arr, startIndex, endIndex);

        if (startIndex < splitPoint - 1) {
            quickSort(arr, startIndex, splitPoint - 1);
        }

        if (splitPoint < endIndex) {
            quickSort(arr, splitPoint, endIndex);
        }
    }

    private static int partition(int[] arr, int startIndex, int endIndex) {
        final int pivot = arr[(startIndex + endIndex) / 2];

        int left = startIndex;
        int right = endIndex;

        while (left <= right) {
            while (arr[left] < pivot) {
                left++;
            }

            while (arr[right] > pivot) {
                right--;
            }

            if (left <= right) {
                exchangeValues(arr, left, right);
                left++;
                right--;
            }
        }

        return left;
    }

    private static void exchangeValues(int[] arr, int leftIndex, int rightIndex) {
        final int tmp = arr[leftIndex];
        arr[leftIndex] = arr[rightIndex];
        arr[rightIndex] = tmp;
    }

}
