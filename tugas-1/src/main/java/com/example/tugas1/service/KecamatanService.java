package com.example.tugas1.service;

import java.util.List;

import com.example.tugas1.model.KecamatanModel;

public interface KecamatanService {
	
	KecamatanModel selectKecamatanbyId(int id);
	
	List<KecamatanModel> selectAllKecamatan();
}
