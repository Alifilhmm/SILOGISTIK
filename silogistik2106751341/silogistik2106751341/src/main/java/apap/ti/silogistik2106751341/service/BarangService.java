package apap.ti.silogistik2106751341.service;

import java.util.List;

import apap.ti.silogistik2106751341.model.Barang;
import apap.ti.silogistik2106751341.model.Gudang;

public interface BarangService {

    void saveBarang(Barang barang);

    List<Barang> getAllBarang();

    Barang getBarangById(String sku);

    String makeSKU(int tipe_barang);

    int countBarang();

    Barang updateBarang(Barang barangFromDTO);

    String getTipeString(int tipe_barang);

    int getTotStok(Barang barang);

    List<Barang> sortedListBarang(List<Barang> listBarang);

    
}
