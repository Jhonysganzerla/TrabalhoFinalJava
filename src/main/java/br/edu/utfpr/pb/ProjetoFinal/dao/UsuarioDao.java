package br.edu.utfpr.pb.ProjetoFinal.dao;

import br.edu.utfpr.pb.ProjetoFinal.model.Usuario;

import javax.persistence.Query;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    public String getMd5(String input) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
