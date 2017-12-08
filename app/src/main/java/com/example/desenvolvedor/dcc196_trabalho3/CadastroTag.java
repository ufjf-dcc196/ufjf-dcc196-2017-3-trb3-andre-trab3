package com.example.desenvolvedor.dcc196_trabalho3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.desenvolvedor.dcc196_trabalho3.DAO.TagDAO;
import com.example.desenvolvedor.dcc196_trabalho3.Modelo.Tag;

public class CadastroTag extends AppCompatActivity {


    private EditText edtNomeTag;
    private Button btnSalvar;

    private ListView listaTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_tag);

        Button btnSalvar = (Button) findViewById(R.id.btnsss);

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

                finish();
            }
        });
    }


}