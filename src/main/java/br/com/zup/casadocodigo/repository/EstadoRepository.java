package br.com.zup.casadocodigo.repository;

import br.com.zup.casadocodigo.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

    @Query("SELECT e FROM Estado e WHERE e.pais.id = :id")
    List<Estado> findAllStatesInCountry(Long id);

}
