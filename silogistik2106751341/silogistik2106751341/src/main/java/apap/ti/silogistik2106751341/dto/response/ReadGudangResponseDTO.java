package apap.ti.silogistik2106751341.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

import apap.ti.silogistik2106751341.model.GudangBarang;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadGudangResponseDTO {

    private String nama;

    private String alamat_gudang;

    private List<GudangBarang> listGudangBarang;


    
}
