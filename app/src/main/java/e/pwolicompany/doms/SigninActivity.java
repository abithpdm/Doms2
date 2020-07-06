package e.pwolicompany.doms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SigninActivity extends AppCompatActivity {
    Button signinbtn;
    EditText emailid,passwd;
    TextView signup;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mFirebaseAuth= FirebaseAuth.getInstance();
        emailid=findViewById(R.id.emailtext2);
        passwd=findViewById(R.id.passwrdtxt2);
        signinbtn=findViewById(R.id.signinbtn);
        signup=findViewById(R.id.textView);

        authStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(SigninActivity.this,"Welcome",Toast.LENGTH_SHORT).show();
                    Intent i =new Intent(SigninActivity.this,MapsActivity2.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(SigninActivity.this,"user not found please signup",Toast.LENGTH_SHORT).show();

                }
                }

            };
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailid.getText().toString();
                String pswd= passwd.getText().toString();
                if(email.isEmpty())
                {
                    emailid.setError("Please enter valid email id");
                    emailid.requestFocus();
                }
                else if (pswd.isEmpty()){
                    passwd.setError("Please enter your password");
                    passwd.requestFocus();
                }
                else if (email.isEmpty() && pswd.isEmpty())
                {

                    Toast.makeText(SigninActivity.this,"Please make sure you entered fields correctly",Toast.LENGTH_SHORT).show();
                }
                else if(! email.isEmpty() &&! pswd.isEmpty())
                {
                    mFirebaseAuth.signInWithEmailAndPassword(email,pswd).addOnCompleteListener(SigninActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(SigninActivity.this,"Login error please try gain later",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Intent toHome= new Intent(SigninActivity.this,Info.class);
                                startActivity(toHome);
                            }
                        }
                    });
                }
                else {
                        Toast.makeText(SigninActivity.this,"Some error occured , please try again",Toast.LENGTH_SHORT).show();

                 }

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backtosignup = new Intent(SigninActivity.this,MainActivity.class);
                startActivity(backtosignup);
            }
        });
        }
    }
