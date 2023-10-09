package apap.ti.silogistik2106751341.repository;


import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106751341.model.Gudang;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GudangDB extends JpaRepository<Gudang, Long> {

    List<Gudang> findByIdGudang(Long idGudang);


    
}
