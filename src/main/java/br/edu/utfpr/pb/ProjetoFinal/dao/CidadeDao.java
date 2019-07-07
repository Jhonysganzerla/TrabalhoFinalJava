package br.edu.utfpr.pb.ProjetoFinal.dao;

import br.edu.utfpr.pb.ProjetoFinal.model.Cidade;
import br.edu.utfpr.pb.ProjetoFinal.model.Estado;

import javax.persistence.Query;
import java.util.List;

public class CidadeDao extends GenericDao<Cidade, Long> {

    public CidadeDao() {
        super(Cidade.class);
    }

    public List<Cidade> findCidadeByEstado(Estado estado){

        Query query = em.createNamedQuery(
                Cidade.FIND_CIDADE_BY_ESTADO);
        query.setParameter("estado", estado.getId());

        return (List<Cidade>) query.getResultList();
    }
}
