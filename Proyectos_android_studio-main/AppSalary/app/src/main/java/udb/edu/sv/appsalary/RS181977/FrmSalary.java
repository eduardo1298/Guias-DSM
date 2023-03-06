package udb.edu.sv.appsalary.RS181977;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;


public class FrmSalary extends AppCompatActivity {
    Double ISSS,AFP,RENTA,SalarioNeto,Horas,Salario;
    String TxtNombre;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_salary);

        TextView Mensaje=(TextView) findViewById(R.id.TvMensaje);
        TextView TvSalario=(TextView) findViewById(R.id.Tv_Salario);
        TextView TvSalarioNeto=(TextView) findViewById(R.id.Tv_SalarioNeto);
        TextView TvAFP=(TextView) findViewById(R.id.Tv_AFP);
        TextView TvISSS=(TextView) findViewById(R.id.Tv_ISSS);
        TextView TvRenta=(TextView) findViewById(R.id.Tv_Renta);
        Button BtnRegresar=(Button) findViewById(R.id.Btn_Regresar);
        Bundle bundle = this.getIntent().getExtras();

        TxtNombre = bundle.getString("Nombre");

        Horas= Double.parseDouble(bundle.getString("Horas"));
        Salario= Horas*8.5;
        ISSS = 0.02*Salario;
        AFP = 0.03*Salario;
        RENTA = 0.04*Salario;
        SalarioNeto = Salario - ISSS - AFP - RENTA;
        //Dar formato a los número decimales
        DecimalFormat formato = new DecimalFormat();
        formato.setMaximumFractionDigits(2); //Numero maximo de decimales a mostrar

        Mensaje.setText("Bienvenido "+TxtNombre + ", detalles de su sueldo :");

        TvSalario.setText("Salario: $"+formato.format(Salario));

        TvISSS.setText("ISSS: $"+ formato.format(ISSS));

        TvAFP.setText("AFP $"+ formato.format(AFP));

        TvRenta.setText("RENTA $"+ formato.format(RENTA));

        TvSalarioNeto.setText("Salario neto $"+formato.format(SalarioNeto));

        BtnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(FrmSalary.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}