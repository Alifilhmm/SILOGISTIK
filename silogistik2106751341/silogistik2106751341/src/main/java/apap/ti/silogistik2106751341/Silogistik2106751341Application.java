package apap.ti.silogistik2106751341;

import java.math.BigDecimal;
import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

import com.github.javafaker.Faker;

import apap.ti.silogistik2106751341.service.BarangService;
import apap.ti.silogistik2106751341.service.GudangBarangService;
import apap.ti.silogistik2106751341.service.GudangService;
import apap.ti.silogistik2106751341.service.KaryawanService;
import apap.ti.silogistik2106751341.dto.GudangMapper;
import apap.ti.silogistik2106751341.dto.KaryawanMapper;
import apap.ti.silogistik2106751341.dto.BarangMapper;
import apap.ti.silogistik2106751341.dto.GudangBarangMapper;
import apap.ti.silogistik2106751341.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106751341.dto.request.CreateGudangBarangRequestDTO;
import apap.ti.silogistik2106751341.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106751341.dto.request.CreateKaryawanRequestDTO;

@SpringBootApplication
public class Silogistik2106751341Application {

	public static void main(String[] args) {
		SpringApplication.run(Silogistik2106751341Application.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run(GudangService gudangService, GudangMapper gudangMapper, BarangService barangService, BarangMapper barangMapper, GudangBarangService gudangBarangService, GudangBarangMapper gudangBarangMapper
	, KaryawanMapper karyawanMapper, KaryawanService karyawanService) {
		return args -> {
			var faker = new Faker(new Locale("in-ID"));

			for (int i = 0 ; i < 3 ; i++){
				var fakerComp = faker.company();
				var gudangDTO = new CreateGudangRequestDTO();
				gudangDTO.setNama(fakerComp.name());
				gudangDTO.setAlamat_gudang(faker.address().fullAddress());
				var gudang = gudangMapper.createGudangDTOtoGudang(gudangDTO);
				gudangService.saveGudang(gudang);

				var karyawanDTO = new CreateKaryawanRequestDTO();
				karyawanDTO.setNamaKaryawan(faker.name().firstName() + " " + faker.name().lastName());
				karyawanDTO.setJenisKelamin(faker.number().numberBetween(1, 3));
				karyawanDTO.setTanggalLahir(faker.date().birthday());
				var karyawan = karyawanMapper.createKaryawanDTOtoKaryawan(karyawanDTO);
				karyawanService.saveKaryawan(karyawan);
			}

		};
	}

}
