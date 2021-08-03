package br.com.zupacademy.brenonoccioli.mercadolivre.categoria;

import br.com.zupacademy.brenonoccioli.mercadolivre.categoria.dto.CategoriaDto;
import br.com.zupacademy.brenonoccioli.mercadolivre.categoria.dto.CategoriaForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    CategoriaController(CategoriaRepository categoriaRepositoryory){
        this.categoriaRepository = categoriaRepositoryory;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<CategoriaDto> cadastrar(@RequestBody @Valid CategoriaForm form){
        Categoria categoria = form.toModel(categoriaRepository);
        categoriaRepository.save(categoria);
        return ResponseEntity.ok().body(new CategoriaDto(categoria.getNome()));
    }
}
