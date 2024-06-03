package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class task4 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String[] nums = reader.lines().map(String::trim).toArray(String[]::new);
            int[] numsArray = Arrays.stream(nums).mapToInt(Integer::parseInt).toArray();

            int minSteps = 0;
            int median = getMedian(numsArray);

            for (int num : numsArray) {
                minSteps += Math.abs(num - median);
            }

            System.out.println(minSteps);
        }
    }

    private static int getMedian(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        return (n % 2 == 0) ? (arr[n / 2 - 1] + arr[n / 2]) / 2 : arr[n / 2];
    }
}