package apap.ti.silogistik2106751341.service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751341.model.Gudang;
import apap.ti.silogistik2106751341.model.Pengiriman;
import apap.ti.silogistik2106751341.model.PengirimanBarang;
import apap.ti.silogistik2106751341.repository.PengirimanDB;

@Service
public class PengirimanServiceImpl implements PengirimanService{

    @Autowired 
    private PengirimanDB pengirimanDB;

    @Autowired 
    private BarangService barangService;

    @Override
    public void savePengiriman(Pengiriman pengiriman) {
        pengirimanDB.save(pengiriman);
    }

    @Override
    public Pengiriman getPengirimanById(Long idPengiriman) {
        for (Pengiriman pengiriman : getAllPengiriman()) {
            if(pengiriman.getIdPengiriman().equals(idPengiriman)) {
                return pengiriman;
            } 
        }
        return null;
    }

    @Override 
    public List<Pengiriman> getAllPengiriman() {
        return pengirimanDB.findAll();
    }

    @Override
    public int getTotalKuantitasPesanan(Pengiriman pengiriman) {

        int totKuantitas = 0;
        List<PengirimanBarang> listPesanan = pengiriman.getListPengirimanBarang();

        for (PengirimanBarang pesanan : listPesanan) {
            totKuantitas += pesanan.getKuantitas();
        }

        return totKuantitas;
    }

    @Override 
    public String getTipeLayanan(Pengiriman pengiriman) {
        String layanan = "";
        if (pengiriman.getJenis_layanan() == 1) {
            layanan = "SAM";
        } else if (pengiriman.getJenis_layanan() == 2) {
            layanan = "KIL";
        } else if (pengiriman.getJenis_layanan() == 3) {
            layanan = "REG";
        } else if (pengiriman.getJenis_layanan() == 4) {
            layanan = "HEM";
        } 
        return layanan;

    }

    @Override
    public String getLayananFullString(int tipe_layanan) {
        String layanan = "";
        if (tipe_layanan == 1) {
            layanan = "Same Day Delivery";
        } else if (tipe_layanan == 2) {
            layanan = "Kilat Delivery";
        } else if (tipe_layanan == 3) {
            layanan = "Reguler Delivary";
        } else if (tipe_layanan == 4) {
            layanan = "Hemat Delivery";
        } 
        return layanan;
    }

    @Override 
    public String getPesananTime() {
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = localTime.format(formatter);
        return formattedTime;
    }

    @Override
    public String kuantitas4NoPengiriman(int totKuantitas) {
        if (totKuantitas < 10) {
            return String.format("%02d", totKuantitas);
        } else if (totKuantitas > 99) {
            
            return String.format("%02d", totKuantitas % 100);
        } else {
            return String.valueOf(totKuantitas);
        }
    }

    @Override
    public String makeNoPengiriman(Pengiriman pengiriman) {
        String noPengiriman = "REQ";
        int totKuantitas = getTotalKuantitasPesanan(pengiriman);
        String stringTotKuantitas = kuantitas4NoPengiriman(totKuantitas);
        String layanan = getTipeLayanan(pengiriman);
        String pesananTime = getPesananTime();

        return noPengiriman + stringTotKuantitas + layanan + pesananTime; 
    }

    @Override
    public void addBarang2Pengiriman(Pengiriman pengiriman) {
        for(PengirimanBarang pb : pengiriman.getListPengirimanBarang()) {
                pb.setPengiriman(pengiriman);
                BigDecimal harga = barangService.getBarangById(pb.getBarang().getSku()).getHarga();
                int kuantitas = pb.getKuantitas();
                pb.setTotHarga(harga.multiply(BigDecimal.valueOf(kuantitas)));
        }
    }

    @Override
    public void deletePengiriman(Pengiriman pengiriman) {
        pengiriman.set_cancelled(Boolean.TRUE);

        pengirimanDB.save(pengiriman);
    }

    @Override
    public int counterPermintaan() {
        var listPengirimanAll = getAllPengiriman();
        int counter = 0;

        for (Pengiriman cekPengiriman : listPengirimanAll) {
            if (cekPengiriman.is_cancelled() == false) {
                counter +=1;
            }
        }

        return counter;
    }

    // @Override
    // public String getPesananDate(){
    //     LocalDate currentDate = LocalDate.now();
        
    //     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        
    //     String formattedDate = currentDate.format(formatter);

    //     return formattedDate;
    // }



    
}
