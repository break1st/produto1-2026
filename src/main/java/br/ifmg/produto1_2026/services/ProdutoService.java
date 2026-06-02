package br.ifmg.produto1_2026.services;

import br.ifmg.produto1_2026.dto.CategoriaDTO;
import br.ifmg.produto1_2026.dto.ProdutoDTO;
import br.ifmg.produto1_2026.dto.ProdutoListDTO;
import br.ifmg.produto1_2026.entities.Categoria;
import br.ifmg.produto1_2026.entities.Produto;
import br.ifmg.produto1_2026.projections.ProdutoProjection;
import br.ifmg.produto1_2026.repositories.CategoriaRepository;
import br.ifmg.produto1_2026.repositories.ProdutoRepository;
import br.ifmg.produto1_2026.resources.ProdutoResource;
import br.ifmg.produto1_2026.services.exceptions.ErroNoBancoDeDados;
import br.ifmg.produto1_2026.services.exceptions.ResourceNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProdutoService {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoService.class);

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public Page<ProdutoListDTO> findAll(String categoriasID, String name, Pageable pageRequest) {

        //Convertemos uma String em uma lista de Longs.
        List<Long> categoriasIDs = null;
        if (categoriasID != null && !categoriasID.equals("0")) {
            categoriasIDs =
                    Arrays.asList(
                                    categoriasID.split(","))
                            .stream().map(n->Long.valueOf(n)).toList();
        }

        //Lista com os dados do BD. Essa lista vem com dados em Projections
        Page<ProdutoProjection> produtos = produtoRepository.searchProdutos(categoriasIDs,  name,   pageRequest);

        //Converter os projections em DTOs, pois a camada de cima, só trabalha com DTOs
        List<ProdutoListDTO> produtosDTO =
                produtos.stream().map(p-> new ProdutoListDTO(p)).toList();

        return  new PageImpl<>(produtosDTO, pageRequest, produtos.getTotalPages());

        /*
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));


        return produtos.stream().map(p
                -> new ProdutoListDTO(p)
                .add( linkTo( methodOn(ProdutoResource.class).produtos(pageable) ).withSelfRel() )
                .add( linkTo( methodOn(ProdutoResource.class).produto(p.getID()) ).withRel("Obter produto pelo ID") )
        );
        */
    }

    @Transactional(readOnly = true)
    public ProdutoDTO findById(Long id) {
        Optional<Produto> opt = produtoRepository.findById(id);
        Produto produto = opt.orElseThrow(() -> new ResourceNotFound("Produto não encontrado."));

        ProdutoDTO dto = new ProdutoDTO(produto);
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));

        return dto
                .add(linkTo(methodOn(ProdutoResource.class).findById(produto.getId())).withSelfRel())
                .add(linkTo(methodOn(ProdutoResource.class).findAll(pageable)).withRel("Todos os produtos"))
                .add(linkTo(methodOn(ProdutoResource.class).update(produto.getId(), dto)).withRel("Atualizar o produto"))
                .add(linkTo(methodOn(ProdutoResource.class).delete(produto.getId())).withRel("Apagar o produto"));
    }

    @Transactional(readOnly = true)
    public Page<ProdutoDTO> findAll(Pageable pageRequest) {
        logger.info("Consultando a lista de produtos");
        logger.error("Consultando a lista de produtos");
        logger.warn("Consultando a lista de produtos");
        logger.debug("Consultando {} a lista {} de produtos", 123, "teste");

        Page<Produto> produtos = produtoRepository.findAll(pageRequest);

        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));

        return produtos.map(p -> new ProdutoDTO(p)
                .add(linkTo(methodOn(ProdutoResource.class).findAll(pageable)).withSelfRel())
                .add(linkTo(methodOn(ProdutoResource.class).findById(p.getId())).withRel("Obter produto pelo ID"))
        );
    }

    @Transactional
    public ProdutoDTO insert(ProdutoDTO dto) {
        Produto produto = new Produto();
        coptDtoToEntity(dto, produto);

        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));

        produto = produtoRepository.save(produto);

        return new ProdutoDTO(produto)
                .add(linkTo(methodOn(ProdutoResource.class).insert(dto)).withSelfRel())
                .add(linkTo(methodOn(ProdutoResource.class).findById(produto.getId())).withRel("Busca pelo ID") )
                .add(linkTo(methodOn(ProdutoResource.class).findAll(pageable)).withRel("Todos os produtos") )
                .add(linkTo(methodOn(ProdutoResource.class).update(produto.getId(), dto)).withRel("Atualizar o produto") )
                .add(linkTo(methodOn(ProdutoResource.class).delete(produto.getId())).withRel("Apagar o produto"));
    }

    @Transactional
    public void delete(Long id) {
        if(!produtoRepository.existsById(id)){
            throw new ResourceNotFound("Produto não encontrada");
        }

        try {
            produtoRepository.deleteById(id);
        }
        catch(DataIntegrityViolationException e) {
            throw new ErroNoBancoDeDados(e.getMessage());
        }
    }

    @Transactional
    public ProdutoDTO update(Long id, ProdutoDTO dto) {
        if(!produtoRepository.existsById(id)){
            throw new ResourceNotFound("Produto não encontrada");
        }

        Produto produto = produtoRepository.getReferenceById(id);
        coptDtoToEntity(dto, produto);
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));

        produto = produtoRepository.save(produto);

        return new ProdutoDTO(produto)
                .add(linkTo(methodOn(ProdutoResource.class).update(id, dto)).withSelfRel())
                .add(linkTo(methodOn(ProdutoResource.class).findById(id)).withRel("Busca pelo ID"))
                .add(linkTo(methodOn(ProdutoResource.class).findAll(pageable)).withRel("Todos os produtos"))
                .add(linkTo(methodOn(ProdutoResource.class).delete(id)).withRel("Apagar o produto"));
    }

    private void coptDtoToEntity(ProdutoDTO dto, Produto entity) {
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setPreco(dto.getPreco());
        entity.setImgUrl(dto.getImgUrl());

        entity.getCategorias().clear();
        for (CategoriaDTO catDto : dto.getCategorias()){
            Categoria cat = categoriaRepository.getReferenceById(catDto.getId());
            entity.getCategorias().add(cat);
        }
    }
}