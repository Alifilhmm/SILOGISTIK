package apap.ti.silogistik2106751341.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import apap.ti.silogistik2106751341.model.Karyawan;
import apap.ti.silogistik2106751341.model.PengirimanBarang;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class CreatePengirimanRequestDTO {

    private String nomor_pengiriman;

    private String nama_penerima;

    private String alamat_penerima;

    private String tanggal_pengiriman;

    private Integer biaya_pengiriman;

    private Integer jenis_layanan;

    private LocalDateTime waktu_permintaan;

    private Karyawan karyawan;

    private List<PengirimanBarang> listPengirimanBarang;
    
}
