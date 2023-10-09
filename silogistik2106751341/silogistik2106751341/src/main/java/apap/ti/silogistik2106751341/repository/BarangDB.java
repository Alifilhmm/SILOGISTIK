package apap.ti.silogistik2106751341.repository;

import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106751341.model.Barang;
import apap.ti.silogistik2106751341.model.Gudang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BarangDB extends JpaRepository<Barang, String> {

    List<Barang> findBySku(String sku);

    @Query(value="SELECT * FROM barang b ORDER BY LOWER(merk_barang) ASC", nativeQuery = true)
    List<Barang> sortBarangByMerk(List<Barang> listBarang);
    
}
