package com.company;

import com.company.Interfaces.IQueue;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class Queue implements IQueue {
    private IQueue next;
    private int ID;

    private int customers;
    private long departTime;



    public  Queue(int id){
        this.ID = id;
        this.next = null;
    }
    public Queue(int id, IQueue next) {
        this.ID = id;
        this.next = next;
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
    public void process() throws InterruptedException {
        long time = System.currentTimeMillis();
        while (time < this.getDepartTime() && this.getCustomers()!=0){
            TimeUnit.SECONDS.sleep((long) utils.getRandomBetween(0, 2));
            this.removeCustomer();
            time = System.currentTimeMillis();
            Timestamp t = new Timestamp(time);
            System.out.println(t +" service completion - queue #: " + this.ID + " # customers: " + this.getCustomers() );
        }
        time = System.currentTimeMillis();
        Timestamp t = new Timestamp(time);
        System.out.println(t +" token surrender - queue #: " + this.ID + " # customers: " + this.getCustomers() );
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
