package br.com.desbravador.repositories;

import br.com.desbravador.entities.ProjetoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends JpaRepository<ProjetoEntity, Long> {
}
