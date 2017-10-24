package com.example.thjen.userfirebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText et_email,et_sgiemail;
    EditText et_pass, et_sgipass;
    Button bt_dk, bt_sgi;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        FindView();

        bt_dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateEmail();
            }
        });

        bt_sgi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignIn();
            }
        });

    }

    private void SignIn() {

        String email = et_sgiemail.getText().toString();
        String pass = et_sgipass.getText().toString();

        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if ( task.isSuccessful() ) {
                    Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "False", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void CreateEmail() {

        String email = et_email.getText().toString();
        String pass = et_pass.getText().toString();

        auth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if ( task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "False", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    private void FindView() {
        et_email = (EditText) findViewById(R.id.et_email);
        et_pass = (EditText) findViewById(R.id.et_pass);
        bt_dk = (Button) findViewById(R.id.bt_dk);
        et_sgiemail = (EditText) findViewById(R.id.et_sgiemail);
        et_sgipass = (EditText) findViewById(R.id.et_sgipass);
        bt_sgi = (Button) findViewById(R.id.bt_sgi);
    }

}
