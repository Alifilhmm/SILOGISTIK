package apap.ti.silogistik2106751341.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751341.model.GudangBarang;
import apap.ti.silogistik2106751341.repository.GudangBarangDB;

@Service
public class GudangBarangServiceImpl implements GudangBarangService{

    @Autowired 
    GudangBarangDB gudangBarangDB;

    @Override
    public void saveGudangBarang(GudangBarang gudangBarang) {
        gudangBarangDB.save(gudangBarang);
    }
    
}
