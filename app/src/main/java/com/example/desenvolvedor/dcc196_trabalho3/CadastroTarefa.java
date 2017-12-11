package com.example.desenvolvedor.dcc196_trabalho3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.desenvolvedor.dcc196_trabalho3.DAO.RelacaoDAO;
import com.example.desenvolvedor.dcc196_trabalho3.DAO.TagDAO;
import com.example.desenvolvedor.dcc196_trabalho3.DAO.TarefaDAO;
import com.example.desenvolvedor.dcc196_trabalho3.Modelo.Tag;
import com.example.desenvolvedor.dcc196_trabalho3.Modelo.Tarefa;

import java.util.ArrayList;

public class CadastroTarefa extends AppCompatActivity {



    private ListView listaTag;


    private EditText edtTitulo;
    private EditText edtDescricao;
    private Spinner spDificuldade;
    private Spinner spEstado;
    private Button btnSalvar;
    private Button btnSalvarTag;
    private Button btnExcluir;
    private Spinner spTag;


    public Tarefa tarefa;




    private int auxiliarALterar=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_tarefa);

        edtTitulo= (EditText) findViewById(R.id.txtTitulo);
        edtDescricao= (EditText) findViewById(R.id.txtDescricao);

        spDificuldade =(Spinner) findViewById(R.id.spDificuldade);
        spEstado =(Spinner) findViewById(R.id.spEstado);
        spTag=(Spinner) findViewById(R.id.spTag);

        btnSalvar= (Button) findViewById(R.id.btnSalvar);
        btnSalvarTag= (Button) findViewById(R.id.btnTag);
        btnExcluir= (Button) findViewById(R.id.btnExcluir);
        btnExcluir.setEnabled(false);
        listaTag = (ListView) findViewById(R.id.taglist);
        btnSalvarTag.setEnabled(false);


        TagDAO tagDAO=new TagDAO(getApplicationContext());
        ArrayAdapter<Tag> taga = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tagDAO.getTodasTags() );
        tagDAO.close();
        spTag.setAdapter(taga);



        String[] estados = {"A fazer", "Em execução", "Bloqueada", "Concluída"};
        ArrayAdapter<String> adapterEstado = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, estados);
        spEstado.setAdapter(adapterEstado);


        Integer[] dificudade = {1,2,3, 4,5};
        ArrayAdapter<Integer> adapterDificudade = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, dificudade);
        spDificuldade.setAdapter(adapterDificudade);

        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

        tarefa = new Tarefa();

        if(getIntent().getSerializableExtra("TAREFA") != null){

            ArrayList<Tarefa> tarefas= tarefaDAO.GetTodasTarefas();
            for(int i=0; i<tarefas.size();i++) {

                 if(tarefas.get(i).getId()==getIntent().getIntExtra("TAREFA",1)){

                     tarefa =tarefas.get(i);
                    edtTitulo.setText(tarefa.getTitulo());
                    edtDescricao.setText(tarefa.getDescricao());

                    spEstado.setSelection(adapterEstado.getPosition(tarefa.getEstado()));
                    spDificuldade.setSelection(adapterDificudade.getPosition(tarefa.getDificuldade()));
                    auxiliarALterar=-1;
                    btnExcluir.setEnabled(true);
                     btnSalvarTag.setEnabled(true);

                }
            }


        }
        if(auxiliarALterar==-1){
            updatelistaTagUtilizadas();
        }


        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RelacaoDAO relacaoDAO= new RelacaoDAO(getApplicationContext());
                relacaoDAO.removerRelacao(tarefa.getId());


                relacaoDAO.close();

                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

                tarefaDAO.removerTarefa(tarefa);

                tarefaDAO.close(); Tarefa tarefa= new Tarefa();
                Toast.makeText(getApplicationContext(), "Tarefa excluida '"+tarefa.getTitulo()+"' excluida com sucesso", Toast.LENGTH_LONG).show();
                finish();

            }
        });
        btnSalvarTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RelacaoDAO relacaoDAO= new RelacaoDAO(getApplicationContext());
                relacaoDAO.inserirAtribuicao(tarefa,(Tag)spTag.getSelectedItem());
                if(auxiliarALterar==-1) {
                    updatelistaTagUtilizadas();
                }
                relacaoDAO.close();
            }
        });


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tarefa tarefa= new Tarefa();
                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

                if(auxiliarALterar!=-1){

                    tarefa.setTitulo(edtTitulo.getText().toString());
                    tarefa.setDescricao(edtDescricao.getText().toString());
                    tarefa.setDificuldade(spDificuldade.getSelectedItem().hashCode());
                    tarefa.setEstado(spEstado.getSelectedItem().toString());
                    tarefaDAO.inserirTarefa(tarefa);




                    Toast.makeText(getApplicationContext(), "Tarefa salva com Sucesso!", Toast.LENGTH_LONG).show();
            }
                    else {


                    CadastroTarefa.this.tarefa.setTitulo(edtTitulo.getText().toString());
                    CadastroTarefa.this.tarefa.setDescricao(edtDescricao.getText().toString());
                    CadastroTarefa.this.tarefa.setDificuldade(spDificuldade.getSelectedItem().hashCode());
                    CadastroTarefa.this.tarefa.setEstado(spEstado.getSelectedItem().toString());
                        tarefaDAO.alterarTarefa(CadastroTarefa.this.tarefa);
                    }
                    tarefaDAO.close();

                Toast.makeText(getApplicationContext(), "Tarefa alterada com Sucesso!", Toast.LENGTH_LONG).show();

                finish();

            }
        });

    }


    private void updatelistaTagUtilizadas() {
        RelacaoDAO relacaoDAO= new RelacaoDAO(getApplicationContext());

        ArrayList<Integer> tags = relacaoDAO.getTag(this.tarefa.getId());

        ArrayList<String> nomeTarefa=new ArrayList<>();

        TagDAO tagDAO= new TagDAO(getBaseContext());
        ArrayList<Tag> tarefas= tagDAO.getTodasTags();

        for(int i=0; i<tarefas.size();i++) {
            for(int j=0; j<tags.size();j++) {

                if(tarefas.get(i).getId()==tags.get(j)) {
                    nomeTarefa.add(tarefas.get(i).getTag());

                }
            }
        }

        tagDAO.close();

        relacaoDAO.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,nomeTarefa);
        listaTag.setAdapter(adapter);
    }




}
