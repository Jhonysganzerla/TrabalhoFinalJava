
package br.edu.utfpr.pb.ProjetoFinal.dao;

        import br.edu.utfpr.pb.ProjetoFinal.model.CompraVenda;

        import javax.persistence.Query;
        import java.util.List;

public class VendaDao extends GenericDao<CompraVenda, Long> {

    public VendaDao() {
        super(CompraVenda.class);
    }

    public List<CompraVenda> listAllVenda(){

        Query query = em.createQuery("select p from CompraVenda p where isVenda = true");

        return (List<CompraVenda>) query.getResultList();
    }
}
