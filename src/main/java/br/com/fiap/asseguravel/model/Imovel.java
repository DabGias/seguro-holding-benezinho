package br.com.fiap.asseguravel.model;

import br.com.fiap.pessoa.model.Pessoa;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(
        name = "tb_imovel",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_num_registro_imovel",
                        columnNames = "num_registro_imovel"
                )
        }
)
public class Imovel {
    @Id
    @GeneratedValue(
            generator = "seq_imovel",
            strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
            name = "seq_imovel",
            sequenceName = "seq_imovel",
            allocationSize = 1
    )
    @Column(name = "id_imovel")
    private Long id;

    @Column(name = "qtde_quartos_imovel")
    private int qtdQuartos;

    @Column(name = "qtde_banheiros_imovel")
    private int qtdBanheiros;

    @Column(name = "qtde_vagas_imovel")
    private int intQtdVagasDeGaragem;

    @Column(name = "num_registro_imovel")
    private String numeroRegistroNoCartorio;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "tb_proprietarios",
            joinColumns = @JoinColumn(
                    name = "id_imovel",
                    referencedColumnName = "id_imovel",
                    foreignKey = @ForeignKey(name = "fk_imovel")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "id_proprietario_imovel",
                    referencedColumnName = "id_pessoa",
                    foreignKey = @ForeignKey(name = "fk_proprietario_imovel")
            )
    )
    protected Set<Pessoa> proprietarios = new LinkedHashSet<>();

    public Imovel() {
    }

    public Imovel(int qtdQuartos, int qtdBanheiros, int intQtdVagasDeGaragem, String numeroRegistroNoCartorio) {
        this.qtdQuartos = qtdQuartos;
        this.qtdBanheiros = qtdBanheiros;
        this.intQtdVagasDeGaragem = intQtdVagasDeGaragem;
        this.numeroRegistroNoCartorio = numeroRegistroNoCartorio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQtdQuartos() {
        return qtdQuartos;
    }

    public Imovel setQtdQuartos(int qtdQuartos) {
        this.qtdQuartos = qtdQuartos;
        return this;
    }

    public int getQtdBanheiros() {
        return qtdBanheiros;
    }

    public Imovel setQtdBanheiros(int qtdBanheiros) {
        this.qtdBanheiros = qtdBanheiros;
        return this;
    }

    public int getIntQtdVagasDeGaragem() {
        return intQtdVagasDeGaragem;
    }

    public Imovel setIntQtdVagasDeGaragem(int intQtdVagasDeGaragem) {
        this.intQtdVagasDeGaragem = intQtdVagasDeGaragem;
        return this;
    }

    public String getNumeroRegistroNoCartorio() {
        return numeroRegistroNoCartorio;
    }

    public Imovel setNumeroRegistroNoCartorio(String numeroRegistroNoCartorio) {
        this.numeroRegistroNoCartorio = numeroRegistroNoCartorio;
        return this;
    }


    public Set<Pessoa> getProprietarios() {
        return Collections.unmodifiableSet(proprietarios);
    }


    public Imovel addProprietario(Pessoa pessoa) {
        this.proprietarios.add(pessoa);
        return this;
    }


    public Imovel removerProprietario(Pessoa pessoa) {
        this.proprietarios.remove(pessoa);
        return this;
    }


    @Override
    public String toString() {
        return "Imovel{" +
                "qtdQuartos=" + qtdQuartos +
                ", qtdBanheiros=" + qtdBanheiros +
                ", intQtdVagasDeGaragem=" + intQtdVagasDeGaragem +
                ", numeroRegistroNoCartorio='" + numeroRegistroNoCartorio + '\'' +
                ", proprietarios=" + proprietarios +
                '}';
    }
}
