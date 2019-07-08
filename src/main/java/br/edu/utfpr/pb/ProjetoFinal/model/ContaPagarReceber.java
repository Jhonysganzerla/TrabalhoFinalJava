package br.edu.utfpr.pb.ProjetoFinal.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class ContaPagarReceber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=300,nullable = false)
    private String descricao;

    @Column(length=60,nullable = false)
    private String tipoPagamento;

    @Column(nullable = false)
    private LocalDate dataMovimento;

    @Column(nullable = false)
    private LocalDate dataVencimento;

    @Column(nullable = false)
    private Integer parcelas;

    @Column(nullable = false)
    private BigDecimal valor;


    public List getTipoPagamento() {
        ArrayList<String> a = new ArrayList<>();
        a.add("Boleto");
        a.add("Dinheiro");
        a.add("Cheque");
        return a;
    }
}
