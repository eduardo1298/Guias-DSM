package udb.edu.sv.retrofit_githubapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button logIn;
    private EditText inputUserName;
    private Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logIn =  findViewById(R.id.btn_login);
        inputUserName =  findViewById(R.id.input_username);


    }

    public void getUser(View view){
        inputUserName.setError(null);
        if ("".equals(inputUserName.getText().toString())) {
            inputUserName.setError("error. enter your name");
            inputUserName.requestFocus();
            return;
        }
        i = new Intent(MainActivity.this, UserActivity.class);
        i.putExtra("STRING_I_NEED", inputUserName.getText().toString());
        startActivity(i);
    }
}