package br.ifmg.produto1_2026.repositories;

import br.ifmg.produto1_2026.entities.Produto;
import br.ifmg.produto1_2026.projections.ProdutoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query(nativeQuery = true,
            value = """
                    SELECT 
                          DISTINCT
                          p.id,
                          p.nome,
                          p.preco,
                          p.img_url as imgUrl
                    FROM tb_produto p
                    INNER JOIN tb_produto_categoria pc 
                                        ON pc.id_produto = p.id
                    INNER JOIN tb_categoria c 
                                        ON c.id = pc.id_categoria                                         
                    WHERE (
                           :categoriasID IS NULL 
                           OR
                           pc.id_categoria in (:categoriasID) 
                          )
                          AND
                          (
                          LOWER(p.nome) 
                            LIKE LOWER( CONCAT('%',:name,'%') )
                          )
                    """,
            countName = """
                     SELECT COUNT(*)
                     FROM
                     (SELECT 
                          DISTINCT
                          p.id,
                          p.nome,
                          p.preco,
                          p.img_url as imgUrl
                    FROM tb_produto p
                    INNER JOIN tb_produto_categoria pc 
                                        ON pc.id_produto = p.id
                    INNER JOIN tb_categoria c 
                                        ON c.id = pc.id_categoria                                         
                    WHERE (
                           :categoriasID IS NULL 
                           OR
                           pc.id_categoria in (:categoriasID) 
                          )
                          AND
                          (
                          LOWER(p.nome) 
                            LIKE LOWER( CONCAT('%',:name,'%') )
                          )
                    ) as tb_result
                    """)
    Page<ProdutoProjection> searchProdutos(List<Long> categoriasID, String name, Pageable pageable);
}