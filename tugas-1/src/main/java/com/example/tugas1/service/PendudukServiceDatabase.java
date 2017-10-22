package com.example.tugas1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1.dao.PendudukMapper;
import com.example.tugas1.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PendudukServiceDatabase implements PendudukService {
	
	@Autowired
	private PendudukMapper pendudukMapper;
	
	@Override
	public PendudukModel selectPenduduk(String nik)
	{
		log.info("select penduduk with nik {}", nik);
		return pendudukMapper.selectPenduduk(nik);
	}
	
	@Override
	public void addPenduduk(PendudukModel penduduk)
	{
		pendudukMapper.addPenduduk(penduduk);
	}
	
	@Override
	public PendudukModel cekPenduduk(String nik)
	{
		return pendudukMapper.cekPenduduk(nik);
	}
	
	@Override
	public void updatePenduduk(PendudukModel penduduk)
	{
		log.info("masuk sini, update penduduk {}", penduduk);
		pendudukMapper.updatePenduduk(penduduk);
	}
	
	@Override
	public void switchMati(PendudukModel penduduk)
	{
		pendudukMapper.switchMati(penduduk);
	}
}
