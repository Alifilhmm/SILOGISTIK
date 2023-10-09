package apap.ti.silogistik2106751341.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import apap.ti.silogistik2106751341.model.Barang;
import apap.ti.silogistik2106751341.model.GudangBarang;

import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadBarangResponseDTO {

    private String sku;

    private String merk;

    private BigDecimal harga;
    
    private int stok;
    
}
