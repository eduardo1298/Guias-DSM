package sv.edu.udb.dsm.guia3.appcalculadora.RS181977;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView tvCero, tvUno, tvDos, tvTres, tvCuatro, tvCinco, tvSeis, tvSiete, tvOcho,
            tvNueve, tvPunto, tvIgual, tvSuma, tvResta, tvMulti, tvDiv, tvLimpiar, tvResultado, tvValores;
    double numero1, numero2, resultado;
    boolean suma, resta, multiplicacion, division;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Botones del 0 al 9
        tvCero = (TextView) findViewById(R.id.cero);
        tvUno = (TextView) findViewById(R.id.numero_1);
        tvDos = (TextView) findViewById(R.id.numero_2);
        tvTres = (TextView) findViewById(R.id.numero_3);
        tvCuatro = (TextView) findViewById(R.id.numero_4);
        tvCinco = (TextView) findViewById(R.id.numero_5);
        tvSeis = (TextView) findViewById(R.id.numero_6);
        tvSiete = (TextView) findViewById(R.id.numero_7);
        tvOcho = (TextView) findViewById(R.id.numero_8);
        tvNueve = (TextView) findViewById(R.id.numero_9);

        tvPunto = (TextView) findViewById(R.id.punto); //Botón del punto(.)
        tvLimpiar = (TextView) findViewById(R.id.limpiar); //Botón C
        //Botones de operaciones e igual
        tvIgual = (TextView) findViewById(R.id.signo_igual);
        tvSuma = (TextView) findViewById(R.id.signo_mas);
        tvResta = (TextView) findViewById(R.id.signo_menos);
        tvMulti = (TextView) findViewById(R.id.signo_por);
        tvDiv = (TextView) findViewById(R.id.signo_division);

        //Textview que muestran los operadores y operando
        tvResultado = (TextView) findViewById(R.id.PantallaResultado);
        tvValores = (TextView) findViewById(R.id.PantallaValores);
        //Desactivamos las operaciones
        desactivaroperaciones();

        tvCero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResultado.setText(tvResultado.getText() + "0");
                activaroperaciones();
            }
        });

        tvUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResultado.setText(tvResultado.getText() + "1");
                activaroperaciones();
            }
        });

        tvDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResultado.setText(tvResultado.getText() + "2");
                activaroperaciones();
            }
        });

        tvTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResultado.setText(tvResultado.getText() + "3");
                activaroperaciones();
            }
        });

        tvCuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResultado.setText(tvResultado.getText() + "4");
                activaroperaciones();
            }
        });

        tvCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResultado.setText(tvResultado.getText() + "5");
                activaroperaciones();
            }
        });

        tvSeis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResultado.setText(tvResultado.getText() + "6");
                activaroperaciones();
            }
        });

        tvSiete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResultado.setText(tvResultado.getText() + "7");
                activaroperaciones();
            }
        });

        tvOcho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResultado.setText(tvResultado.getText() + "8");
                activaroperaciones();
            }
        });

        tvNueve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResultado.setText(tvResultado.getText() + "9");
                activaroperaciones();
            }
        });

        tvPunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResultado.setText(tvResultado.getText() + ".");
                desactivarpunto();
            }
        });

        tvLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvValores.setText("");
                tvResultado.setText("");
                activarnumerosyoperadores();
                desactivaroperaciones();

            }
        });

        tvSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvResultado.getText() != null) {
                    numero1 = Float.parseFloat(tvResultado.getText() + "");
                    suma = true;
                }
                tvValores.setText(tvResultado.getText() + " +");
                tvResultado.setText(null);
                activarpunto();
            }
        });

        tvResta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(tvResultado.getText())) {
                    numero1 = Float.parseFloat(tvResultado.getText() + "");
                    resta = true;
                    tvValores.setText(tvResultado.getText() + " -");
                    tvResultado.setText(null);
                    activarpunto();
                    tvResta.setEnabled(false);
                }else{
                    tvResultado.setText(tvResultado.getText() + " -");
                    tvResta.setEnabled(false);
                }

            }
        });

        tvMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvResultado.getText() != null) {
                    numero1 = Float.parseFloat(tvResultado.getText() + "");
                    multiplicacion = true;
                }
                tvValores.setText(tvResultado.getText() + " *");
                tvResultado.setText(null);
                activarpunto();
            }
        });

        tvDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvResultado.getText() != null) {
                    numero1 = Float.parseFloat(tvResultado.getText() + "");
                    division = true;
                }
                tvValores.setText(tvResultado.getText() + " /");
                tvResultado.setText(null);
                activarpunto();
            }
        });


        tvIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvResultado.getText() == "" || tvValores.getText() == "") {
                    tvResultado.setText("ERROR");
                    tvValores.setText("Presione C para continuar");
                    desactivarnumerosyoperadores();
                } else {
                    if (suma == true || resta == true || multiplicacion == true || division == true) {
                        desactivaroperaciones();
                        activarpunto();
                        if (tvResultado.getText() != null) {
                            numero2 = Float.parseFloat(tvResultado.getText() + "");
                            tvValores.setText(tvValores.getText() + " " + tvResultado.getText());
                            //Dar formato a los número decimales
                            DecimalFormat formato = new DecimalFormat();
                            formato.setMaximumFractionDigits(2); //Numero maximo de decimales a mostrar
                            if (suma == true) {
                                resultado = numero1 + numero2;
                                tvResultado.setText(formato.format(resultado) + "");
                                suma = false;

                            }

                            if (resta == true) {
                                resultado = numero1 - numero2;
                                tvResultado.setText(formato.format(resultado) + "");
                                resta = false;
                            }

                            if (multiplicacion == true) {
                                resultado = numero1 * numero2;
                                tvResultado.setText(formato.format(resultado) + "");
                                multiplicacion = false;
                            }

                            if (division == true) {
                                if (numero2 != 0) {
                                    resultado = numero1 / numero2;
                                    tvResultado.setText(formato.format(resultado) + "");
                                } else {
                                    tvResultado.setText("Infinito");
                                }
                                division = false;
                            }
                            activaroperaciones();
                            numero2 = 0;
                            numero1 = resultado;

                        }

                    }
                }
            }
        });

    }

    public void activarpunto() {

        tvPunto.setEnabled(true);
    }

    public void desactivarpunto() {
        tvPunto.setEnabled(false);
    }

    public void activaroperaciones() {
        tvSuma.setEnabled(true);
        tvResta.setEnabled(true);
        tvMulti.setEnabled(true);
        tvDiv.setEnabled(true);

    }

    public void desactivaroperaciones() {
        tvSuma.setEnabled(false);
        tvMulti.setEnabled(false);
        tvDiv.setEnabled(false);

    }

    public void activarnumerosyoperadores() {
        tvSuma.setEnabled(true);
        tvResta.setEnabled(true);
        tvMulti.setEnabled(true);
        tvDiv.setEnabled(true);
        tvPunto.setEnabled(true);
        tvCero.setEnabled(true);
        tvUno.setEnabled(true);
        tvDos.setEnabled(true);
        tvTres.setEnabled(true);
        tvCuatro.setEnabled(true);
        tvCinco.setEnabled(true);
        tvSeis.setEnabled(true);
        tvSiete.setEnabled(true);
        tvOcho.setEnabled(true);
        tvNueve.setEnabled(true);
        tvIgual.setEnabled(true);
    }

    public void desactivarnumerosyoperadores() {
        tvSuma.setEnabled(false);
        tvResta.setEnabled(false);
        tvMulti.setEnabled(false);
        tvDiv.setEnabled(false);
        tvPunto.setEnabled(false);
        tvCero.setEnabled(false);
        tvUno.setEnabled(false);
        tvDos.setEnabled(false);
        tvTres.setEnabled(false);
        tvCuatro.setEnabled(false);
        tvCinco.setEnabled(false);
        tvSeis.setEnabled(false);
        tvSiete.setEnabled(false);
        tvOcho.setEnabled(false);
        tvNueve.setEnabled(false);
        tvIgual.setEnabled(false);
    }


}