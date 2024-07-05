package com.carol.agenda.ui.activity;

import static com.carol.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.carol.agenda.R;
import com.carol.agenda.dao.AlunoDAO;
import com.carol.agenda.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {

    private final AlunoDAO alunoDAO = new AlunoDAO();
    public static final String TITULO_APPBAR_LISTA = "Lista de alunos";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR_LISTA);
        configuraBotaoNovoAluno();
        alunoDAO.salvar(new Aluno("Carol", "2321412414", "carol@carol.com.br"));
        alunoDAO.salvar(new Aluno("Berenilde", "2321412414", "berenilde@carol.com.br"));
    }

    private void configuraBotaoNovoAluno() {
        FloatingActionButton botaoAdicionarAluno = findViewById(R.id.activity_novo_aluno_fab);
        botaoAdicionarAluno.setOnClickListener(abreFormularioAdicionarAluno());
    }

    private View.OnClickListener abreFormularioAdicionarAluno() {
        return v -> startActivity(new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();
    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_lv);
        List<Aluno> buscarTodos = alunoDAO.getTodos();

        configuraAdapter(listaDeAlunos, buscarTodos);
        configuraClickPorItem(listaDeAlunos);
    }

    private void configuraAdapter(ListView listaDeAlunos, List<Aluno> buscarTodos) {
        listaDeAlunos.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                buscarTodos));
    }

    private void configuraClickPorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Aluno alunoSelecionado = (Aluno) adapterView.getItemAtPosition(position);
                abreFormularioEditaAluno(alunoSelecionado);
            }
        });
    }

    private void abreFormularioEditaAluno(Aluno aluno) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiParaFormularioActivity);
    }
}
