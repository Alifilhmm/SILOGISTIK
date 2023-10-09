package apap.ti.silogistik2106751341.service;

import java.util.List;

import apap.ti.silogistik2106751341.model.Gudang;
import apap.ti.silogistik2106751341.model.GudangBarang;

public interface GudangService {
    
    void saveGudang(Gudang gudang);

    List<Gudang> getAllGudang();

    Gudang getGudangById(Long idGudang);

    List<GudangBarang> getListGudangBarang(Gudang gudang);

    Gudang addBarang2Gudang(Gudang gudangDTO);

    
}
