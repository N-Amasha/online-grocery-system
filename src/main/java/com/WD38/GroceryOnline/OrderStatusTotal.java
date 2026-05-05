package com.WD38.GroceryOnline;

public class OrderStatusTotal {
    private int packing;
    private int inTransit;
    private int received;
    private int cancelled;

    public OrderStatusTotal() {
    }

    public void setPacking(int packing) {
        this.packing = packing;
    }

    public void setinTransit(int shipped) {
        this.inTransit = shipped;
    }

    public void setReceived(int received) {
        this.received = received;
    }

    public void setCancelled(int cancelled) {
        this.cancelled = cancelled;
    }

    public int getCancelled() {
        return cancelled;
    }

    public int getInTransit() {
        return inTransit;
    }

    public int getPacking() {
        return packing;
    }

    public int getReceived() {
        return received;
    }
    
}
