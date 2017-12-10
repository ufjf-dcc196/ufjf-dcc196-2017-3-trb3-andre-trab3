package com.example.desenvolvedor.dcc196_trabalho3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.desenvolvedor.dcc196_trabalho3.DAO.TagDAO;
import com.example.desenvolvedor.dcc196_trabalho3.DAO.TarefaDAO;
import com.example.desenvolvedor.dcc196_trabalho3.Modelo.Tag;

public class CadastroTag extends AppCompatActivity {


    private EditText edtNomeTag;
    private Button btnSalvar;
    private ListView lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_tag);



        lista = (ListView) findViewById(R.id.ListaTag2);




        UpdateListaTarefas();
        Button btnSalvar = (Button) findViewById(R.id.btnsss);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tag tag= (Tag) parent.getItemAtPosition(position);
                Intent intent1 = new Intent(CadastroTag.this, ViewTarefasDaTag.class);
                intent1.putExtra("TAG",tag.getId() );
                startActivity(intent1);
            }
        });






        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tag tag = new Tag();

                EditText etTag = (EditText) findViewById(R.id.edtNomeTag);
                tag.setTag(etTag.getText().toString());

                TagDAO tagDAO = new TagDAO(getApplicationContext());
                tagDAO.inserirTag(tag);
                tagDAO.close();

                Toast.makeText(getApplicationContext(), "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
                UpdateListaTarefas();

            }
        });
    }


    private void UpdateListaTarefas() {
        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

        TagDAO tagDAO=new TagDAO(getApplicationContext());


        ArrayAdapter<Tag> taga = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tagDAO.getTodasTags() );
        tagDAO.close();
        lista.setAdapter(taga);
    }
}