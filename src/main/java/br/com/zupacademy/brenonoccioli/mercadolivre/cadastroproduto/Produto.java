package br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto;

import br.com.zupacademy.brenonoccioli.mercadolivre.categoria.Categoria;
import br.com.zupacademy.brenonoccioli.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

//@Entity
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull @Positive
    private BigDecimal preco;

    @NotNull
    @PositiveOrZero
    private Integer qtdDisponivel;

    @Size(min=3)
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicasDoProduto> caracteristicas = new HashSet<>();

    @NotBlank
    @Size(max=1000)
    private String descricao;

    @NotNull
    @ManyToOne
    private Usuario dono;

    @NotNull
    @ManyToOne
    private Categoria categoria;

    private LocalDateTime instanteCadastro = LocalDateTime.now();

    @Deprecated
    public Produto() {
    }
}
