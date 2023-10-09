package apap.ti.silogistik2106751341.dto;

import java.util.HashMap;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import apap.ti.silogistik2106751341.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106751341.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106751341.dto.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106751341.model.Barang;
import apap.ti.silogistik2106751341.model.Gudang;
import apap.ti.silogistik2106751341.model.GudangBarang;

@Mapper(componentModel = "spring")
public interface BarangMapper {

    Barang createBarangDTOtoBarang(CreateBarangRequestDTO createBarangRequestDTO);

    Barang updateBarangRequestDTOToBarang(UpdateBarangRequestDTO updateBarangRequestDTO);

    UpdateBarangRequestDTO barangToUpdateBarangRequestDTO(Barang barang);

    ReadBarangResponseDTO barangToReadBarangResponseDTO(Barang barang);

    @AfterMapping
    default void addStok(@MappingTarget ReadBarangResponseDTO readBarangResponseDTO, Barang barang) {
        int totStok = 0;
        for (GudangBarang gb : barang.getListGudangBarang()) {
            if (gb.getBarang().getSku().equals(barang.getSku())){
                totStok += gb.getStok();
            }
        }

        readBarangResponseDTO.setStok(totStok);

    }
    
}
