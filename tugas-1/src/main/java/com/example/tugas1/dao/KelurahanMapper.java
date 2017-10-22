package com.example.tugas1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.tugas1.model.KelurahanModel;
import com.example.tugas1.model.KotaModel;

@Mapper
public interface KelurahanMapper {

	@Select("select * from kelurahan where id = #{id}")
	KelurahanModel selectKelurahanbyId(@Param("id") int id);
	
	@Select("select * from kelurahan")
	List<KelurahanModel> selectAllKelurahan();
}
