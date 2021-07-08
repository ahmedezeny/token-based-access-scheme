package com.company;
import java.io.IOException;
import java.lang.*;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import static com.company.utils.getRandomBetween;

public class QueuesManager implements Runnable {

    private Thread t;
    private int T;
    private Queue q3;
    private Queue q2 ;
    private Queue q1 ;
    private Queue current;
    private static QueuesManager manager;

    QueuesManager(){
        q3 = new Queue(3);
        q2 = new Queue(2, q3);
        q1 = new Queue(1, q2);
        q3.setNext(q1);
        current = q1;


    }

    private void start() throws InterruptedException {
        setupPacketsSpawner();
        current.process();
    }

    private static QueuesManager getInstance() throws IOException {
        if (manager == null) {
            manager = new QueuesManager();
        }
        return manager;
    }

    private void setupPacketsSpawner(){
        // thread created
        t = new Thread(this, "Admin Thread");

        // prints thread created
        System.out.println("thread  = " + t);

        // this will call run() function
        System.out.println("Calling run() function... ");
        t.start();
    }

    private Queue getQueueNumbered(int i){
        switch (i) {
            case 1:
                return q1;
            case 2:
                return q2;
            default:
                return q3;
        }
    }

    public void run() {
        Queue q;
        int queueNumber;
        while(true) {

            queueNumber = (int) utils.getRandomBetween(1, 3);
            q = getQueueNumbered(queueNumber);
            q.addCustomer();

            long time = System.currentTimeMillis();
            Timestamp t = new Timestamp(time);

            System.out.println(t +" packet Arrival - queue #: " + queueNumber + " # customers: " + q.getCustomers() );

            try {
                TimeUnit.SECONDS.sleep((long) utils.getRandomBetween(1, 4));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }





}
