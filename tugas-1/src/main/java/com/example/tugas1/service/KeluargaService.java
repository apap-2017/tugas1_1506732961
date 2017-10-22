package com.example.tugas1.service;

import java.util.List;

import com.example.tugas1.model.KeluargaModel;
import com.example.tugas1.model.KelurahanModel;
import com.example.tugas1.model.PendudukModel;

public interface KeluargaService {
	KeluargaModel selectKeluargabyId(int id);
	
	KeluargaModel selectKeluarga(String nomor_kk);
	
	List<KelurahanModel> selectAllKelurahan();
	
	KeluargaModel cekKeluarga(String nomor_kk);
	
	void addKeluarga(KeluargaModel keluarga);
	
	KeluargaModel selectKeluarga2(String nomor_kk);
	
	void updateKeluarga(KeluargaModel keluarga);
	
	List<PendudukModel> selectAllPenduduk(int id_kelurahan);
}
