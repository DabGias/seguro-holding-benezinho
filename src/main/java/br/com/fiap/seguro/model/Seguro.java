package br.com.fiap.seguro.model;

import br.com.fiap.pessoa.model.Pessoa;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tb_seguro")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tp_seguro")
public abstract class Seguro {
    @Id
    @GeneratedValue(
            generator = "seq_seguro",
            strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
            name = "seq_seguro",
            sequenceName = "seq_seguro",
            allocationSize = 1
    )
    @Column(name = "id_seguro")
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "inicio_vigencia_seguro")
    private LocalDate inicioVigencia;

    @Temporal(TemporalType.DATE)
    @Column(name = "fim_vigencia_seguro")
    private LocalDate fimVigencia;

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
            foreignKey = @ForeignKey(name = "fk_contratante")
    )
    private Pessoa contratante;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(
            name = "id_corretor",
            referencedColumnName = "id_corretor",
            foreignKey = @ForeignKey(name = "fk_corretor")
    )
    private Corretor corretor;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "tb_beneficiarios",
            joinColumns = @JoinColumn(
                    name = "id_seguro",
                    referencedColumnName = "id_seguro",
                    foreignKey = @ForeignKey(name = "fk_seguro")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "id_beneficiario",
                    referencedColumnName = "id_pessoa",
                    foreignKey = @ForeignKey(name = "fk_beneficiario")
            )
    )
    private Set<Pessoa> beneficiarios = new LinkedHashSet<>();

    public Seguro() {
    }

    public Seguro(Long id, LocalDate inicioVigencia, LocalDate fimVigencia, Pessoa contratante, Corretor corretor) {
        this.id = id;
        this.inicioVigencia = inicioVigencia;
        this.fimVigencia = fimVigencia;
        this.contratante = contratante;
        this.corretor = corretor;
    }


    public Long getId() {
        return id;
    }

    public Seguro setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate getInicioVigencia() {
        return inicioVigencia;
    }

    public Seguro setInicioVigencia(LocalDate inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
        return this;
    }

    public LocalDate getFimVigencia() {
        return fimVigencia;
    }

    public Seguro setFimVigencia(LocalDate fimVigencia) {
        this.fimVigencia = fimVigencia;
        return this;
    }

    public Pessoa getContratante() {
        return contratante;
    }

    public Seguro setContratante(Pessoa contratante) {
        this.contratante = contratante;
        return this;
    }

    public Corretor getCorretor() {
        return corretor;
    }

    public Seguro setCorretor(Corretor corretor) {
        this.corretor = corretor;
        return this;
    }


    public Set<Pessoa> getBeneficiarios() {
        return Collections.unmodifiableSet(beneficiarios);
    }

    public Seguro addBeneficiario(Pessoa pessoa) {
        this.beneficiarios.add(pessoa);
        return this;
    }

    public Seguro removeBeneficiario(Pessoa pessoa) {
        this.beneficiarios.remove(pessoa);
        return this;
    }


    @Override
    public String toString() {
        return "Seguro{" +
                "id=" + id +
                ", inicioVigencia=" + inicioVigencia +
                ", fimVigencia=" + fimVigencia +
                ", contratante=" + contratante +
                ", corretor=" + corretor +
                ", beneficiarios=" + beneficiarios +
                '}';
    }
}
