package com.carol.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR_FORMULARIO);
        inicializacaoDosCampos();
        configurarBotaoSalvar();

        Intent dados = getIntent();
        if(dados.hasExtra("aluno")) {
            aluno = (Aluno) dados.getSerializableExtra("aluno");
            campoNome.setText(aluno != null ? aluno.getNome() : null);
            campoTelefone.setText(aluno != null ? aluno.getTelefone() : null);
            campoEmail.setText(aluno != null ? aluno.getEmail() : null);
        } else {
            aluno = new Aluno();
        }
    }

    private void configurarBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                preencherAluno();
                if(aluno.temIdValido()) {
                    alunoDAO.editar(aluno);
                } else {
                    alunoDAO.salvar(aluno);
                }
                finish();
            }
        });
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome_et);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone_et);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email_et);
    }

    private void preencherAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setTelefone(telefone);
    }
}