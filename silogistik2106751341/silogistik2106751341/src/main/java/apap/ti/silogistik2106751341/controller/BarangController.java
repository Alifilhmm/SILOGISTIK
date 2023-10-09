package apap.ti.silogistik2106751341.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.ti.silogistik2106751341.dto.BarangMapper;
import apap.ti.silogistik2106751341.dto.GudangMapper;
import apap.ti.silogistik2106751341.dto.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106751341.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106751341.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106751341.model.Barang;
import apap.ti.silogistik2106751341.service.BarangService;
import apap.ti.silogistik2106751341.service.GudangService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

@Controller
public class BarangController {

    @Autowired
    private BarangService barangService;

    @Autowired
    private BarangMapper barangMapper;

    @GetMapping("barang/tambah")
    public String formAddBarang(Model model){

        var barangDTO = new CreateBarangRequestDTO();

        model.addAttribute("barangDTO", barangDTO);

        return "form-create-barang";
    }

    @PostMapping("barang/tambah")
    public String addBarang(@ModelAttribute CreateBarangRequestDTO createBarangRequestDTO, Model model) {

        var barang = barangMapper.createBarangDTOtoBarang(createBarangRequestDTO);
        barang.setSku(barangService.makeSKU(barang.getTipe_barang()));
        barangService.saveBarang(barang);

        model.addAttribute("barang", barang);

        return "success-create-barang";
    }

    @GetMapping("/barang")
    public String viewAllBarang(Model model) {

        List<Barang> listBarang = barangService.getAllBarang();
        List<ReadBarangResponseDTO> listBarangDTO = new ArrayList<>();

        for (Barang barang : listBarang){
            var barangDTO = barangMapper.barangToReadBarangResponseDTO(barang);
            listBarangDTO.add(barangDTO);
        }

        model.addAttribute("listBarang", listBarangDTO);

        return "viewall-barang";
    }

    @GetMapping("/barang/{idBarang}")
    public String detailBarang(@PathVariable("idBarang") String idBarang, Model model){
        var barang = barangService.getBarangById(idBarang);
        var tipe_barang_string = barangService.getTipeString(barang.getTipe_barang()); 
        int totStok = barangService.getTotStok(barang);

        model.addAttribute("barang", barang);
        model.addAttribute("tipe_barang_string", tipe_barang_string);
        model.addAttribute("totStok", totStok);

        return "view-barang";
    }

    @GetMapping("/barang/{idBarang}/ubah")
    public String FormUbahBarang(@PathVariable("idBarang") String idBarang, Model model) {
        var barang = barangService.getBarangById(idBarang);
        var barangDTO = barangMapper.barangToUpdateBarangRequestDTO(barang);

        model.addAttribute("barangDTO", barangDTO);

        return "form-ubah-barang";
    }

    @PostMapping("barang/ubah") 
    public String ubahBarang(@ModelAttribute UpdateBarangRequestDTO barangDTO, Model model){

        var barangFromDTO = barangMapper.updateBarangRequestDTOToBarang(barangDTO);
        var barang = barangService.updateBarang(barangFromDTO);

        model.addAttribute("barang", barang);

        return "success-ubah-barang";
    }

    




    
}
