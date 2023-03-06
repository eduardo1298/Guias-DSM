package sv.edu.udb.contadorapp.RS181977;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData;

public class MainViewModel extends ViewModel {

    private MutableLiveData<Integer> contador;

    public MutableLiveData<Integer> ObtenerContador() {
        if (contador == null) {
            contador = new MutableLiveData<>();
            contador.setValue(0);
        }
        return contador;
    }

    public void AgregarContador() {
        contador.setValue(contador.getValue() + 1);
        if (contador.getValue() != null) {
            if (contador.getValue() > 9) {
                contador.setValue(0);
            }
        }
    }

}
