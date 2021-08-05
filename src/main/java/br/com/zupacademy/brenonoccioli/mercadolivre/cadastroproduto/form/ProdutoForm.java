package br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.form;

import br.com.zupacademy.brenonoccioli.mercadolivre.annotations.IdExists;
import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.CaracteristicasDoProduto;
import br.com.zupacademy.brenonoccioli.mercadolivre.cadastroproduto.Produto;

import br.com.zupacademy.brenonoccioli.mercadolivre.categoria.Categoria;
import br.com.zupacademy.brenonoccioli.mercadolivre.categoria.CategoriaRepository;

import br.com.zupacademy.brenonoccioli.mercadolivre.usuario.Usuario;

import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ProdutoForm {

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal preco;

    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    @NotBlank
    @Size(max=1000)
    private String descricao;

    @NotNull
    @ManyToOne
    @IdExists(field = "id", domainClass = Categoria.class)
    private Long idCategoria;

    @Size(min=3)
    private Set<CaracteristicasDoProdutoForm> caracteristicasForm = new HashSet<>();

    @Deprecated
    public ProdutoForm(){}

    public ProdutoForm(String nome, BigDecimal preco, Integer quantidade,
                       String descricao, Long idCategoria,
                       Set<CaracteristicasDoProdutoForm> caracteristicas) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.caracteristicasForm.addAll(caracteristicas);
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public Set<CaracteristicasDoProdutoForm> getCaracteristicasForm() {
        return caracteristicasForm;
    }

    public Produto toModel(CategoriaRepository repository, Usuario dono){
        Categoria categoria = repository.findById(idCategoria).get();
        return new Produto(nome, preco, quantidade, descricao, categoria, dono, caracteristicasForm);
    }

    public Set<String> buscaCaracteristicasIguais() {
        HashSet<String> nomesIguais = new HashSet<>();
        HashSet<String> resultados = new HashSet<>();

        for(CaracteristicasDoProdutoForm caracteristica : caracteristicasForm){
            String nome = caracteristica.getNome();
            if(!nomesIguais.add(nome)){
                resultados.add(nome);
            }
        }
        return resultados;
    }
}
