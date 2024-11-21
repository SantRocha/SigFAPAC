package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.Arquivo;

public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {
    @Query("SELECT a FROM Arquivo a WHERE a.nomeArquivo LIKE %?1%")
    Page<Arquivo> busca(String termoBusca, Pageable page);
}
