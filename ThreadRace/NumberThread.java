package ThreadOdev;

import java.util.ArrayList;

public class NumberThread implements Runnable{
    private int number=1;
    private final Object LOCK = new Object();
    ArrayList<Integer> arrayList1= new ArrayList<>();
    ArrayList<Integer> arrayList2= new ArrayList<>();
    ArrayList<Integer> arrayList3= new ArrayList<>();
    ArrayList<Integer> arrayList4= new ArrayList<>();
    ArrayList<Integer> arrayListEven= new ArrayList<>();
    ArrayList<Integer> arrayListOdd= new ArrayList<>();
    @Override
    public void run() {
        synchronized (LOCK){
            if (this.number <= 2500) {
                this.arrayList1.add(this.number);
                System.out.println("arrayList1 : "+this.number);
            } else if (this.number <= 5000) {
                this.arrayList2.add(this.number);
                System.out.println("arrayList2 : "+this.number);
            } else if (this.number <= 7500) {
                this.arrayList3.add(this.number);
                System.out.println("arrayList3 : "+this.number);
            } else {
                this.arrayList4.add(this.number);
                System.out.println("arrayList4 : "+this.number);
            }

            if (this.number % 2 == 0) {
                this.arrayListEven.add(this.number);
                System.out.println("arrayListEven : "+this.number);
            } else {
                this.arrayListOdd.add(this.number);
                System.out.println("arrayListOdd : "+this.number);
            }
            this.number++;
        }
    }
    }

