package br.edu.utfpr.pb.ProjetoFinal.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contapagar")
public class ContaPagar extends ContaPagarReceber {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compravenda_id", referencedColumnName = "id", nullable = true)
    private CompraVenda compra;
}
