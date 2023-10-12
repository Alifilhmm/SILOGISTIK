package apap.ti.silogistik2106751341.dto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import apap.ti.silogistik2106751341.dto.request.CreatePengirimanRequestDTO;
import apap.ti.silogistik2106751341.model.Pengiriman;

@Mapper(componentModel = "spring")
public interface PengirimanMapper {

    Pengiriman CreatePengirimanDTOtoPengiriman(CreatePengirimanRequestDTO createPengirimanRequestDTO);
    
}