package br.com.matheus.imdbbr.controllers;

import br.com.matheus.imdbbr.dominio.dto.UsuarioDto;
import br.com.matheus.imdbbr.dominio.entidades.Usuario;
import br.com.matheus.imdbbr.servicos.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> cadastrar(@RequestBody Usuario usuario){
        Usuario novoUsuario = service.insertUsuario(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(novoUsuario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public ResponseEntity<List<UsuarioDto>> listarUsuariosAtivos(){
        List<Usuario> usuariosAtivos = service.listarUsuariosAtivos();
        List<UsuarioDto> usuarioDtos = usuariosAtivos.stream().map(obj -> new UsuarioDto(obj))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(usuarioDtos);
    }

    @RequestMapping(value = "/listar/page", method = RequestMethod.GET)
    public ResponseEntity<Page<UsuarioDto>> listarUsuariosAtivosPaginado(@RequestParam(name = "page", defaultValue = "0") Integer page){

        Page<Usuario> usuariosAtivos = service.listarUsuariosAtivosPaginados(page);
        Page<UsuarioDto> usuarioDtos = usuariosAtivos.map(obj -> new UsuarioDto(obj));
        return ResponseEntity.ok().body(usuarioDtos);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> editar(@RequestBody UsuarioDto usuarioDto, @PathVariable Integer id){
        Usuario usuario = service.fromDto(usuarioDto);
        usuario.setId(id);
        service.updateUsuario(usuario);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/desativar/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> desativar(@PathVariable Integer id){
        service.desativacaoUsuario(id);
        return ResponseEntity.noContent().build();
    }


}
