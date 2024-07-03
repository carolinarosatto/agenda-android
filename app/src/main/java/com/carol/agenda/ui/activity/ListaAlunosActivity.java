package com.carol.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.carol.agenda.R;
import com.carol.agenda.dao.AlunoDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaAlunosActivity extends AppCompatActivity {

    private final AlunoDAO alunoDAO = new AlunoDAO();
    public static final String TITULO_APPBAR_LISTA = "Lista de alunos";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR_LISTA);
        configuraBotaoNovoAluno();
    }

    private void configuraBotaoNovoAluno() {
        FloatingActionButton botaoAdicionarAluno = findViewById(R.id.activity_novo_aluno_fab);
        botaoAdicionarAluno.setOnClickListener(abreFormularioAlunoActivity());
    }

    private View.OnClickListener abreFormularioAlunoActivity() {
        return v -> startActivity(new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_lv);
        configuraLista(listaDeAlunos);
    }

    private void configuraLista(ListView listaDeAlunos) {
        listaDeAlunos.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                alunoDAO.getTodos()));
    }
}
