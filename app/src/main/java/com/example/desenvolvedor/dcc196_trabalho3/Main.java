package com.example.desenvolvedor.dcc196_trabalho3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.desenvolvedor.dcc196_trabalho3.DAO.TarefaDAO;
import com.example.desenvolvedor.dcc196_trabalho3.Modelo.Tarefa;

import java.util.ArrayList;

public class Main extends AppCompatActivity {

    private ListView lista;
    private Button btnInserirTarefa;
    private Button btnTag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista= (ListView) findViewById(R.id.listViewTarefas);
        registerForContextMenu(lista);
        UpdateListaTarefas();


        btnInserirTarefa=  (Button) findViewById(R.id.btnInserirTarefa);
        btnTag=  (Button) findViewById(R.id.btnTag);


        btnInserirTarefa.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          Intent intent = new Intent(getBaseContext(), CadastroTarefa.class);
          startActivity(intent);
      }
        });






        btnTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), CadastroTag.class);
                startActivity(intent);
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tarefa t= (Tarefa) parent.getItemAtPosition(position);
                Intent intent1 = new Intent(Main.this, CadastroTarefa.class);
                intent1.putExtra("TAREFA", t.getId());
                startActivity(intent1);
            }
        });

    }

    protected void onResume() {
        super.onResume();

        UpdateListaTarefas();
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tarefa t= (Tarefa) parent.getItemAtPosition(position);
                Intent intent1 = new Intent(Main.this, CadastroTarefa.class);
                intent1.putExtra("TAREFA", t.getId());
                startActivity(intent1);
            }
        });
    }




    private void UpdateListaTarefas() {
        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

        ArrayList<Tarefa> tarefas = tarefaDAO.GetTodasTarefas();
        tarefaDAO.close();

        ArrayAdapter<Tarefa> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tarefas);
        lista.setAdapter(adapter);
    }
}
