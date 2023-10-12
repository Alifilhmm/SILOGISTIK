package apap.ti.silogistik2106751341.service;

import java.util.List;

import apap.ti.silogistik2106751341.model.Karyawan;

public interface KaryawanService {

    void saveKaryawan(Karyawan karyawan);

    List<Karyawan> getAllKaryawan();
    
}
