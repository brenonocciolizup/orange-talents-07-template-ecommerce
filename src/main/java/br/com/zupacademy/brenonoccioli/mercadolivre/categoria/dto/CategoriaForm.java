package br.com.zupacademy.brenonoccioli.mercadolivre.categoria.dto;

import br.com.zupacademy.brenonoccioli.mercadolivre.annotations.IdExists;
import br.com.zupacademy.brenonoccioli.mercadolivre.annotations.UniqueValue;
import br.com.zupacademy.brenonoccioli.mercadolivre.categoria.Categoria;
import br.com.zupacademy.brenonoccioli.mercadolivre.categoria.CategoriaRepository;


import javax.validation.constraints.NotBlank;


public class CategoriaForm {

    @NotBlank
    @UniqueValue(field = "nome", domainClass = Categoria.class)
    private String nome;
    @IdExists(field = "id", domainClass = Categoria.class)
    private Long idCategoriaMae;

    public CategoriaForm(String nome, Long idCategoria){
        this.nome = nome;
        this.idCategoriaMae = idCategoria;
    }

    public Categoria toModel(CategoriaRepository repository){
        if(idCategoriaMae == null){
            return new Categoria(nome, null);
        }

        Categoria categoriaMae = repository.findById(idCategoriaMae).get();

        return new Categoria(nome, categoriaMae);
    }

    public String getNome() {
        return nome;
    }

    public Long getIdCategoriaMae(){
        return idCategoriaMae;
    }
}
