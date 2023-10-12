package apap.ti.silogistik2106751341.service;

import java.util.List;

import apap.ti.silogistik2106751341.model.Pengiriman;

public interface PengirimanService {

    void savePengiriman(Pengiriman pengiriman);

    Pengiriman getPengirimanById(Long idPengiriman);

    List<Pengiriman> getAllPengiriman();

    int getTotalKuantitasPesanan(Pengiriman Pengiriman);

    String makeNoPengiriman(Pengiriman pengiriman);

    String getTipeLayanan(Pengiriman pengiriman);

    String getPesananTime();

    String getLayananFullString(int tipe_layanan);

    String kuantitas4NoPengiriman(int totKuantitas);

    void addBarang2Pengiriman(Pengiriman pengiriman);

    void deletePengiriman(Pengiriman pengiriman);

    int counterPermintaan();

    //String getPesananDate();
    
}
