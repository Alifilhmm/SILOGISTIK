package apap.ti.silogistik2106751341.dto.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateGudangRequestDTO extends CreateGudangRequestDTO{
    private long id;
    
}