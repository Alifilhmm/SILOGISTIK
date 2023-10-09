package apap.ti.silogistik2106751341.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import apap.ti.silogistik2106751341.model.GudangBarang;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class CreateBarangRequestDTO {

    private String sku;

    private Integer tipe_barang;

    private String merk;

    private BigDecimal harga;
    
    private List<GudangBarang> listGudangBarang;
}
