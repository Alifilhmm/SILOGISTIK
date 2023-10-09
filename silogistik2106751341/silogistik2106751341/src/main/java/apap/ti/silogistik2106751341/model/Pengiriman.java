package apap.ti.silogistik2106751341.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pengiriman")
public class Pengiriman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long idPengiriman;

    @NotNull
    @Size(max = 12)
    @Column(name = "nomor_pengiriman", nullable = false)
    private String nomor_pengiriman;

    @NotNull
    @Column(name = "is_cancelled", nullable = false)
    private boolean is_cancelled = false;

    @NotNull
    @Column(name = "nama_penerima", nullable = false)
    private String nama_penerima;

    @NotNull
    @Column(name = "alamat_penerima", nullable = false)
    private String alamat_penerima;

    @NotNull
    @Column(name = "tanggal_pengiriman", nullable = false)
    private Date tanggal_pengiriman;

    @NotNull
    @Column(name = "biaya_pengiriman", nullable = false)
    private Integer biaya_pengiriman;

    @NotNull
    @Column(name = "jenis_layanan", nullable = false)
    private Integer jenis_layanan;

    @NotNull
    @Column(name = "waktu_permintaan", nullable = false)
    private Date waktu_permintaan;



    
}
