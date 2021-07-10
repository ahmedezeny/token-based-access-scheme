package com.company;

import com.company.Interfaces.IQueue;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class Queue implements IQueue {
    private IQueue next;
    private int ID;
    private int T;
    private int customers;
    private long departTime;



    public  Queue(int id, int T){
        this.ID = id;
        this.next = null;
        this.T = T;
    }
    public Queue(int id, IQueue next, int T) {
        this.ID = id;
        this.next = next;
        this.T = T;
    }


    @Override
    public void setNext(IQueue queue) {
        this.next = queue;
    }

    @Override
    public IQueue getNext() {
        return this.next;
    }

    @Override
    public int getId() {
        return this.ID;
    }

    @Override
    public void removeCustomer() {
        if (this.customers != 0)
            customers--;

    }

    @Override
    public int getCustomers() {
        return customers;
    }

    @Override
    public void addCustomer() {
        this.customers ++;
    }

    @Override
    public long getDepartTime() {
        return departTime;
    }

    @Override
    public long getInBetweenTime() {
        return this.T;
    }

    @Override
    public void process() throws InterruptedException {
        long endTime = System.currentTimeMillis()+(30*1000);
        while (System.currentTimeMillis()<endTime) {
            long time = System.currentTimeMillis();
            this.departTime = time + this.T;
            while (time < this.getDepartTime() && this.getCustomers() != 0) {
                TimeUnit.SECONDS.sleep((long) utils.getRandomBetween(0, 2));
                this.removeCustomer();
                time = System.currentTimeMillis();
                Timestamp t = new Timestamp(time);
                System.out.println(t + " service completion - queue #: " + this.ID + " # customers: " + this.getCustomers());
            }
            time = System.currentTimeMillis();
            Timestamp t = new Timestamp(time);
            System.out.println(t + " token surrender - queue #: " + this.ID + " # customers: " + this.getCustomers());
        }
    }

    @Override
    public void setDepartTime(long departTime) {
        this.departTime = departTime;
    }

    @Override
    public void start(int t) {
        setDepartTime(System.nanoTime() + (t*1000));
    }
}
