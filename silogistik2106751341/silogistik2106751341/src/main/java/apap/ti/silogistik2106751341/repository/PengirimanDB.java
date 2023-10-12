package apap.ti.silogistik2106751341.repository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106751341.model.Pengiriman;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PengirimanDB extends JpaRepository<Pengiriman, Long>{
    
    List<Pengiriman> findByIdPengiriman(Long idPengiriman);
}
