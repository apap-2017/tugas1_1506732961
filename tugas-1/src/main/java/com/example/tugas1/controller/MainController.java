package com.example.tugas1.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tugas1.model.KecamatanModel;
import com.example.tugas1.model.KeluargaModel;
import com.example.tugas1.model.KelurahanModel;
import com.example.tugas1.model.KotaModel;
import com.example.tugas1.model.PendudukModel;
import com.example.tugas1.service.KecamatanService;
import com.example.tugas1.service.KeluargaService;
import com.example.tugas1.service.KelurahanService;
import com.example.tugas1.service.KotaService;
import com.example.tugas1.service.PendudukService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {
	
	@Autowired
	PendudukService pendudukDAO; 
	@Autowired
	KeluargaService keluargaDAO;
	@Autowired
	KelurahanService kelurahanDAO;
	@Autowired
	KecamatanService kecamatanDAO;
	@Autowired
	KotaService kotaDAO;
	
	@RequestMapping("/")
	public String index()
	{
		return "index";
	}
	
	@RequestMapping(value="/penduduk", method= RequestMethod.GET)
	public String showbynik(@RequestParam(value ="nik") String nik, Model model)
	{
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		
		if(penduduk !=null) {
			
			KeluargaModel keluarga = keluargaDAO.selectKeluargabyId(penduduk.getId_keluarga());
			KelurahanModel kelurahan = kelurahanDAO.selectKelurahanbyId(keluarga.getId_kelurahan());
			KecamatanModel kecamatan = kecamatanDAO.selectKecamatanbyId(kelurahan.getId_kecamatan());
			KotaModel kota = kotaDAO.selectKotabyId(kecamatan.getId_kota());
			model.addAttribute("keluarga", keluarga);
			model.addAttribute("kelurahan", kelurahan);
			model.addAttribute("kecamatan", kecamatan);
			model.addAttribute("kota", kota);
			
			String tahun = penduduk.getTanggal_lahir().substring(0,4);
			String bulan = penduduk.getTanggal_lahir().substring(5,7);
			String tanggal = penduduk.getTanggal_lahir().substring(8); 
			String newBulan = "";
			switch(bulan) {
			case "01":
				newBulan = "Januari";
				break;
			case "02":
				newBulan = "Februari";
				break;
			case "03":
				newBulan = "Maret";
				break;
			case "04":
				newBulan = "April";
				break;
			case "05":
				newBulan = "Mei";
				break;
			case "06":
				newBulan = "Juni";
				break;
			case "07":
				newBulan = "Juli";
				break;
			case "08":
				newBulan = "Agustus";
				break;
			case "09":
				newBulan = "September";
				break;
			case "10":
				newBulan = "Oktober";
				break;
			case "11":
				newBulan = "November";
				break;
			case "12":
				newBulan = "Desember";
				break;			
			}
			
			String newtanggal = tanggal + " " + newBulan + " " + tahun;
			
			penduduk.setTanggal_lahir(newtanggal);
			model.addAttribute("page_title", "Lihat Penduduk");
			model.addAttribute("penduduk", penduduk);
			return "showpenduduk";

			} else {
				return "not-found";
			}
	}
	
	@RequestMapping(value="/keluarga", method=RequestMethod.GET)
	public String viewkeluarga(@RequestParam(value="nomor_kk") String nomor_kk, Model model)
	{
		
		KeluargaModel keluarga = keluargaDAO.selectKeluarga(nomor_kk);
		if(keluarga !=null) {
			model.addAttribute("page_title", "Lihat Keluarga");
			model.addAttribute("keluarga", keluarga);
			KelurahanModel kelurahan = kelurahanDAO.selectKelurahanbyId(keluarga.getId_kelurahan());
			KecamatanModel kecamatan = kecamatanDAO.selectKecamatanbyId(kelurahan.getId_kecamatan());
			KotaModel kota = kotaDAO.selectKotabyId(kecamatan.getId_kota());
			model.addAttribute("kelurahan", kelurahan);
			model.addAttribute("kecamatan", kecamatan);
			model.addAttribute("kota", kota);
			
			return "showkeluarga";
		} else {
			return "not-found";
		}
	}
	
	@RequestMapping("/penduduk/tambah")
	public String tambah(Model model)
	{
		PendudukModel penduduk = new PendudukModel();
		model.addAttribute("penduduk", penduduk);
		model.addAttribute("page_title", "Tambah Penduduk");
		return "form-add";
	}
	
	@RequestMapping(value="/penduduk/tambah/submit", method=RequestMethod.POST)
	public String tambahProses(PendudukModel penduduk1, Model model)
	{
		KeluargaModel keluarga = keluargaDAO.selectKeluargabyId(penduduk1.getId_keluarga());
		KelurahanModel kelurahan = kelurahanDAO.selectKelurahanbyId(keluarga.getId_kelurahan());
		KecamatanModel kecamatan = kecamatanDAO.selectKecamatanbyId(kelurahan.getId_kecamatan());
		model.addAttribute("keluarga", keluarga);
		model.addAttribute("kelurahan", kelurahan);
		model.addAttribute("kecamatan", kecamatan);
		
		
		String tahun = penduduk1.getTanggal_lahir().substring(2,4);
		String bulan = penduduk1.getTanggal_lahir().substring(5,7);
		String tanggal = penduduk1.getTanggal_lahir().substring(8); 
		
		int newtanggal;
		if(penduduk1.getJenis_kelamin() == 1) {
			int tanggalint = Integer.parseInt(tanggal);
			newtanggal = tanggalint + 40;
			
		}  else {
			newtanggal = Integer.parseInt(tanggal);
		}
		
		String findNik = kecamatan.getKode_kecamatan().substring(0,6) + newtanggal + bulan + tahun;
		int counter;
		String counterstring;
		String nik = "";
		PendudukModel penduduk2 = pendudukDAO.cekPenduduk(findNik);
		
		if(penduduk1.getJenis_kelamin() ==0 ) {
			if(penduduk2==null) {
				counter = 1;
				counterstring = String.format("%04d", counter);
				nik = findNik + counterstring;
				penduduk1.setNik(nik);
				pendudukDAO.addPenduduk(penduduk1);
				
			} else {
				String last4 = penduduk2.getNik().substring(12);
				counter = Integer.parseInt(last4) + 1;
				counterstring = String.format("%04d", counter);
				nik = findNik + counterstring;
				penduduk1.setNik(nik);
				pendudukDAO.addPenduduk(penduduk1);
			}
		} else {
			if(penduduk2==null) {
				counter = 1;
				counterstring = String.format("%04d", counter);
				nik = findNik + counterstring;
				penduduk1.setNik(nik);
				pendudukDAO.addPenduduk(penduduk1);
				
			} else {
				String last4 = penduduk2.getNik().substring(12);
				counter = Integer.parseInt(last4) + 1;
				counterstring = String.format("%04d", counter);
				nik = findNik + counterstring;
				penduduk1.setNik(nik);
				pendudukDAO.addPenduduk(penduduk1);
			}
		}
		
		model.addAttribute("page_title","Input sukses");
		model.addAttribute("nik", nik);
		
		return "success-add";
	}
	
	@RequestMapping("/keluarga/tambah")
	public String keluargaTambah(Model model)
	{
		KeluargaModel keluarga = new KeluargaModel();
		
		List<KelurahanModel> kelurahans = keluargaDAO.selectAllKelurahan();
		
		model.addAttribute("keluarga", keluarga);
		model.addAttribute("page_title", "Tambah Keluarga");
		model.addAttribute("kelurahans", kelurahans);
		return "form-add2";
	}
	
	@RequestMapping(value="/keluarga/tambah/submit", method=RequestMethod.POST)
	public String tambahKeluargaProses(KeluargaModel keluarga, Model model)
	{
		
		KelurahanModel kelurahan = kelurahanDAO.selectKelurahanbyId(keluarga.getId_kelurahan());
		KecamatanModel kecamatan = kecamatanDAO.selectKecamatanbyId(kelurahan.getId_kecamatan());
		model.addAttribute("kelurahan2", kelurahan);
		model.addAttribute("kecamatan", kecamatan);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		String datenow = dtf.format(localDate);
		
		String tahun = datenow.substring(2,4);
		String bulan = datenow.substring(5,7);
		String tanggal = datenow.substring(8); 
		
		String findNkk = kecamatan.getKode_kecamatan().substring(0,6) + tanggal + bulan + tahun;
		
		int counter;
		String counterstring;
		String nomor_kk = "";
		KeluargaModel keluarga2 = keluargaDAO.cekKeluarga(findNkk);
		
		if(keluarga2 == null) {
			counter = 1;
			counterstring = String.format("%04d", counter);
			nomor_kk = findNkk + counterstring;
			keluarga.setNomor_kk(nomor_kk);
			keluargaDAO.addKeluarga(keluarga);
		} else {
			String last4 = keluarga2.getNomor_kk().substring(12);
			counter = Integer.parseInt(last4) + 1;
			counterstring = String.format("%04d", counter);
			nomor_kk = findNkk + counterstring;
			keluarga.setNomor_kk(nomor_kk);
			keluargaDAO.addKeluarga(keluarga);
		}
			
		model.addAttribute("page_title","Input sukses");
		model.addAttribute("nomor_kk", nomor_kk);
		
		return "success-add2";
	}
	
	
	@RequestMapping(value="/penduduk/ubah/{nik}")
	public String ubahPenduduk(Model model, @PathVariable(value = "nik") String nik)
	{
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		model.addAttribute("penduduk", penduduk);
		return "form-update";
	}
	
	@RequestMapping(value="/penduduk/ubah/submit", method=RequestMethod.POST)
	public String ubahPendudukProses(PendudukModel penduduk, Model model)
	{
		String niklama = penduduk.getNik();
		log.info(niklama);
		
		KeluargaModel keluarga = keluargaDAO.selectKeluargabyId(penduduk.getId_keluarga());
		KelurahanModel kelurahan = kelurahanDAO.selectKelurahanbyId(keluarga.getId_kelurahan());
		KecamatanModel kecamatan = kecamatanDAO.selectKecamatanbyId(kelurahan.getId_kecamatan());
		model.addAttribute("keluarga", keluarga);
		model.addAttribute("kelurahan", kelurahan);
		model.addAttribute("kecamatan", kecamatan);
		
		String tahun = penduduk.getTanggal_lahir().substring(2,4);
		String bulan = penduduk.getTanggal_lahir().substring(5,7);
		String tanggal = penduduk.getTanggal_lahir().substring(8); 
		
		int newtanggal;
		if(penduduk.getJenis_kelamin() == 1) {
			int tanggalint = Integer.parseInt(tanggal);
			newtanggal = tanggalint + 40;
			
		}  else {
			newtanggal = Integer.parseInt(tanggal);
		}
		
		String findNik = kecamatan.getKode_kecamatan().substring(0,6) + newtanggal + bulan + tahun;
		int counter;
		String counterstring;	
		String nik = "";
		
		String niklama12 = niklama.substring(0,12);
		if(findNik.equals(niklama12)) {
			pendudukDAO.updatePenduduk(penduduk);
		} else {
			log.info("masuk sini1");
			PendudukModel penduduk3 = pendudukDAO.cekPenduduk(findNik);
			
			if(penduduk3!=null) {
				log.info("masuk sini2");
				String last4 = penduduk3.getNik().substring(12);
				counter = Integer.parseInt(last4) + 1;
				counterstring = String.format("%04d", counter);
				nik = findNik + counterstring;
				penduduk.setNik(nik);
				log.info(nik);
				pendudukDAO.updatePenduduk(penduduk);
			} else {
				log.info("masuk sini2");
				counter = 1;
				counterstring = String.format("%04d", counter);
				nik = findNik + counterstring;
				penduduk.setNik(nik);
				log.info(nik);
				pendudukDAO.updatePenduduk(penduduk);
			}
		}
		
		model.addAttribute("page_title","Update sukses");
		model.addAttribute("niklama", niklama);
		return "success-update";
	}
	
	@RequestMapping("/keluarga/ubah/{nomor_kk}")
	public String ubahKeluarga(Model model,  @PathVariable(value = "nomor_kk") String nomor_kk)
	{
		KeluargaModel keluarga = keluargaDAO.selectKeluarga2(nomor_kk);
		model.addAttribute("keluarga", keluarga);
		List<KelurahanModel> kelurahans = keluargaDAO.selectAllKelurahan();
		
		model.addAttribute("page_title", "Update Keluarga");
		model.addAttribute("kelurahans", kelurahans);
		return "form-update2";
	}
	
	@RequestMapping("/keluarga/ubah/submit")
	public String ubahKeluargaProses(Model model, KeluargaModel keluarga)
	{	
		String nkklama = keluarga.getNomor_kk();
		KelurahanModel kelurahan = kelurahanDAO.selectKelurahanbyId(keluarga.getId_kelurahan());
		KecamatanModel kecamatan = kecamatanDAO.selectKecamatanbyId(kelurahan.getId_kecamatan());
		model.addAttribute("kelurahan2", kelurahan);
		model.addAttribute("kecamatan", kecamatan);
		
		String findNkk = kecamatan.getKode_kecamatan().substring(0,6) + nkklama.substring(6,12);
		
		int counter;
		String counterstring;
		String nomor_kk = "";
		KeluargaModel keluarga2 = keluargaDAO.cekKeluarga(findNkk);
		
		if(findNkk.equals(nkklama)) {
			keluargaDAO.updateKeluarga(keluarga);
		} else {
			if(keluarga2==null) {
				counter = 1;
				counterstring = String.format("%04d", counter);
				nomor_kk = findNkk + counterstring;
				keluarga.setNomor_kk(nomor_kk);
				keluargaDAO.updateKeluarga(keluarga);
			} else {
				String last4 = keluarga2.getNomor_kk().substring(12);
				counter = Integer.parseInt(last4) + 1;
				counterstring = String.format("%04d", counter);
				nomor_kk = findNkk + counterstring;
				keluarga.setNomor_kk(nomor_kk);
				keluargaDAO.updateKeluarga(keluarga);
			}
		}
		model.addAttribute("page_title", "Update Sukses" );
		model.addAttribute("nkklama", nkklama);
		return "success-update2";
	}
	
	@RequestMapping(value="/penduduk/mati", method=RequestMethod.POST)
	public String switchMati(PendudukModel penduduk, Model model)
	{
		KeluargaModel keluarga = keluargaDAO.selectKeluargabyId(penduduk.getId_keluarga());
		KelurahanModel kelurahan = kelurahanDAO.selectKelurahanbyId(keluarga.getId_kelurahan());
		KecamatanModel kecamatan = kecamatanDAO.selectKecamatanbyId(kelurahan.getId_kecamatan());
		KotaModel kota = kotaDAO.selectKotabyId(kecamatan.getId_kota());
		model.addAttribute("keluarga", keluarga);
		model.addAttribute("kelurahan", kelurahan);
		model.addAttribute("kecamatan", kecamatan);
		model.addAttribute("kota", kota);
		
		String[] tgllahir = penduduk.getTanggal_lahir().split(" "); 
		String tahun = tgllahir[2];
		String bulan = tgllahir[1];
		String tanggal = tgllahir[0]; 
		
		String newBulan = "";
		switch(bulan) {
		case "Januari":
			newBulan = "01";
			break;
		case "Februari":
			newBulan = "02";
			break;
		case "Maret":
			newBulan = "03";
			break;
		case "April":
			newBulan = "04";
			break;
		case "Mei":
			newBulan = "05";
			break;
		case "Juni":
			newBulan = "06";
			break;
		case "Juli":
			newBulan = "07";
			break;
		case "Agustus":
			newBulan = "08";
			break;
		case "September":
			newBulan = "09";
			break;
		case "Oktober":
			newBulan = "10";
			break;
		case "November":
			newBulan = "11";
			break;
		case "Desember":
			newBulan = "12";
			break;			
		}
		
		String newtanggal = tahun + "-" + newBulan + "-" + tanggal;
		
		penduduk.setTanggal_lahir(newtanggal);
		log.info("penduduk {}", penduduk);
		pendudukDAO.switchMati(penduduk);
		model.addAttribute("penduduk", penduduk);
		model.addAttribute("page_title", "Sukses nonaktifkan");
		return "success-nonactive";
	}
	
	@RequestMapping("/penduduk/cari")
	public String search(Model model, @RequestParam(value="kt", required=false) String kt, 
									  @RequestParam(value="kc", required=false) String kc, 
									  @RequestParam(value="kl", required=false) String kl)
	{
		String kt2="";
		String kc2="";
		String kl2="";
		
		if(kt==null&&kc==null&&kl==null) {
			KotaModel kota = new KotaModel();
			List<KotaModel> kotas = kotaDAO.selectAllKota();	
			model.addAttribute("kota", kota);
			model.addAttribute("kotas", kotas);
			return "cari";
		} else if(kt!=null && kc==null && kl==null) {
			kt2=kt;
			KotaModel kota = new KotaModel();
			List<KotaModel> kotas = kotaDAO.selectAllKota();	
			model.addAttribute("kota", kota);
			model.addAttribute("kotas", kotas);
			
			KecamatanModel kecamatan = new KecamatanModel();
			List<KecamatanModel> kecamatans = kecamatanDAO.selectAllKecamatan();
			model.addAttribute("kecamatan", kecamatan);
			model.addAttribute("kecamatans", kecamatans);
			return "cari-kecamatan";
		} else if(kt!=null&&kc!=null&&kl==null) {
			kt2=kt;
			kc2=kc;
			KotaModel kota = new KotaModel();
			List<KotaModel> kotas = kotaDAO.selectAllKota();	
			model.addAttribute("kota", kota);
			model.addAttribute("kotas", kotas);
			
			KecamatanModel kecamatan = new KecamatanModel();
			List<KecamatanModel> kecamatans = kecamatanDAO.selectAllKecamatan();
			model.addAttribute("kecamatan", kecamatan);
			model.addAttribute("kecamatans", kecamatans);
			
			KelurahanModel kelurahan = new KelurahanModel();
			List<KelurahanModel> kelurahans = kelurahanDAO.selectAllKelurahan();
			model.addAttribute("kelurahan", kelurahan);
			model.addAttribute("kelurahans", kelurahans);
			return "cari-kelurahan";
		} else {
			kt2=kt;
			kc2=kc;
			kl2=kl;
			int kl2fix = Integer.parseInt(kl2);
			KotaModel kota = new KotaModel();
			List<KotaModel> kotas = kotaDAO.selectAllKota();	
			model.addAttribute("kota", kota);
			model.addAttribute("kotas", kotas);
			
			KecamatanModel kecamatan = new KecamatanModel();
			
			List<KecamatanModel> kecamatans = kecamatanDAO.selectAllKecamatan();
			model.addAttribute("kecamatan", kecamatan);
			model.addAttribute("kecamatans", kecamatans);
			KelurahanModel kelurahan = new KelurahanModel();
			
			List<KelurahanModel> kelurahans = kelurahanDAO.selectAllKelurahan();
			model.addAttribute("kelurahan", kelurahan);
			model.addAttribute("kelurahans", kelurahans);
			PendudukModel penduduk = new PendudukModel();
			List<PendudukModel> penduduks = keluargaDAO.selectAllPenduduk(kl2fix);
			model.addAttribute("penduduk", penduduk);
			model.addAttribute("penduduks", penduduks);
			return "cari-penduduk";
		}
	}
}
