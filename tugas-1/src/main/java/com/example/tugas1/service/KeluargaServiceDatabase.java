package com.example.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1.dao.KeluargaMapper;
import com.example.tugas1.model.KeluargaModel;
import com.example.tugas1.model.KelurahanModel;
import com.example.tugas1.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KeluargaServiceDatabase implements KeluargaService{
	
	@Autowired
	private KeluargaMapper keluargaMapper;
	
	@Override
	public KeluargaModel selectKeluargabyId(int id)
	{
		log.info("select keluarga by id {}", id);
		return keluargaMapper.selectKeluargabyId(id);
	}
	
	@Override
	public KeluargaModel selectKeluarga(String nomor_kk)
	{
		log.info("select keluarga by nomor_kk {}", nomor_kk);
		return keluargaMapper.selectKeluarga(nomor_kk);
	}
	
	@Override
	public List<KelurahanModel> selectAllKelurahan()
	{
		return keluargaMapper.selectAllKelurahan();
	}
	
	@Override
	public KeluargaModel cekKeluarga(String nomor_kk)
	{
		return keluargaMapper.cekKeluarga(nomor_kk);
	}
	
	@Override
	public void addKeluarga(KeluargaModel keluarga)
	{
		keluargaMapper.addKeluarga(keluarga);
	}
	
	@Override
	public KeluargaModel selectKeluarga2(String nomor_kk)
	{
		return keluargaMapper.selectKeluarga2(nomor_kk);
	}
	
	@Override
	public void updateKeluarga(KeluargaModel keluarga)
	{
		keluargaMapper.updateKeluarga(keluarga);
	}
	
	@Override
	public List<PendudukModel> selectAllPenduduk(int id_keluarga)
	{
		return keluargaMapper.selectAllPenduduk(id_keluarga);
	}
}
