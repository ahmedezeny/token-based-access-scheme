package com.company.Interfaces;

import java.util.List;

public interface IQueue {

    public void setNext(IQueue queue);
    public IQueue getNext();
    public int getId();

    public void addCustomer() ;
    public void removeCustomer();

    public  int getCustomers();
    public long getDepartTime() ;

    public long getInBetweenTime() ;

    public void process() throws InterruptedException;

    public void setDepartTime(long departTime) ;
    public void start(int t);

}
