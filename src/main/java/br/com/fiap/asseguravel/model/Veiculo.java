package br.com.fiap.asseguravel.model;

import br.com.fiap.pessoa.model.Pessoa;
import jakarta.persistence.*;

@Entity
@Table(
        name = "tb_veiculo",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_chassis_veiculo",
                        columnNames = "chassis_veiculo"
                ),
                @UniqueConstraint(
                        name = "uk_placa_veiculo",
                        columnNames = "placa_veiculo"
                )
        }
)
public class Veiculo {
    @Id
    @GeneratedValue(
            generator = "seq_veiculo",
            strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
            name = "seq_veiculo",
            sequenceName = "seq_veiculo",
            allocationSize = 1
    )
    @Column(name = "id_veiculo")
    private Long id;

    @Column(name = "chassis_veiculo")
    private String chassis;

    @Column(name = "placa_veiculo")
    private String placa;

    @Column(name = "modelo_veiculo")
    private String modelo;

    @Column(name = "ano_fabricacao_veiculo")
    private int anoDeFabricacao;

    @Column(name = "fabricante_veiculo")
    private String fabricante;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(
            name = "id_proprietario_veiculo",
            referencedColumnName = "id_pessoa",
            foreignKey = @ForeignKey(name = "fk_proprietario_veiculo")
    )
    private Pessoa proprietario;

    public Veiculo() {
    }

    public Veiculo(String chassis, String placa, String modelo, int anoDeFabricacao, String fabricante, Pessoa proprietario) {
        this.chassis = chassis;
        this.placa = placa;
        this.modelo = modelo;
        this.anoDeFabricacao = anoDeFabricacao;
        this.fabricante = fabricante;
        this.proprietario = proprietario;
    }

    public String getChassis() {
        return chassis;
    }

    public Veiculo setChassis(String chassis) {
        this.chassis = chassis;
        return this;
    }

    public String getPlaca() {
        return placa;
    }

    public Veiculo setPlaca(String placa) {
        this.placa = placa;
        return this;
    }

    public String getModelo() {
        return modelo;
    }

    public Veiculo setModelo(String modelo) {
        this.modelo = modelo;
        return this;
    }

    public int getAnoDeFabricacao() {
        return anoDeFabricacao;
    }

    public Veiculo setAnoDeFabricacao(int anoDeFabricacao) {
        this.anoDeFabricacao = anoDeFabricacao;
        return this;
    }

    public String getFabricante() {
        return fabricante;
    }

    public Veiculo setFabricante(String fabricante) {
        this.fabricante = fabricante;
        return this;
    }


    public Pessoa getProprietario() {
        return proprietario;
    }

    public Veiculo setProprietario(Pessoa proprietario) {
        this.proprietario = proprietario;
        return this;
    }
}
