package com.company;

import java.util.*;

public class Main{
    public static void main (String[] args){
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter the size of an array: ");
        int size = myScanner.nextInt();
        int[] firstArray = new int[size];
        System.out.println("Enter the item of array:");
        for(int i=0; i<size; i++){
            firstArray[i] = myScanner.nextInt();
        }
        int sizeM = size/2;

        int[] firstHalf;
        int[] secondHalf;

        if((size%2) == 0){
            firstHalf = new int[sizeM];
            secondHalf = new int[sizeM];
            for(int i=0; i<sizeM; i++){
                firstHalf[i] = firstArray[i];
                secondHalf[i] = firstArray[i+sizeM];
            }
        }

        else{
            firstHalf = new int[sizeM];
            secondHalf = new int[sizeM+1];
            for(int i=0; i<sizeM; i++){
                firstHalf[i] = firstArray[i];
            }
            for(int i=0; i<sizeM+1; i++){
                secondHalf[i] = firstArray[i+sizeM];
            }
        }
        System.out.println(" first half of an array ");
        for(int i=0; i<firstHalf.length;i++){
            System.out.print(firstHalf[i]+ " ");
        }
        System.out.println("");
        System.out.println(" second half of an array ");
        for(int i=0; i<secondHalf.length;i++){
            System.out.print(secondHalf[i] + " " );
        }

        Thread thread1 = new Thread(new MergeTask(firstHalf));
        Thread thread2 = new Thread(new MergeTask(secondHalf));
        Thread thread3 = new Thread(new MergeTask(firstHalf, secondHalf));
        System.out.println(" ");
        System.out.println(" Sorted arrays 1 " );

        thread1.start();
        try {
            thread1.join();
        }
        catch(InterruptedException ex) { }

        thread2.start();
        try{
            thread2.join();
        }
        catch(InterruptedException ex) { }

        thread3.start();
        try {
            thread3.join();
        }
        catch(InterruptedException ex) { }


        Thread thread4 = new Thread(new MergeTask1(firstHalf));
        Thread thread5 = new Thread(new MergeTask1(secondHalf));
        Thread thread6 = new Thread(new MergeTask1(firstHalf, secondHalf));
        System.out.println(" Sorted arrays 2 " );

        thread4.start();
        try {
            thread4.join();
        }
        catch(InterruptedException ex) { }

        thread5.start();
        try{
            thread5.join();
        }
        catch(InterruptedException ex) { }

        thread6.start();
        try {
            thread6.join();
        }
        catch(InterruptedException ex) { }
    }
}

class MergeTask extends Thread {
    int[] nums;

    MergeTask(int[] n) {
        nums = n;
    }

    MergeTask(int[] x, int[] y) {
        int middle = x.length;
        nums = new int[x.length + y.length];
        for (int i = 0; i < (x.length + y.length); i++) {
            if (i < middle) {
                nums[i] = x[i];
            } else {
                nums[i] = y[i - middle];
            }
        }
    }

    public void run() {
        Arrays.sort(this.nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }
}

class MergeTask1 extends Thread{
    int[] nums;

    MergeTask1(int[] n){
        nums = n;
    }

    MergeTask1(int[] x, int[] y){
        int middle = x.length;
        nums = new int[x.length + y.length];
        for(int i = 0; i < (x.length + y.length); i++){
            if(i < middle){
                nums[i] = x[i];
            }
            else{
                nums[i] = y[i - middle];
            }
        }
    }

    public void run() {
        Arrays.sort(this.nums);
        int temp;
        for (int i = nums.length-1, j = 0; i >=nums.length/2 ; i--,j++) {
            temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
        }
        for(int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }
}