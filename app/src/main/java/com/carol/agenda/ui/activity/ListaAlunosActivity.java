package com.carol.agenda.ui.activity;

import static com.carol.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.carol.agenda.R;
import com.carol.agenda.dao.AlunoDAO;
import com.carol.agenda.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaAlunosActivity extends AppCompatActivity {

    private final AlunoDAO alunoDAO = new AlunoDAO();
    public static final String TITULO_APPBAR_LISTA = "Lista de alunos";
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR_LISTA);
        configuraBotaoNovoAluno();
        configuraLista();
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
        atualizaAlunos();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_alunos_menu_remover) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            if (menuInfo != null) {
                Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                removerAluno(alunoEscolhido);
            }
        }


        return super.onContextItemSelected(item);
    }

    private void atualizaAlunos() {
        adapter.clear();
        adapter.addAll(alunoDAO.getTodos());
    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_lv);

        configuraAdapter(listaDeAlunos);
        configuraClickPorItem(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
    }

    private void removerAluno(Aluno aluno) {
        alunoDAO.remover(aluno);
        adapter.remove(aluno);
    }

    private void configuraAdapter(ListView listaDeAlunos) {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1);
        listaDeAlunos.setAdapter(adapter);
    }

    private void configuraClickPorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener((adapterView, view, position, id) -> {
            Aluno alunoSelecionado = (Aluno) adapterView.getItemAtPosition(position);
            abreFormularioEditaAluno(alunoSelecionado);
        });
    }

    private void abreFormularioEditaAluno(Aluno aluno) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiParaFormularioActivity);
    }
}
