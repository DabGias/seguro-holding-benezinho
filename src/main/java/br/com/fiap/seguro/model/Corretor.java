package br.com.fiap.seguro.model;

import br.com.fiap.pessoa.model.Pessoa;
import jakarta.persistence.*;

@Entity
@Table(
        name = "tb_corretor",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_susep_corretor",
                columnNames = "susep_corretor"
        )
)
public class Corretor {
    @Id
    @GeneratedValue(
            generator = "seq_corretor",
            strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
            name = "seq_corretor",
            sequenceName = "seq_corretor",
            allocationSize = 1
    )
    @Column(name = "id_corretor")
    private long id;

    @Column(name = "susep_corretor")
    private String numeroSUSEP;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(
            name = "id_pessoa",
            referencedColumnName = "id_pessoa",
            foreignKey = @ForeignKey(name = "fk_pessoa")
    )
    private Pessoa pessoa;


    public Corretor() {
    }

    public Corretor(long id, String numeroSUSEP, Pessoa pessoa) {
        this.id = id;
        this.numeroSUSEP = numeroSUSEP;
        this.pessoa = pessoa;
    }

    public long getId() {
        return id;
    }

    public Corretor setId(long id) {
        this.id = id;
        return this;
    }

    public String getNumeroSUSEP() {
        return numeroSUSEP;
    }

    public Corretor setNumeroSUSEP(String numeroSUSEP) {
        this.numeroSUSEP = numeroSUSEP;
        return this;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Corretor setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        return this;
    }

    @Override
    public String toString() {
        return "Corretor{" +
                "id=" + id +
                ", numeroSUSEP='" + numeroSUSEP + '\'' +
                ", pessoa=" + pessoa +
                '}';
    }
}
