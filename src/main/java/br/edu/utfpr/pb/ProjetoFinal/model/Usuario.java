package br.edu.utfpr.pb.ProjetoFinal.model;

import br.edu.utfpr.pb.ProjetoFinal.util.BooleanConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario implements AbstractModel{

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
    @NotNull(message = "O campo "
            + "'Data de Nascimento' é "
            + "obrigatório!")
    @Column(nullable = false)
    private LocalDate dataNascimento;
    @Lob
    @Column()
    private byte[] foto;

}
