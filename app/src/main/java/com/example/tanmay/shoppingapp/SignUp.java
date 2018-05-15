package com.example.tanmay.shoppingapp;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    Bundle step1Bundle;
    Bundle step2Bundle;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(SignUp.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(SignUp.this);
        }

        builder.setTitle("Cancel SignUp")
                .setMessage("Are you sure you don't want to Sign Up?")
                .setPositiveButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Do Nothing
                    }
                })
                .setNegativeButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        step1Bundle = new Bundle();
        step2Bundle = new Bundle();

        //And "step" other than 1 or 2 gets a new Bundle
        step1(getBundle(3));

    }

    public void step1(Bundle bundle) {

        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();

        //Inflate Step 1
        SignUpFrag1 step1 = new SignUpFrag1();
        step1.setArguments(bundle);
        transaction.replace(android.R.id.content, step1);
        transaction.commit();

    }

    public void step2(Bundle bundle) {

        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();

        //Inflate Step 2
        SignUpFrag2 step2 = new SignUpFrag2();
        step2.setArguments(bundle);
        transaction.replace(android.R.id.content, step2);
        transaction.commit();

    }

    public void setBundle(int step, Bundle bundle) {

        switch (step) {

            case 1:
                step1Bundle = bundle;
                break;
            case 2:
                step2Bundle = bundle;
                break;

        }

    }

    public Bundle getBundle(int step) {

        switch (step) {

            case 1:
                return step1Bundle;

            case 2:
                return step2Bundle;

            default:
                return new Bundle();
        }

    }

    public void signUpComplete() {

        SharedPreferences.Editor editor = getSharedPreferences("UserInformation", MODE_PRIVATE).edit();

        editor.putString("FirstName", step1Bundle.getString("firstName"));
        editor.putString("LastName", step1Bundle.getString("lastName"));
        editor.putInt("Gender", step1Bundle.getInt("gender"));
        editor.putString("UserName", step2Bundle.getString("username"));

        editor.apply();

        Toast.makeText(SignUp.this, "Welcome, " + step1Bundle.getString("firstName"), Toast.LENGTH_SHORT).show();

        finish();

    }

}
