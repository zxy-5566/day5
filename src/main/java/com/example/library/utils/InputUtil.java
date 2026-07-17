package com.example.library.utils;

import java.util.Scanner;

public class InputUtil {
    private static Scanner scanner = new Scanner(System.in);
    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    public static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠ 输入无效，请输入一个整数。");
            }
        }
    }
     public static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠ 输入无效，请输入一个数字。");
            }
        }
    }
    public static void close() {
        scanner.close();
    }
    public static boolean readConfirm(String prompt) {
        while (true) {
            String input = readString(prompt + "(Y/N): ");
            if (input.equalsIgnoreCase("Y")) return true;
            if (input.equalsIgnoreCase("N")) return false;
            System.out.println("⚠ 请输入 Y 或 N。");
        }
    }
}
