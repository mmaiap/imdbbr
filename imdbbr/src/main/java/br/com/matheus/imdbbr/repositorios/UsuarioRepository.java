package br.com.matheus.imdbbr.repositorios;

import br.com.matheus.imdbbr.dominio.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findAllByStatusAtivoOrderByNomeAsc(Integer ativo);

    @Query(value = "SELECT * FROM USUARIOS WHERE ATIVO = 1",
            countQuery = "SELECT COUNT(*) FROM usuarios",
            nativeQuery = true)
    Page<Usuario> listarUsuarioNaoAdmAtivosPaginados(Pageable pageable);

}
