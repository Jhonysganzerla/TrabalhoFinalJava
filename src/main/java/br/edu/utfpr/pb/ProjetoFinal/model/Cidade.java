package br.edu.utfpr.pb.ProjetoFinal.model;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "cidade")
@NamedQueries({
        @NamedQuery(name = "Cidade.find_cidade_by_estado",
                query = "from Cidade c "
                        + " where c.estado=:estado"),
        @NamedQuery(name = "Cidade.findAll",
                query = "Select c from Cidade c")
})
public class Cidade implements AbstractModel {
    public static final String FIND_ALL = "Cidade.findAll";
    public static final String FIND_CIDADE_BY_ESTADO=
            "Cidade.find_cidade_by_estado";
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", length = 60, nullable = false)
    private String nome;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "estado_id", referencedColumnName = "id")
    private Estado estado;

    public Cidade() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Estado getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return nome;
    }
}
