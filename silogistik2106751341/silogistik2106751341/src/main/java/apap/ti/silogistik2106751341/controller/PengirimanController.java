package apap.ti.silogistik2106751341.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.ti.silogistik2106751341.dto.PengirimanMapper;
import apap.ti.silogistik2106751341.dto.request.CreatePengirimanRequestDTO;
import apap.ti.silogistik2106751341.model.Pengiriman;
import apap.ti.silogistik2106751341.model.PengirimanBarang;
import apap.ti.silogistik2106751341.repository.PengirimanDB;
import apap.ti.silogistik2106751341.service.BarangService;
import apap.ti.silogistik2106751341.service.KaryawanService;
import apap.ti.silogistik2106751341.service.PengirimanService;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

@Controller
public class PengirimanController {

    @Autowired 
    private PengirimanService pengirimanService;

    @Autowired
    private PengirimanMapper pengirimanMapper;

    @Autowired 
    private KaryawanService karyawanService;

    @Autowired 
    private BarangService barangService;

    @GetMapping("/permintaan-pengiriman")
    public String viewallPengiriman(Model model) {
        List<Pengiriman> listPengiriman = pengirimanService.getAllPengiriman();

        List<Pengiriman> listPengirimanSorted = new ArrayList<Pengiriman>();

        for(Pengiriman p: listPengiriman) {
            listPengirimanSorted.add(p);
        }

        Collections.sort(listPengirimanSorted, Comparator.comparing(Pengiriman::getWaktu_permintaan).reversed());

        model.addAttribute("listPengiriman", listPengirimanSorted);

        return "viewall-permintaan";
    }

    @GetMapping("/permintaan-pengiriman/{idPermintaanPengiriman}")
    public String detailPengiriman(@PathVariable("idPermintaanPengiriman") long idPermintaanPengiriman, Model model) {
        
        var pengiriman = pengirimanService.getPengirimanById(idPermintaanPengiriman);
        var listPengirimanBarang = pengiriman.getListPengirimanBarang();
        var jenisLayananString = pengirimanService.getLayananFullString(pengiriman.getJenis_layanan());


        model.addAttribute("pengiriman", pengiriman);
        model.addAttribute("listPengirimanBarang", listPengirimanBarang);
        model.addAttribute("jenisLayananString", jenisLayananString);

        return "view-permintaan";

    }

    @GetMapping("/permintaan-pengiriman/tambah") 
    public String addPengiriman(Model model) {

        var pengirimanDTO = new CreatePengirimanRequestDTO();
        var listKaryawan = karyawanService.getAllKaryawan();

        model.addAttribute("pengirimanDTO", pengirimanDTO);
        model.addAttribute("listKaryawan", listKaryawan);
        
        return "form-create-permintaan";

    }

    @PostMapping(value = "/permintaan-pengiriman/tambah", params = {"addRow"})
    public String addRowPengirimanBarang(@ModelAttribute CreatePengirimanRequestDTO createPengirimanRequestDTO, Model model) {
        if (createPengirimanRequestDTO.getListPengirimanBarang() == null || createPengirimanRequestDTO.getListPengirimanBarang().size() == 0) {
            createPengirimanRequestDTO.setListPengirimanBarang(new ArrayList<>());
        }

        createPengirimanRequestDTO.getListPengirimanBarang().add(new PengirimanBarang());

        model.addAttribute("listBarangExisting", barangService.getAllBarang());
        model.addAttribute("listKaryawan", karyawanService.getAllKaryawan());
        model.addAttribute("pengirimanDTO", createPengirimanRequestDTO);

        return "form-create-permintaan";
    }

    @PostMapping("/permintaan-pengiriman/tambah")
    public String addPengiriman(@ModelAttribute CreatePengirimanRequestDTO pengirimanDTO, Model model) {

        var pengiriman = pengirimanMapper.CreatePengirimanDTOtoPengiriman(pengirimanDTO);
        pengirimanService.addBarang2Pengiriman(pengiriman);
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTimeString = currentDateTime.format(formatter);
        LocalDateTime formattedDateTime = LocalDateTime.parse(formattedDateTimeString, formatter);

        pengiriman.setNomor_pengiriman(pengirimanService.makeNoPengiriman(pengiriman));
        pengiriman.setWaktu_permintaan(formattedDateTime);
        pengirimanService.savePengiriman(pengiriman);

        model.addAttribute("pengiriman", pengiriman);

        return "success-create-permintaan";
    
    }

    @GetMapping("/permintaan-pengiriman/{idPermintaanPengiriman}/cancel")
    public String deleteBuku(@PathVariable("idPermintaanPengiriman") long idPermintaanPengiriman, Model model){
        var pengiriman = pengirimanService.getPengirimanById(idPermintaanPengiriman);

        LocalDateTime dateTimePermintaan = pengiriman.getWaktu_permintaan();
        LocalDateTime currentDateTime = LocalDateTime.now();

        Duration duration = Duration.between(dateTimePermintaan, currentDateTime);
        long hours = duration.toHours();

        model.addAttribute("pengiriman", pengiriman);

        if (hours > 24) {
            return "cannot-delete-permintaan";
        } else {
            pengirimanService.deletePengiriman(pengiriman);
            return "success-delete-permintaan";
        }

    }



    
}
