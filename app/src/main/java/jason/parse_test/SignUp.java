package jason.parse_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by vbigmouse on 2018/2/13.
 */

public class SignUp extends AppCompatActivity {
    private EditText username;
    private EditText passwowrd;
    private EditText conf_password;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        // initialize parse
        Parse.initialize(this);

        // find input view
        username = findViewById(R.id.user_name_input);
        passwowrd = findViewById(R.id.pw_input);
        conf_password = findViewById(R.id.pw_input_confirm);


        // find view by button id
        final Button SignUp = findViewById(R.id.sign_up_button);
        final Button Cancel= findViewById(R.id.to_main_button);

        Cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
            Intent intent = new Intent(SignUp.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // check input data
                if (isEmpty(username) || isEmpty(passwowrd) || isEmpty(conf_password)) {
                    Toast.makeText(SignUp.this, "Please Enter User Name/Password!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!isMatch(passwowrd, conf_password)) {
                    Toast.makeText(SignUp.this, "Password not match!", Toast.LENGTH_LONG).show();
                    return;
                }

                // Use Parse User library
                ParseUser user = new ParseUser();
                user.setUsername(username.getText().toString().trim());
                user.setPassword(passwowrd.getText().toString().trim());
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(SignUp.this, "Sign up success!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SignUp.this, LogOut.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                        else {
                            ParseUser.logOut();
                            Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                });

            }
        });


    }

    boolean isMatch(EditText text1, EditText text2) {
        if (text1.getText().toString().trim().equals(text2.getText().toString().trim()))
            return true;
        else
            return false;
    }

    boolean isEmpty(EditText text) {
        if (text.getText().toString().trim().length() > 0)
            return false;
        else
            return true;
    }
}
