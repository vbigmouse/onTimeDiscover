package jason.parse_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

/**
 * Created by vbigmouse on 2018/2/13.
 */

public class LogOut extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_out);

        Parse.initialize(this);

        final EditText score_v = findViewById(R.id.enter_score);

        final Button save_score = findViewById(R.id.save_score);
        save_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(score_v)) {
                    Toast.makeText(LogOut.this, "Please Enter Your Score!", Toast.LENGTH_LONG).show();
                    return;
                }

                final ParseObject Score = new ParseObject("Score");
                Score.put("score", Integer.parseInt(score_v.getText().toString()));
                Score.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(LogOut.this, "Score saved!", Toast.LENGTH_LONG).show();
                            String ScoreID = Score.getObjectId();
                        }
                        else {
                            Toast.makeText(LogOut.this, "Score save fail!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        final Button logout = findViewById(R.id.log_out);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                Toast.makeText(LogOut.this, "You Log Out", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LogOut.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
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
