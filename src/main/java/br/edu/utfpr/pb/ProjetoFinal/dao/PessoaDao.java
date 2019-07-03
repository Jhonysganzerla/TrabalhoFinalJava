package br.edu.utfpr.pb.ProjetoFinal.dao;

import br.edu.utfpr.pb.ProjetoFinal.model.Pessoa;

public class PessoaDao extends GenericDao<Pessoa, Long> {

    public PessoaDao() {
        super(Pessoa.class);
    }
}
