package br.edu.utfpr.pb.ProjetoFinal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contato")
public class Contato implements AbstractModel {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25, nullable = true)
    private String telefone;

    @Column(length = 50, nullable = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    private Pessoa pessoa;

}
