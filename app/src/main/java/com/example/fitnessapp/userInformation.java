package com.example.fitnessapp;

public class userInformation {
    private String userWeight;
    private String userHeight;
    private String userAge;
    private String intakeOfCalories;
    private String weightGoal;
    private String calorieGoal;
    private String Gender;
    private String systemFormats;

    public userInformation(){

    }


    public userInformation(String userWeight, String userHeight, String userAge, String intakeOfCalories, String weightGoal, String calorieGoal, String gender, String systemFormats) {
        this.userWeight = userWeight;
        this.userHeight = userHeight;
        this.userAge = userAge;
        this.intakeOfCalories = intakeOfCalories;
        this.weightGoal = weightGoal;
        this.calorieGoal = calorieGoal;
        Gender = gender;
        this.systemFormats = systemFormats;

    }

    public String getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(String userWeight) {
        this.userWeight = userWeight;
    }

    public String getUserHeight() {
        return userHeight;
    }

    public void setUserHeight(String userHeight) {
        this.userHeight = userHeight;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getIntakeOfCalories() {
        return intakeOfCalories;
    }

    public void setIntakeOfCalories(String intakeOfCalories) {
        this.intakeOfCalories = intakeOfCalories;
    }

    public String getWeightGoal() {
        return weightGoal;
    }

    public void setWeightGoal(String weightGoal) {
        this.weightGoal = weightGoal;
    }

    public String getCalorieGoal() {
        return calorieGoal;
    }

    public void setCalorieGoal(String calorieGoal) {
        this.calorieGoal = calorieGoal;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getSystemFormats() {
        return systemFormats;
    }

    public void setSystemFormats(String systemFormats) {
        this.systemFormats = systemFormats;
    }
}
