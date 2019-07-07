package br.edu.utfpr.pb.ProjetoFinal.model;

import br.edu.utfpr.pb.ProjetoFinal.util.BooleanConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
@NamedQueries({
        @NamedQuery(name = "Usuario.findByEmailAndSenha",
                query = "from Usuario u "
                        + " where u.email=:email AND u.senha=:senha"),
        @NamedQuery(name = "Usuario.findAll",
                query = "Select u from Usuario u")
})
public class Usuario implements AbstractModel, Serializable {
    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL = "Usuario.findAll";
    public static final String FIND_BY_EMAIL_AND_SENHA=
            "Usuario.findByEmailAndSenha";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "O campo 'nome' é "
            + "obrigatório!")
    @Column(length = 100, nullable = false)
    private String nome;
    @NotEmpty(message = "O campo 'cpf' é "
            + "obrigatório!")
    @Column(length = 11, nullable = false)
    private String cpf;
    @Column(length = 100, nullable = false)
    private String email;
    @Column(length = 512, nullable = false)
    private String senha;
    @Convert(converter = BooleanConverter.class)
    @Column(columnDefinition = "char(1) default 'T'")
    private Boolean ativo;

    @Column(nullable = true)
    private LocalDate dataNascimento;
    @Lob
    @Column()
    private byte[] foto;

    @Convert(converter = BooleanConverter.class)
    @Column(columnDefinition = "char(1) default 'F'",nullable = false)
    private Boolean isAdmin;

}
