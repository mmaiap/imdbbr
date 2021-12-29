package br.com.matheus.imdbbr.dominio.entidades;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "notas")
@Data
public class Nota implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filme_id")
    private Filme filme;

    private Integer nota;

    public Nota(){}

    public Nota(Integer id, Filme filme, Integer nota) {
        this.id = id;
        this.filme = filme;
        this.nota = nota;
    }
}
