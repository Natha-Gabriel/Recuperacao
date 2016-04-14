package br.ifpb.edu.atividadeconvite.callback;

import java.util.List;

import br.ifpb.edu.atividadeconvite.entidades.Pessoa;

public interface PessoaCallBack {

    void backBuscarNome(List<Pessoa> names);

    void errorBuscarNome(String error);

}
