package br.ifmg.produto1_2026.services;

import br.ifmg.produto1_2026.dto.ProdutoDTO;
import br.ifmg.produto1_2026.entities.Produto;
import br.ifmg.produto1_2026.repositories.ProdutoRepository;
import br.ifmg.produto1_2026.services.exceptions.ErroNoBancoDeDados;
import br.ifmg.produto1_2026.services.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional(readOnly = true)
    public ProdutoDTO findById(Long id) {
        return produtoRepository.findById(id).map(ProdutoDTO::new)
                .orElseThrow(() -> new ResourceNotFound("Produto não encontrada"));
    }

    @Transactional(readOnly = true)
    public Page<ProdutoDTO> findAll(Pageable pageable) {
        return produtoRepository.findAll(pageable)
                .map(ProdutoDTO::new);
    }

    @Transactional
    public ProdutoDTO insert(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setImgUrl(dto.getImgUrl());
        produto.setPreco(dto.getPreco());
        produto = produtoRepository.save(produto);
        return new ProdutoDTO(produto);
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
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setImgUrl(dto.getImgUrl());
        produto.setPreco(dto.getPreco());
        produto = produtoRepository.save(produto);
        return new ProdutoDTO(produto);
    }
}