package apap.ti.silogistik2106751341.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751341.model.Gudang;
import apap.ti.silogistik2106751341.model.GudangBarang;
import apap.ti.silogistik2106751341.repository.GudangDB;

@Service
public class GudangServiceImpl implements GudangService{

    @Autowired
    GudangDB gudangDb;

    @Override
    public void saveGudang(Gudang gudang){
        gudangDb.save(gudang);
    }
    
    @Override
    public List<Gudang> getAllGudang() {
        return gudangDb.findAll();
    }

    @Override
    public Gudang getGudangById(Long idGudang) {
        for (Gudang gudang : getAllGudang()) {
            if(gudang.getIdGudang().equals(idGudang)) {
                return gudang;
            } 
        }
        return null;
    }

    @Override
    public List<GudangBarang> getListGudangBarang(Gudang gudang) {
        return gudang.getListGudangBarang();
    }

    @Override
    public Gudang addBarang2Gudang(Gudang gudangDTO) {
        Gudang gudang = getGudangById(gudangDTO.getIdGudang());
        
        if (gudang != null) {
            for (GudangBarang gb : gudangDTO.getListGudangBarang()) {
                boolean update=false;
                for (GudangBarang cekGB : gudang.getListGudangBarang()) {
                    if (gb.getBarang().getSku().equals(cekGB.getBarang().getSku())) {
                        cekGB.setStok(gb.getStok());
                        update = true;
                    }
                }

                if (!update) {
                    gb.setGudang(gudang);
                    gudang.getListGudangBarang().add(gb);

                }
            }
            gudangDb.save(gudang);

        }

        return gudang;
    }


    
}
