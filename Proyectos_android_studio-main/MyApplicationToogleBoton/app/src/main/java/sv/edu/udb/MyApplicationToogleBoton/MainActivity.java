package sv.edu.udb.MyApplicationToogleBoton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private ToggleButton btnToggler;
    private TextView txtResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
  txtResultado=(TextView)findViewById(R.id.txtMensajeID);
  btnToggler=(ToggleButton)findViewById(R.id.toggleButtonID);

  btnToggler.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
          if(isChecked==false){
            txtResultado.setVisibility(View.VISIBLE);
          }else{
              txtResultado.setVisibility(View.INVISIBLE);
          }
      }
  });
    }
}