package apap.ti.silogistik2106751341.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private Long idPengiriman;

    @Size(max = 16)
    @Column(name = "nomor_pengiriman", nullable = false)
    private String nomor_pengiriman;

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
    private String tanggal_pengiriman;

    @NotNull
    @Column(name = "biaya_pengiriman", nullable = false)
    private Integer biaya_pengiriman;

    @NotNull
    @Column(name = "jenis_layanan", nullable = false)
    private Integer jenis_layanan;

    @Column(name = "waktu_permintaan", nullable = false)
    private LocalDateTime waktu_permintaan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "karyawan_id", referencedColumnName = "idKaryawan") 
    private Karyawan karyawan;

    @OneToMany(mappedBy = "pengiriman",  fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PengirimanBarang> listPengirimanBarang;



    
}
