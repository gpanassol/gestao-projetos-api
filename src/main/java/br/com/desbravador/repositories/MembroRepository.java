package br.com.desbravador.repositories;

import br.com.desbravador.entities.MembroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembroRepository extends JpaRepository<MembroEntity, Long>  {
}
