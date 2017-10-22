package com.example.tugas1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.tugas1.model.KecamatanModel;
import com.example.tugas1.model.KotaModel;

@Mapper
public interface KecamatanMapper {


	@Select("select * from kecamatan where id = #{id}")
	KecamatanModel selectKecamatanbyId(@Param("id") int id);
	
	@Select("select * from kecamatan")
	List<KecamatanModel> selectAllKecamatan();
}
