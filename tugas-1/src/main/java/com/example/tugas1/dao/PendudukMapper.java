package com.example.tugas1.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.tugas1.model.PendudukModel;

@Mapper
public interface PendudukMapper {
	
	@Select("select * from penduduk where nik = #{nik}")
	PendudukModel selectPenduduk(@Param("nik") String nik);
	
	@Insert("insert into penduduk(nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, "
			+ " id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, "
			+ " is_wafat) values (#{nik}, #{nama}, #{tempat_lahir}, "
			+ " #{tanggal_lahir}, #{jenis_kelamin}, #{is_wni}, #{id_keluarga}, #{agama}, #{pekerjaan}, "
			+ " #{status_perkawinan}, #{status_dalam_keluarga}, #{golongan_darah}, #{is_wafat})")
	void addPenduduk(PendudukModel penduduk);
	
	@Select("select * from penduduk where nik like concat(#{nik},'%') order by id desc limit 1")
	PendudukModel cekPenduduk(@Param("nik") String nik);
	
	@Update("update penduduk set nik = #{nik}, nama = #{nama}, tempat_lahir = #{tempat_lahir}, tanggal_lahir = #{tanggal_lahir}, "
			+ " jenis_kelamin = #{jenis_kelamin}, is_wni = #{is_wni}, id_keluarga = #{id_keluarga}, agama = #{agama}, "
			+ " pekerjaan = #{pekerjaan}, status_perkawinan = #{status_perkawinan}, status_dalam_keluarga = #{status_dalam_keluarga}, "
			+ " golongan_darah = #{golongan_darah}, is_wafat = #{is_wafat} where id = #{id}")
	void updatePenduduk(PendudukModel penduduk);
	
	@Update("update penduduk set nik = #{nik}, nama = #{nama}, tempat_lahir = #{tempat_lahir}, tanggal_lahir = #{tanggal_lahir}, "
			+ " jenis_kelamin = #{jenis_kelamin}, is_wni = #{is_wni}, id_keluarga = #{id_keluarga}, agama = #{agama}, "
			+ " pekerjaan = #{pekerjaan}, status_perkawinan = #{status_perkawinan}, status_dalam_keluarga = #{status_dalam_keluarga}, "
			+ " golongan_darah = #{golongan_darah}, is_wafat = #{is_wafat} where id = #{id}")
	void switchMati(PendudukModel penduduk);
}
