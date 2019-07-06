package br.edu.utfpr.pb.ProjetoFinal.dao;

import br.edu.utfpr.pb.ProjetoFinal.model.Usuario;

import javax.persistence.Query;

public class UsuarioDao extends GenericDao<Usuario, Long> {

    public UsuarioDao() {
        super(Usuario.class);
    }

    public Usuario findByEmailAndSenhaNamedQuery(String email,
                                                 String senha){
        Query query = em.createNamedQuery(
                Usuario.FIND_BY_EMAIL_AND_SENHA);
        query.setParameter("email", email);
        query.setParameter("senha", senha);

        return (Usuario) query.getSingleResult();
    }
}
