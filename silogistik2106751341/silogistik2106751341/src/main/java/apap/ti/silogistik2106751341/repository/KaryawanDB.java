package apap.ti.silogistik2106751341.repository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106751341.model.Karyawan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface KaryawanDB extends JpaRepository<Karyawan, Long>{

    List<Karyawan> findByIdKaryawan(long idKaryawan);
    
}
