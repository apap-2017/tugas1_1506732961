package com.example.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1.dao.KelurahanMapper;
import com.example.tugas1.model.KelurahanModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KelurahanServiceDatabase implements KelurahanService {
	
	@Autowired
	private KelurahanMapper kelurahanMapper;
	
	@Override
	public KelurahanModel selectKelurahanbyId(int id)
	{
		return kelurahanMapper.selectKelurahanbyId(id);
	}
	
	@Override
	public List<KelurahanModel> selectAllKelurahan()
	{
		return kelurahanMapper.selectAllKelurahan();
	}
	
	
}
