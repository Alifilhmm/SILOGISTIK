package apap.ti.silogistik2106751341.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

import java.util.List;

import apap.ti.silogistik2106751341.model.GudangBarang;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class CreateGudangRequestDTO {

    private Long idGudang;

    private String nama;

    private String alamat_gudang;

    private List<GudangBarang> listGudangBarang;
    
}
