package com.carol.agenda.dao;

import com.carol.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    public static int contadorDeIds = 1;

    public void salvar(Aluno aluno) {
        aluno.setId(contadorDeIds);
        alunos.add(aluno);
        contadorDeIds++;
    }

    public void editar(Aluno aluno) {
        Aluno alunoEncontrado = null;
        for (Aluno a :
                alunos) {
            if (a.getId() == aluno.getId()) {
                alunoEncontrado = a;
            }
        }
        if (alunoEncontrado != null) {
            int indexDoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(indexDoAluno, aluno);
        }

    }

    public List<Aluno> getTodos() {
        return new ArrayList<>(alunos);
    }


}
