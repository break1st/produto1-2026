package br.ifmg.produto1_2026.services;

import br.ifmg.produto1_2026.dto.CategoriaDTO;
import br.ifmg.produto1_2026.entities.Categoria;
import br.ifmg.produto1_2026.repositories.CategoriaRepository;
import br.ifmg.produto1_2026.services.exceptions.ErroNoBancoDeDados;
import br.ifmg.produto1_2026.services.exceptions.ResourceNotFound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public CategoriaDTO findById(Long id) {
        return categoriaRepository.findById(id).map(CategoriaDTO::new)
                .orElseThrow(() -> new ResourceNotFound("Categoria não encontrada"));
    }

    @Transactional(readOnly = true)
    public Page<CategoriaDTO> findAll(Pageable pageable) {
         return categoriaRepository.findAll(pageable)
             .map(CategoriaDTO::new);
    }

    @Transactional
    public CategoriaDTO insert(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());
        categoria = categoriaRepository.save(categoria);
        return new CategoriaDTO(categoria);
    }

    @Transactional
    public void delete(Long id) {
        if(!categoriaRepository.existsById(id)){
            throw new ResourceNotFound("Categoria não encontrada");
        }

        try {
            categoriaRepository.deleteById(id);
        }
        catch(DataIntegrityViolationException e) {
            throw new ErroNoBancoDeDados(e.getMessage());
        }
    }

    @Transactional
    public CategoriaDTO update(Long id, CategoriaDTO dto) {
        if(!categoriaRepository.existsById(id)){
            throw new ResourceNotFound("Categoria não encontrada");
        }

        Categoria categoria = categoriaRepository.getReferenceById(id);
        categoria.setNome(dto.getNome());
        categoria = categoriaRepository.save(categoria);
        return new CategoriaDTO(categoria);
    }
}