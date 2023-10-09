package apap.ti.silogistik2106751341.dto;
import java.util.ArrayList;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import apap.ti.silogistik2106751341.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106751341.dto.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106751341.dto.response.ReadGudangResponseDTO;
import apap.ti.silogistik2106751341.model.Gudang;

@Mapper(componentModel = "spring")
public interface GudangMapper {
    Gudang createGudangDTOtoGudang(CreateGudangRequestDTO createGudangRequestDTO);

    Gudang updateGudangRequestDTOToGudang(UpdateGudangRequestDTO updateGudangRequestDTO);

    UpdateGudangRequestDTO gudangToUpdateGudangRequestDTO(Gudang gudang);
    
    @Mapping(target="listGudangBarang", ignore = true)
    ReadGudangResponseDTO readGudangResponseDTO(Gudang gudang);

    @AfterMapping
    default void cekGudang(@MappingTarget ReadGudangResponseDTO readGudangResponseDTO, Gudang gudang) {
        if (gudang.getListGudangBarang() != null) {
            readGudangResponseDTO.setListGudangBarang(gudang.getListGudangBarang());
        } else {
            readGudangResponseDTO.setListGudangBarang(new ArrayList<>());
        }
    }
}
