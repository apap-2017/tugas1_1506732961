package com.example.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1.dao.KecamatanMapper;
import com.example.tugas1.model.KecamatanModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KecamatanServiceDatabase implements KecamatanService{
	
	@Autowired
	private KecamatanMapper kecamatanMapper;
	
	@Override
	public KecamatanModel selectKecamatanbyId(int id)
	{
		return kecamatanMapper.selectKecamatanbyId(id);
	}
	
	@Override
	public List<KecamatanModel> selectAllKecamatan()
	{
		return kecamatanMapper.selectAllKecamatan();
	}
}
