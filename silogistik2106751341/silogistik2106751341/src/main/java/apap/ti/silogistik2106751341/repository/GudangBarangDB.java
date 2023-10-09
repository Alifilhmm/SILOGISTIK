package apap.ti.silogistik2106751341.repository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106751341.model.GudangBarang;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface GudangBarangDB extends JpaRepository<GudangBarang, Long> {

    List<GudangBarang> findAll();

}
