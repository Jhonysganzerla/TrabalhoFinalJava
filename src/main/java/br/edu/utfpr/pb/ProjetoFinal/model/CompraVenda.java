package br.edu.utfpr.pb.ProjetoFinal.model;

import br.edu.utfpr.pb.ProjetoFinal.util.BooleanConverter;
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
public class CompraVenda implements Serializable, AbstractModel {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 25, nullable = false)
    private String numeroDocumento;

    @Column(length = 300, nullable = true)
    private String descricao;

    @Column(nullable = false)
    private LocalDate data;

    @Column
    private Integer parcelas;

    @ManyToOne()
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    private Pessoa pessoa;

    @OneToMany(mappedBy = "compravenda",
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER)
    private List<CompraVendaProduto> compraVendaProdutos;

    @Transient
    private Double valorTotal;

    @Convert(converter = BooleanConverter.class)
    @Column(columnDefinition = "char(1) default 'F'")
    private Boolean isVenda;



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
