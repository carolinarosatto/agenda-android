package com.carol.agenda.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.carol.agenda.R;
import com.carol.agenda.dao.AlunoDAO;
import com.carol.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR_FORMULARIO = "Novo aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    final AlunoDAO alunoDAO = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR_FORMULARIO);
        inicializacaoDosCampos();
        configurarBotaoSalvar();
    }

    private void configurarBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(v -> {
            Aluno novoAluno = criarAluno();
            salvarAluno(novoAluno);
        });
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome_et);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone_et);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email_et);
    }

    private void salvarAluno(Aluno novoAluno) {
        alunoDAO.salvar(novoAluno);
        finish();
    }

    private @NonNull Aluno criarAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        return new Aluno(nome, telefone, email);
    }
}