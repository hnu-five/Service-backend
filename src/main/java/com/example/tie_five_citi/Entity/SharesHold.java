package com.example.tie_five_citi.Entity;

public class SharesHold {
    private String RIC;
    private String user_id;
    private int current_hold;

    public void setRIC(String RIC) {
        this.RIC = RIC;
    }

    public void setCurrent_hold(int current_hold) {
        this.current_hold = current_hold;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public int getCurrent_hold() {
        return current_hold;
    }

    public String getRIC() {
        return RIC;
    }
}
