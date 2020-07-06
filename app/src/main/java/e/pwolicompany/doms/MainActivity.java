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

public class MainActivity extends AppCompatActivity {
    Button signupbtn;
    EditText emailid,passwd;
    TextView signin;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    mFirebaseAuth= FirebaseAuth.getInstance();
    emailid=findViewById(R.id.emailtext);
    passwd=findViewById(R.id.passwrdtxt);
    signupbtn=findViewById(R.id.signupbtn);
    signin=findViewById(R.id.textView);
    signupbtn.setOnClickListener(new View.OnClickListener() {
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
                passwd.setError("Please enter password");
                passwd.requestFocus();
            }
            else if (email.isEmpty() || pswd.isEmpty())
            {
                Toast.makeText(MainActivity.this,"Please make sure you entered fields correctly",Toast.LENGTH_SHORT).show();
            }
            else if( !email.isEmpty() || !pswd.isEmpty())
            {
                mFirebaseAuth.createUserWithEmailAndPassword(email,pswd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this,"User registration failed",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            startActivity(new Intent(MainActivity.this,MapsActivity2.class));
                        }
                    }
                });
            }
            else {

                Toast.makeText(MainActivity.this,"Some error occured , please try again",Toast.LENGTH_SHORT).show();

            }
        }
    });
    signin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent signinintent = new Intent(MainActivity.this,SigninActivity.class);
            startActivity(signinintent);
        }
    });
    }
}
