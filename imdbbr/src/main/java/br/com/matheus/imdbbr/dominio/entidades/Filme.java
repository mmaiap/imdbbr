package br.com.matheus.imdbbr.dominio.entidades;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "filmes")
public class Filme implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String genero;
    private String diretor;

    @OneToMany(mappedBy = "filme", cascade = CascadeType.ALL)
    private List<Nota> notas = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "ATORES")
    private List<String> atores = new ArrayList<>();

    public Filme(){}

    public Filme(Integer id, String nome, String genero, String diretor) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.diretor = diretor;
    }
}
