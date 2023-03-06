package sv.edu.udb.lifecycleapp.RS181977;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(new MyLifeCycleObserver(this));
        setContentView(R.layout.activity_main);
    }
}