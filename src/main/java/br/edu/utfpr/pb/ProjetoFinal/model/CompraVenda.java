package br.edu.utfpr.pb.ProjetoFinal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "compravenda")
public class CompraVenda implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25, nullable = false)
    private String numeroDocumento;

    @Column(nullable = false)
    private LocalDate data;

    @Column
    private Integer Parcelas;

    @ManyToOne()
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    private Pessoa pessoa;

    @OneToMany(mappedBy = "compravenda",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY)
    private List<CompraVendaProduto> compraVendaProdutos;

    @Transient
    private Double valorTotal;


    public Double getValorTotal() {

        return compraVendaProdutos.stream().mapToDouble(vp -> vp.getValor() *
                vp.getQuantidade()).sum();
    }

    public List<CompraVendaProduto> getCompraVendaProdutos() {
        return compraVendaProdutos;
    }

    public void setCompraVendaProdutos(List<CompraVendaProduto> compraVendaProdutos) {
        this.compraVendaProdutos = compraVendaProdutos;
    }
}
