package net.sssssssthedev.SmartClient.utils;

public class Random {

    static final java.util.Random rand = new java.util.Random();

    public static int rand(int max) {
        return rand.nextInt(max);
    }

    public static int rand() {
        return rand.nextInt();
    }

    public static byte randByte() {
        return (byte) rand();
    }

    public static byte[] randBytes(int len) {
        byte[] a = new byte[len];
        for(int i = 0; i < len; i++)
            a[i] = randByte();
        return a;
    }

    public static boolean randBool() {
        return rand.nextBoolean();
    }

    public static long randLong() {
        return rand.nextLong();
    }

    public static float randFloat() {
        return rand.nextFloat();
    }

    public static double randDouble() {
        return rand.nextDouble();
    }

    public static double randDouble(double min, double max) {
        return rand.nextDouble() * (max - min) + min;
    }
}
