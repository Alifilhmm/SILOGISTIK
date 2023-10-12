package apap.ti.silogistik2106751341.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751341.model.Karyawan;
import apap.ti.silogistik2106751341.repository.KaryawanDB;

@Service
public class KaryawanServiceImpl implements KaryawanService{

    @Autowired
    private KaryawanDB karyawanDB;

    @Override
    public void saveKaryawan(Karyawan karyawan) {
        karyawanDB.save(karyawan);

    }

    @Override
    public List<Karyawan> getAllKaryawan() {
        return karyawanDB.findAll();
    }

    
    
}
