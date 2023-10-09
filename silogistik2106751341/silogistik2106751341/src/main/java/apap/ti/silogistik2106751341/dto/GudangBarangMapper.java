package apap.ti.silogistik2106751341.dto;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import apap.ti.silogistik2106751341.dto.request.CreateGudangBarangRequestDTO;
import apap.ti.silogistik2106751341.model.GudangBarang;

@Mapper(componentModel = "spring")
public interface GudangBarangMapper {
    
    GudangBarang createGudangBarangDTOtoGudangBarang(CreateGudangBarangRequestDTO createGudangBarangRequestDTO);
    
}
