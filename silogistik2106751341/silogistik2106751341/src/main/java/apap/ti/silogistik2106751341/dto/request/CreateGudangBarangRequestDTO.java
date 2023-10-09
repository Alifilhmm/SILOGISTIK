package apap.ti.silogistik2106751341.dto.request;
import apap.ti.silogistik2106751341.model.Barang;
import apap.ti.silogistik2106751341.model.Gudang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class CreateGudangBarangRequestDTO {

    private long id;

    private Gudang gudang;

    private Barang barang;

    private Integer stok;
    
}
