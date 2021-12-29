package br.com.matheus.imdbbr.servicos;

import br.com.matheus.imdbbr.dominio.dto.UsuarioDto;
import br.com.matheus.imdbbr.dominio.entidades.Usuario;
import br.com.matheus.imdbbr.repositorios.UsuarioRepository;
import br.com.matheus.imdbbr.servicos.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario find(Integer id){
        Optional<Usuario> usuario = repository.findById(id);

        return usuario.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado Id: " + id + ", Tipo: " + Usuario.class.getName()));
    }

    public Usuario insertUsuario(Usuario usuario){
        return repository.save(usuario);
    }

    public Usuario updateUsuario(Usuario usuario){
        Usuario novoUsuario = find(usuario.getId());
        updateData(novoUsuario, usuario);
        return repository.save(novoUsuario);
    }

    public List<Usuario> listarUsuariosAtivos(){
        return repository.findAllByAtivoOrderByNomeAsc(1);
    }

    public Page<Usuario> listarUsuariosAtivosPaginados(Integer page){
        Pageable pageable = PageRequest.of(page, 5, Sort.Direction.ASC);
        return repository.listarUsuarioNaoAdmAtivosPaginados(pageable);
    }

    public Usuario desativacaoUsuario(Integer id){
        Usuario novoUsuario = find(id);
        desativar(novoUsuario);
        return repository.save(novoUsuario);
    }

    private void updateData(Usuario novoUsuario, Usuario usuario) {
        novoUsuario.setNome(usuario.getNome());
        novoUsuario.setEmail(usuario.getEmail());
        novoUsuario.setSenha(usuario.getSenha());
    }

    private void desativar(Usuario novoUsuario) {
        novoUsuario.setStatusAtivo(0);
    }


    public Usuario fromDto(UsuarioDto usuarioDto) {
        return new Usuario(usuarioDto.getId(), usuarioDto.getNome(), usuarioDto.getEmail(), null, null);
    }
}
