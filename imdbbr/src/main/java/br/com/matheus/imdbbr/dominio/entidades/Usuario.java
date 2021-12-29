package br.com.matheus.imdbbr.dominio.entidades;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String email;

    private String senha;

    @Column(name = "ativo")
    private Integer statusAtivo;

    public Usuario() {
    }

    public Usuario(Integer id, String nome, String email, String senha, Integer statusAtivo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.statusAtivo = statusAtivo;
    }

}
