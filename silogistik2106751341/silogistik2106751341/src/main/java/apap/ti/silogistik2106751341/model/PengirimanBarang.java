package apap.ti.silogistik2106751341.model;
import java.math.BigDecimal;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pengiriman_barang")
public class PengirimanBarang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_permintaan_pengiriman", referencedColumnName = "idPengiriman")
    private Pengiriman pengiriman;

    @ManyToOne
    @JoinColumn(name = "sku_barang", referencedColumnName = "sku")
    private Barang barang;

    @NotNull
    @Column(name = "kuantitas_pesanan", nullable = false)
    private Integer kuantitas;

    private BigDecimal totHarga;
    
}
