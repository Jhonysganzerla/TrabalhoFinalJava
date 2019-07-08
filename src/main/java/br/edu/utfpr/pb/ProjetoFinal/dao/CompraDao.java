package br.edu.utfpr.pb.ProjetoFinal.dao;

        import br.edu.utfpr.pb.ProjetoFinal.model.CompraVenda;

        import javax.persistence.Query;
        import java.util.List;

public class CompraDao extends GenericDao<CompraVenda, Long> {

    public CompraDao() {
        super(CompraVenda.class);
    }

    public List<CompraVenda> listAllCompra(){

        Query query = em.createQuery("select p from CompraVenda p where isVenda = false ");

        return (List<CompraVenda>) query.getResultList();
    }
}
