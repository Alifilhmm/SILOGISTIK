package apap.ti.silogistik2106751341.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import apap.ti.silogistik2106751341.dto.BarangMapper;
import apap.ti.silogistik2106751341.dto.GudangBarangMapper;
import apap.ti.silogistik2106751341.dto.GudangMapper;
import apap.ti.silogistik2106751341.dto.request.CreateGudangBarangRequestDTO;
import apap.ti.silogistik2106751341.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106751341.dto.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106751341.model.Gudang;
import apap.ti.silogistik2106751341.model.GudangBarang;
import apap.ti.silogistik2106751341.service.BarangService;
import apap.ti.silogistik2106751341.service.GudangBarangService;
import apap.ti.silogistik2106751341.service.GudangService;
import apap.ti.silogistik2106751341.service.KaryawanService;
import apap.ti.silogistik2106751341.service.PengirimanService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

@Controller
public class GudangController {

    @Autowired
    private GudangService gudangService;

    @Autowired
    private GudangMapper gudangMapper;

    @Autowired
    private BarangService barangService;

    @Autowired 
    private BarangMapper barangMapper;

    @Autowired
    private GudangBarangMapper gudangBarangMapper;

    @Autowired 
    private GudangBarangService gudangBarangService;

    @Autowired
    private KaryawanService karyawanService;

    @Autowired
    private PengirimanService pengirimanService;

    @GetMapping("/")
    public String home(Model model) {
        var totBarang = barangService.getAllBarang().size();
        var totGudang = gudangService.getAllGudang().size();
        var totKaryawan = karyawanService.getAllKaryawan().size();
        var totPengiriman = pengirimanService.getAllPengiriman().size();

        model.addAttribute("totBarang", totBarang);
        model.addAttribute("totGudang", totGudang);
        model.addAttribute("totKaryawan", totKaryawan);
        model.addAttribute("totPengiriman", totPengiriman);
        return "home";
    }

    @GetMapping("/gudang")
    public String viewAllGudang(Model model) {
        List<Gudang> listGudang = gudangService.getAllGudang();

        model.addAttribute("listGudang", listGudang);

        return "viewall-gudang";
        
    }

    @GetMapping("/gudang/{idGudang}")
    public String detailGudang(@PathVariable("idGudang")long idGudang, Model model) {

        var gudang = gudangService.getGudangById(idGudang);
        var listGudangBarang = gudang.getListGudangBarang();

        model.addAttribute("gudang", gudang);
        model.addAttribute("listGudangBarang", listGudangBarang);

        return "view-gudang";
    }

    @GetMapping("/gudang/{idGudang}/restock-barang") 
    public String formRestockBarang(@PathVariable("idGudang")long idGudang, Model model) {

        var gudang = gudangService.getGudangById(idGudang);
        var gudangDTO = gudangMapper.gudangToUpdateGudangRequestDTO(gudang);
        var listBarangExisting = barangService.getAllBarang();

        model.addAttribute("listBarangExisting", listBarangExisting);
        model.addAttribute("gudangDTO", gudangDTO);
        //System.out.println(gudangDTO.getNama());

        return "restock-barang";
    }


    @PostMapping(value="/gudang/{idGudang}/restock-barang", params = {"addRow"})
    public String addRowBarangGudang(@PathVariable("idGudang")long idGudang, @ModelAttribute UpdateGudangRequestDTO updateGudangRequestDTO, Model model) {
        var gudang = gudangService.getGudangById(idGudang);
        updateGudangRequestDTO.setNama(gudang.getNama());
        updateGudangRequestDTO.setAlamat_gudang(gudang.getAlamat_gudang());
        if (updateGudangRequestDTO.getListGudangBarang() == null || updateGudangRequestDTO.getListGudangBarang().size() == 0) {
            updateGudangRequestDTO.setListGudangBarang((new ArrayList<>()));
        }

        updateGudangRequestDTO.getListGudangBarang().add(new GudangBarang());

        model.addAttribute("listBarangExisting", barangService.getAllBarang());

        model.addAttribute("gudangDTO", updateGudangRequestDTO);
        //System.out.println(updateGudangRequestDTO.getNama());

        return "restock-barang";
    }

    @PostMapping(value="/gudang/{idGudang}/restock-barang")
    public String addBarangToGudang(@ModelAttribute UpdateGudangRequestDTO gudangDTO, Model model){
        
        var gudangFromDto = gudangMapper.updateGudangRequestDTOToGudang(gudangDTO);

        var gudang = gudangService.addBarang2Gudang(gudangFromDto);

        model.addAttribute("gudang", gudang);

        return "success-add-barang";

    }

    @GetMapping("/gudang/cari-barang")
    public String insertBarang(Model model) {

        var listBarang = barangService.getAllBarang();
        var sortedListBarang = barangService.sortedListBarang(listBarang);
        model.addAttribute("listBarang", sortedListBarang);

        return "search-barang";


    }

    @GetMapping(value ="/gudang/cari-barang", params={"sku"})
    public String searchBarang(@RequestParam(value="sku", required = true) String sku, Model model) {
        var barang = barangService.getBarangById(sku);
        var listGudangBarang = barang.getListGudangBarang();
        // List<Gudang> insideGudang = new ArrayList<Gudang>();

        // for (GudangBarang gb : listGudangBarang) {
        //     insideGudang.add(gb.getGudang());
        // }

        var listBarang = barangService.getAllBarang();
        var sortedListBarang = barangService.sortedListBarang(listBarang);
        
        model.addAttribute("listBarang", sortedListBarang);;
        model.addAttribute("listGudangBarang", listGudangBarang);

        return "search-barang";
    }
    
}
