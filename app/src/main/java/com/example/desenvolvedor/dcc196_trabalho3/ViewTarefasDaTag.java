package com.example.desenvolvedor.dcc196_trabalho3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.desenvolvedor.dcc196_trabalho3.DAO.RelacaoDAO;
import com.example.desenvolvedor.dcc196_trabalho3.DAO.TagDAO;
import com.example.desenvolvedor.dcc196_trabalho3.DAO.TarefaDAO;
import com.example.desenvolvedor.dcc196_trabalho3.Modelo.Tag;
import com.example.desenvolvedor.dcc196_trabalho3.Modelo.Tarefa;

import java.util.ArrayList;

public class ViewTarefasDaTag extends AppCompatActivity {


    private TextView nomeTag;
    private ListView listaTag1;
    private Tag tag;

    int tagInt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tarefas_da_tag);
        listaTag1 = (ListView) findViewById(R.id.ListaTarefa11);

        nomeTag= (TextView) findViewById(R.id.nomeTagLista);





        tag = new Tag();



        if(getIntent().getSerializableExtra("TAG") != null){

            TagDAO tagDAO= new TagDAO(getBaseContext());
            ArrayList<Tag> tags;
            tags= tagDAO.getTodasTags();

            for(int i=0; i<tags.size();i++) {

                if(tags.get(i).getId()==getIntent().getIntExtra("TAG",1)){

                    tag=tags.get(i);
                    nomeTag.setText(tag.getTag());
                    tagInt= tag.getId();


                }
            }
        tagDAO.close();

        }
        updatelistaTarefaUtilizadas();


    }


    private void updatelistaTarefaUtilizadas() {
        RelacaoDAO relacaoDAO= new RelacaoDAO(getApplicationContext());
        TagDAO tagDAO= new TagDAO(getBaseContext());
        ArrayList<String> nomeTarefa=new ArrayList<>();
       // Toast.makeText(getApplicationContext(), "Tarefa salva com Sucesso"+tag.getId(), Toast.LENGTH_LONG).show();

        ArrayList<Integer> tarefasDaTagEspecificada = relacaoDAO.getTarefa(tagInt);



        TarefaDAO TarefaDAO= new TarefaDAO(getBaseContext());
        ArrayList<Tarefa> tarefas= TarefaDAO.GetTodasTarefas();

        for(int i=0; i<tarefas.size();i++) {
            for(int j=0; j<tarefasDaTagEspecificada.size();j++) {

                if(tarefas.get(i).getId()==tarefasDaTagEspecificada.get(j)) {
                    nomeTarefa.add(tarefas.get(i).getTitulo());
                }
            }
        }




        tagDAO.close();

        relacaoDAO.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,nomeTarefa);
        listaTag1.setAdapter(adapter);
    }





}
