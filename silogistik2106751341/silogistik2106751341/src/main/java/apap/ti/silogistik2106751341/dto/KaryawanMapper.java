package apap.ti.silogistik2106751341.dto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import apap.ti.silogistik2106751341.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106751341.model.Karyawan;

@Mapper(componentModel = "spring")
public interface KaryawanMapper {

    Karyawan createKaryawanDTOtoKaryawan(CreateKaryawanRequestDTO createKaryawanRequestDTO);
    
}
