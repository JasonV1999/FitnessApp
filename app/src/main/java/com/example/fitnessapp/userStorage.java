package com.example.fitnessapp;

public class userStorage {
    private String usersCurrentWeight;
    private String Today;

    public userStorage(String usersCurrentWeight, String today) {
        this.usersCurrentWeight = usersCurrentWeight;
        Today = today;
    }

    public String getUsersCurrentWeight() {
        return usersCurrentWeight;
    }

    public void setUsersCurrentWeight(String usersCurrentWeight) {
        this.usersCurrentWeight = usersCurrentWeight;
    }

    public String getToday() {
        return Today;
    }

    public void setToday(String today) {
        Today = today;
    }
}
