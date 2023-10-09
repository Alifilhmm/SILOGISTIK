package apap.ti.silogistik2106751341.dto.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class CreateKaryawanRequestDTO {

    private Long idKaryawan;

    private String namaKaryawan;

    private Integer jenisKelamin;

    private Date tanggalLahir;
    
}
