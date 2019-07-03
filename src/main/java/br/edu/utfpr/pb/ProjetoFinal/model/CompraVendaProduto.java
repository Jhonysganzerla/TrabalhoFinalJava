package br.edu.utfpr.pb.ProjetoFinal.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CompraVendaProduto implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private Double valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", referencedColumnName = "id", nullable = true)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "compravenda_id", referencedColumnName = "id")
    private CompraVenda compravenda;

}
