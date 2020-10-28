package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    String Gender, formatSystem,genderStore;
    Switch Converter;
    TextView startingWeight, currentHeight, Age, Genders, calorieIntake, goalWeight, goalCalorieIntake, detailsWeight, detailsHeight, detailsGoalWeight, dailyWeight;
    CalendarView currentDate;
    EditText email, password, txtOther, Weight, Height, Age2, Calories, GoalWeight, GoalCalories, currentWeight;
    Button signIn, createNew, signOut, Add, Profile, dailyFood, dailyWeights, Details, cancel, addWeight, cancelWeight, returnUser, edit;
    RadioButton Male, Female, Other;
    private FirebaseAuth mAuth;
    FirebaseUser loggedIn;
    CardView logInCardView, detailsCardView, optionsCardView, dailyCardView, profileCardView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        loggedIn = mAuth.getCurrentUser();
        email = findViewById(R.id.enterEmail);
        password = findViewById(R.id.enterPassword);
        signIn = findViewById(R.id.btnLogin);
        signOut = findViewById(R.id.btnSignOut);
        createNew = findViewById(R.id.btnNewProfile);
        logInCardView = findViewById(R.id.cvLogin);
        Converter = findViewById(R.id.switchConvert);
        startingWeight = findViewById(R.id.txtWeight);
        currentHeight = findViewById(R.id.txtHeight);
        Age = findViewById(R.id.txtAge);
        Genders = findViewById(R.id.txtGender);
        calorieIntake = findViewById(R.id.txtCaloryIntake);
        goalWeight = findViewById(R.id.txtGoalWeight);
        goalCalorieIntake = findViewById(R.id.txtGoalCaloryIntake);
        detailsWeight = findViewById(R.id.lblWeight);
        detailsHeight = findViewById(R.id.lblHeight);
        detailsGoalWeight = findViewById(R.id.lblGoalWeight);
        dailyWeight = findViewById(R.id.txtKilogram);
        currentDate = findViewById(R.id.dateCurrent);
        txtOther = findViewById(R.id.txtOther);
        Weight = findViewById(R.id.numWeight);
        Height = findViewById(R.id.numHeight);
        Age2 = findViewById(R.id.numAge);
        Calories = findViewById(R.id.numCalory);
        GoalWeight = findViewById(R.id.numGoalWeight);
        GoalCalories = findViewById(R.id.numGoalCalory);
        currentWeight = findViewById(R.id.numTodayWeight);
        Add = findViewById(R.id.btnAdd);
        Profile = findViewById(R.id.btnProfile);
        dailyFood = findViewById(R.id.btnDailyFood);
        dailyWeights = findViewById(R.id.btnDailyWeight);
        Details = findViewById(R.id.btnDetails);
        cancel = findViewById(R.id.btnCancel);
        addWeight = findViewById(R.id.btnAddDaily);
        cancelWeight = findViewById(R.id.btnCancelDaily);
        returnUser = findViewById(R.id.btnReturn);
        edit = findViewById(R.id.btnEdit);
        Male = findViewById(R.id.radMale);
        Female = findViewById(R.id.radFemale);
        Other = findViewById(R.id.radOther);
        detailsCardView = findViewById(R.id.cvDetails);
        optionsCardView = findViewById(R.id.cvOptions);
        dailyCardView = findViewById(R.id.cvDaily);
        profileCardView = findViewById(R.id.cvProfile);

        detailsCardView.setVisibility(View.GONE);
        dailyCardView.setVisibility(View.GONE);
        currentDate.setVisibility(View.GONE);
        profileCardView.setVisibility(View.GONE);
        Gender ="Male";
        formatSystem = "Metric";


        if (loggedIn != null)
        {
            logInCardView.setVisibility(View.GONE);
            optionsCardView.setVisibility(View.VISIBLE);
        }
        else
        {
            logInCardView.setVisibility(View.VISIBLE);
            signOut.setVisibility(View.GONE);
            optionsCardView.setVisibility(View.GONE);
        }


        createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredEmail = email.getText().toString().trim();
                String enteredPassword = password.getText().toString().trim();
                mAuth.createUserWithEmailAndPassword(enteredEmail, enteredPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "User "
                                            + mAuth.getCurrentUser().getEmail() + "Successful", Toast.LENGTH_SHORT).show();
                                }
                                else if(enteredEmail.isEmpty() || enteredPassword.isEmpty()){
                                    Toast.makeText(MainActivity.this, "Please Fill in all fields", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredEmail = email.getText().toString().trim();
                String enteredPassword = password.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(enteredEmail,enteredPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this, "Logged in " +
                                    mAuth.getCurrentUser().getEmail()+" successfully", Toast.LENGTH_SHORT).show();
                            logInCardView.setVisibility(View.GONE);
                            optionsCardView.setVisibility(View.VISIBLE);
                            signOut.setVisibility(View.VISIBLE);
                        }

                        else
                        {
                            Toast.makeText(MainActivity.this, "Oh no! You did something wrong :(", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Toast.makeText(MainActivity.this, "You have signed out", Toast.LENGTH_SHORT).show();
                logInCardView.setVisibility(View.VISIBLE);
                optionsCardView.setVisibility(View.GONE);
                detailsCardView.setVisibility(View.GONE);
                dailyCardView.setVisibility(View.GONE);
                profileCardView.setVisibility(View.GONE);
                signOut.setVisibility(View.GONE);
            }
        });

        Male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Male.isChecked())
                {
                    txtOther.setEnabled(false);
                    Gender ="Male";
                    Female.setChecked(false);
                    Other.setChecked(false);
                    Toast.makeText(MainActivity.this,"You Chose: " + Gender, Toast.LENGTH_SHORT).show();
                }

            }
        });
        Female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Female.isChecked())
                {
                    txtOther.setEnabled(false);
                    Gender ="Female";
                    Male.setChecked(false);
                    Other.setChecked(false);
                    Toast.makeText(MainActivity.this,"You Chose: " + Gender, Toast.LENGTH_SHORT).show();
                }

            }
        });
        Other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Other.isChecked())
                {
                    txtOther.setEnabled(true);
                    Gender = txtOther.getText().toString();
                    Female.setChecked(false);
                    Male.setChecked(false);
                    Toast.makeText(MainActivity.this,"You Chose: " + Gender, Toast.LENGTH_SHORT).show();
                }

            }
        });
        Details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsCardView.setVisibility(View.VISIBLE);
                optionsCardView.setVisibility(View.GONE);

                DatabaseReference refUser = database.getReference(mAuth.getCurrentUser().getUid());
                refUser.child("User Details").addValueEventListener(new ValueEventListener() {

                    userInformation info = new userInformation();
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        info = snapshot.getValue(userInformation.class);
                        if(snapshot.exists()) {
                            Weight.setText(info.getUserWeight());
                            Height.setText(info.getUserHeight());
                            Age2.setText(info.getUserAge());
                            Calories.setText(info.getIntakeOfCalories());
                            goalWeight.setText(info.getWeightGoal());
                            goalCalorieIntake.setText(info.getCalorieGoal());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                detailsCardView.setVisibility(View.VISIBLE);
                profileCardView.setVisibility(View.GONE);
                DatabaseReference refUser = database.getReference(mAuth.getCurrentUser().getUid());
                refUser.child("User Details").addValueEventListener(new ValueEventListener() {

                    userInformation info = new userInformation();
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        info = snapshot.getValue(userInformation.class);
                        if(snapshot.exists()) {
                            Weight.setText(info.getUserWeight());
                            Height.setText(info.getUserHeight());
                            Age2.setText(info.getUserAge());
                            Calories.setText(info.getIntakeOfCalories());
                            goalWeight.setText(info.getWeightGoal());
                            goalCalorieIntake.setText(info.getCalorieGoal());
                            genderStore = info.getGender();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsCardView.setVisibility(View.GONE);
                optionsCardView.setVisibility(View.VISIBLE);

            }
        });
        dailyFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Sorry this feature is currently unavailable", Toast.LENGTH_SHORT).show();
            }
        });
        dailyWeights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dailyCardView.setVisibility(View.VISIBLE);
                optionsCardView.setVisibility(View.GONE);
            }
        });
        cancelWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dailyCardView.setVisibility(View.GONE);
                optionsCardView.setVisibility(View.VISIBLE);
            }
        });
        addWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat date = new SimpleDateFormat("dd MM yyyy");
                SimpleDateFormat date2 = new SimpleDateFormat("dd/MM/yyyy");
                String curWeight = currentWeight.getText().toString();
                String curDate = date.format(new Date(currentDate.getDate()));
                String curDateStore = date2.format(new Date(currentDate.getDate()));
                DatabaseReference refUser = database.getReference(mAuth.getCurrentUser().getUid());
                userStorage Storage = new userStorage(curWeight,curDateStore);
                if(curWeight.isEmpty() )
                {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    refUser.child("Users Daily weight").child(curDate).setValue(Storage);
                    Toast.makeText(MainActivity.this, "Stored weight " + curWeight + "Kg on: " + curDateStore, Toast.LENGTH_SHORT).show();
                    dailyCardView.setVisibility(View.GONE);
                    optionsCardView.setVisibility(View.VISIBLE);
                }
            }
        });

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionsCardView.setVisibility(View.GONE);
                profileCardView.setVisibility(View.VISIBLE);

                DatabaseReference refUser = database.getReference(mAuth.getCurrentUser().getUid());
                refUser.child("User Details").addValueEventListener(new ValueEventListener() {

                    userInformation info = new userInformation();
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        info = snapshot.getValue(userInformation.class);
                        if(snapshot.exists()) {
                            if(formatSystem == "Metric") {
                                startingWeight.setText("Starter Weight: " + info.getUserWeight() + "Kg");
                                currentHeight.setText("Height: " + info.getUserHeight() + "cm");
                                Age.setText("Age: " + info.getUserAge());
                                Genders.setText("Gender: " + info.getGender());
                                calorieIntake.setText("Calorie Intake: " + info.getIntakeOfCalories() + " Calories");
                                goalWeight.setText("Goal Weight: " + info.getWeightGoal() + "Kg");
                                goalCalorieIntake.setText("Goal Calorie Intake: " + info.getCalorieGoal() + " Calories");
                            }
                            else if(formatSystem == "Imperial") {
                                startingWeight.setText("Starter Weight: " + info.getUserWeight() + "lb");
                                currentHeight.setText("Height: " + info.getUserHeight() + " Inches");
                                Age.setText("Age: " + info.getUserAge());
                                Genders.setText("Gender: " + info.getGender());
                                calorieIntake.setText("Calorie Intake: " + info.getIntakeOfCalories() + " Calories");
                                goalWeight.setText("Goal Weight: " + info.getWeightGoal() + "lb");
                                goalCalorieIntake.setText("Goal Calorie Intake: " + info.getCalorieGoal() + " Calories");
                            }
                        }
                        else{
                            startingWeight.setText("Starter Weight: No Values Stored" );
                            currentHeight.setText("Height: No Values Stored ");
                            Age.setText("Age:No Values Stored" );
                            Genders.setText("Gender: No Values Stored" );
                            calorieIntake.setText("Calorie Intake: No Values Stored");
                            goalWeight.setText("Goal Weight: No Values Stored " );
                            goalCalorieIntake.setText("Goal Calorie Intake: No Values Stored ");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        returnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionsCardView.setVisibility(View.VISIBLE);
                profileCardView.setVisibility(View.GONE);
            }
        });
        Converter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Converter.isChecked()){
                    formatSystem = "Metric";
                    Converter.setText(formatSystem);
                    String metricWeight=Weight.getText().toString().trim();
                    String metricHeight=Height.getText().toString().trim();
                    String metricGWeight=GoalWeight.getText().toString().trim();
                    if(!metricWeight.isEmpty() && !metricHeight.isEmpty() && !metricGWeight.isEmpty()) {
                        Weight.setText(String.valueOf(Double.parseDouble(metricWeight) / 2.20462));
                        Height.setText(String.valueOf(Double.parseDouble(metricHeight) / 0.3937));
                        goalWeight.setText(String.valueOf(Double.parseDouble(metricGWeight) / 2.20462));
                    }
                    detailsWeight.setText("Kg");
                    detailsHeight.setText("cm");
                    detailsGoalWeight.setText("Kg");
                }
                else if(Converter.isChecked()){
                    formatSystem = "Imperial";
                    Converter.setText(formatSystem);
                    String imperialWeight=Weight.getText().toString().trim();
                    String imperialHeight=Height.getText().toString().trim();
                    String imperialGWeight=GoalWeight.getText().toString().trim();
                    if(!imperialWeight.isEmpty() && !imperialHeight.isEmpty() && !imperialGWeight.isEmpty()) {
                        Weight.setText(String.valueOf(Double.parseDouble(imperialWeight) * 2.20462));
                        Height.setText(String.valueOf(Double.parseDouble(imperialHeight) * 0.3937));
                        goalWeight.setText(String.valueOf(Double.parseDouble(imperialGWeight) * 2.20462));
                    }
                    detailsWeight.setText("lb");
                    detailsHeight.setText("Inches");
                    detailsGoalWeight.setText("lb");
                }
            }
        });
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userWeight = Weight.getText().toString().trim();
                String userHeight = Height.getText().toString();
                String userAge = Age2.getText().toString();
                String userCalorie = Calories.getText().toString();
                String userGWeight = GoalWeight.getText().toString();
                String userGCalorie = GoalCalories.getText().toString();
                if(Other.isChecked())
                {
                    Gender = txtOther.getText().toString();
                }
                DatabaseReference refUser = database.getReference(mAuth.getCurrentUser().getUid());
                userInformation info = new userInformation(userWeight, userHeight, userAge, userCalorie, userGWeight, userGCalorie, Gender,formatSystem);

                if(userWeight.isEmpty() || userHeight.isEmpty() || userAge.isEmpty() || userCalorie.isEmpty() || userGWeight.isEmpty()||userGCalorie.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    refUser.child("User Details").setValue(info);
                    Toast.makeText(MainActivity.this, "Your Info has been Added/Updated", Toast.LENGTH_SHORT).show();
                    detailsCardView.setVisibility(View.GONE);
                    optionsCardView.setVisibility(View.VISIBLE);
                }


            }
        });
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        if(loggedIn != null)
        {
            Toast.makeText(this, "You Are logged in dummy"+ loggedIn.getEmail(), Toast.LENGTH_SHORT).show();
        }
    }


}