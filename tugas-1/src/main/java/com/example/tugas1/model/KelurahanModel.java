package com.example.tugas1.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KelurahanModel {
	
	private int id;
	private String kode_kelurahan;
	private int id_kecamatan;
	private String nama_kelurahan;
	private String kode_pos;
	
	private List<KelurahanModel> kelurahans;  
}
