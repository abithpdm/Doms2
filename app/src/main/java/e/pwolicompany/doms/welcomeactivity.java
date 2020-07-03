package e.pwolicompany.doms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class welcomeactivity extends AppCompatActivity {
Button loginbtn;
TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomeactivity);
        loginbtn=findViewById(R.id.welcomeloginbtn);
        signup=findViewById(R.id.welcomesignup);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginint = new Intent(welcomeactivity.this,SigninActivity.class);
                startActivity(loginint);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(welcomeactivity.this,MainActivity.class);
                startActivity(signup);
            }
        });
    }
}
