package apap.ti.silogistik2106751341.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751341.model.Barang;
import apap.ti.silogistik2106751341.model.Gudang;
import apap.ti.silogistik2106751341.model.GudangBarang;
import apap.ti.silogistik2106751341.repository.BarangDB;

@Service
public class BarangServiceImpl implements BarangService{

    @Autowired
    BarangDB barangDb;

    @Override
    public void saveBarang(Barang barang) {
        barangDb.save(barang);
    }

    @Override
    public List<Barang> getAllBarang() {
        return barangDb.findAll();
    }

    @Override
    public Barang getBarangById(String sku) {
        for (Barang barang : getAllBarang()) {
            if(barang.getSku().equals(sku)) {
                return barang;
            } 
        }
        return null;

    }

    @Override
    public String makeSKU(int tipe_barang) {

        String sku = "";

        if (tipe_barang == 1) {
            sku = "ELEC";
        } else if (tipe_barang == 2) {
            sku = "CLOT";
        } else if (tipe_barang == 3) {
            sku = "FOOD";
        } else if (tipe_barang == 4) {
            sku = "COSM";
        } else if (tipe_barang ==5) {
            sku = "TOOL";
        }

        return sku + String.format("%03d", countBarang()+1);
    }

    @Override
    public int countBarang() {
        return getAllBarang().size();
    }

    @Override
    public Barang updateBarang(Barang barangFromDTO) {
        Barang barang = getBarangById(barangFromDTO.getSku());
        if (barang != null) {
            barang.setMerk(barangFromDTO.getMerk());
            barang.setHarga(barangFromDTO.getHarga());
            barangDb.save(barang);
        }
        return barang;
    }
    
    @Override
    public String getTipeString(int tipe_barang) {
        String skuString = "";
        if (tipe_barang == 1) {
            skuString = "Produk Elektronik";
        } else if (tipe_barang == 2) {
            skuString = "Pakaian & Aksesoris";
        } else if (tipe_barang == 3) {
            skuString = "Makanan & Minuman";
        } else if (tipe_barang == 4) {
            skuString = "Kosmetik";
        } else if (tipe_barang ==5) {
            skuString = "Perlengkapan Rumah";
        }
        return skuString;
    }

    @Override
    public int getTotStok(Barang barang) {
        int stok = 0;
        for (GudangBarang gb : barang.getListGudangBarang()) {
            if (gb.getBarang().getSku().equals(barang.getSku())){
                stok += gb.getStok();
            }
        }

        return stok;
    }

    @Override
    public List<Barang> sortedListBarang(List<Barang> listBarang) {
        return barangDb.sortBarangByMerk(listBarang);
    }
}
