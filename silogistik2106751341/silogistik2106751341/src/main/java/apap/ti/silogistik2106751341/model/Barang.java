package apap.ti.silogistik2106751341.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "barang")
public class Barang {
    
    @Id
    @NotNull
    private String sku;

    @NotNull
    @Column(name = "tipe_barang", nullable = false)
    private Integer tipe_barang;

    @NotNull
    @Column(name = "merk_barang", nullable = false)
    private String merk;

    @NotNull
    @Column(name = "harga", nullable = false)
    private BigDecimal harga;

    @OneToMany(mappedBy = "barang",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GudangBarang> listGudangBarang;
    
}
