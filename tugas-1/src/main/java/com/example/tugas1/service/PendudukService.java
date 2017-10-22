package com.example.tugas1.service;

import com.example.tugas1.model.PendudukModel;

public interface PendudukService {
	PendudukModel selectPenduduk(String nik);
	
	void addPenduduk(PendudukModel penduduk);
	
	PendudukModel cekPenduduk(String nik);
	
	void updatePenduduk(PendudukModel penduduk);
	
	void switchMati(PendudukModel penduduk);
}
