package jason.parse_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.ParseInstallation;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        Parse.initialize(new Parse.Configuration.Builder(this)
            .applicationId(getString(R.string.back4app_app_id))
            .server(getString(R.string.back4app_server_url))
            .build()
        );*/
        Parse.initialize(this);

        // Save the current Installation to Back4App
        ParseInstallation.getCurrentInstallation().saveInBackground();

        // find text
        username = findViewById(R.id.user_name_input);
        password = findViewById(R.id.pw_input);

        final Button sign_up = findViewById(R.id.to_sign_up_button);
        final Button sign_in = findViewById(R.id.sign_in_button);

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TextView show = findViewById(R.id.on_click_text);
                //counter += 1;
                //show.setText(counter+"");
                if (isEmpty(username) || isEmpty(password))
                    return;

                ParseUser.logInInBackground(username.getText().toString().trim(),
                    password.getText().toString().trim(),
                    new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user != null) {
                                Toast.makeText(MainActivity.this, "Successfully login!",
                                        Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(MainActivity.this, LogOut.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                            else {
                                ParseUser.logOut();
                                Toast.makeText(MainActivity.this, e.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });


    }

    boolean isEmpty(EditText text) {
        if (text.getText().toString().trim().length() > 0)
            return false;
        else
            return true;
    }
}
