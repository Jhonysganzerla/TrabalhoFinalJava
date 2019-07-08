package br.edu.utfpr.pb.ProjetoFinal.dao;

        import br.edu.utfpr.pb.ProjetoFinal.model.CompraVenda;

        import javax.persistence.Query;
        import java.util.List;

public class CompraVendaDao extends GenericDao<CompraVenda, Long> {

    public CompraVendaDao() {
        super(CompraVenda.class);
    }

    public List<CompraVenda> listAllCompra(){

        Query query = em.createQuery("select p from CompraVenda p where isVenda = false ");

        return (List<CompraVenda>) query.getResultList();
    }

    public List<CompraVenda> listAllVenda(){

        Query query = em.createQuery("select p from CompraVenda p where isVenda = true");

        return (List<CompraVenda>) query.getResultList();
    }
}
