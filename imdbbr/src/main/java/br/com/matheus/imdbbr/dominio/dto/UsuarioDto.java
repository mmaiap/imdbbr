package br.com.matheus.imdbbr.dominio.dto;

import br.com.matheus.imdbbr.dominio.entidades.Usuario;
import lombok.Data;

import java.io.Serializable;

@Data
public class UsuarioDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String email;

    public UsuarioDto(){}

    public UsuarioDto(Integer id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
    }
}
