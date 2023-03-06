package sv.edu.udb.myapplicationrecicycleview01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import Adapter.MiAdapter;
import Model.ListItem;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView01;
    private RecyclerView.Adapter adapter01;
    private List<ListItem> listaitems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView01 = (RecyclerView) findViewById(R.id.recyclerViewID);
        recyclerView01.setHasFixedSize(true);
        recyclerView01.setLayoutManager(new LinearLayoutManager(this));

      listaitems = new ArrayList<>();

      ListItem item01=new ListItem("Desarrollo de software para móviles","DSM104");
      ListItem item02=new ListItem("Programación estructurada","PRE104");
      ListItem item03=new ListItem("Sistema operativos","SOS04");

      listaitems.add(item01);
      listaitems.add(item02);
      listaitems.add(item03);

      adapter01= new MiAdapter(this,listaitems);
      recyclerView01.setAdapter(adapter01);
    }
}