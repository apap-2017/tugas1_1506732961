package com.example.tugas1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.tugas1.model.KeluargaModel;
import com.example.tugas1.model.KelurahanModel;
import com.example.tugas1.model.PendudukModel;

@Mapper
public interface KeluargaMapper {
	
	@Select("select * from keluarga where id = #{id}")
	KeluargaModel selectKeluargabyId(@Param("id") int id);
	
	@Select("select * "
			+ " from keluarga where nomor_kk = #{nomor_kk}") 
	@Results(value= {
			@Result(property="id", column="id"),
			@Result(property="nomor_kk", column="nomor_kk"),
			@Result(property="alamat", column="alamat"),
			@Result(property="rt", column="rt"),
			@Result(property="rw", column="rw"),
			@Result(property="id_kelurahan", column="id_kelurahan"),
			@Result(property="is_tidak_berlaku", column="is_tidak_berlaku"),
			@Result(property="penduduks", column="id",
					javaType = List.class,
					many = @Many(select="selectPenduduks"))
			})
	KeluargaModel selectKeluarga(@Param("nomor_kk") String nomor_kk);
	
	@Select("select * "
			+ " from penduduk p join keluarga k "
			+ " on p.id_keluarga = k.id where p.id_keluarga = #{id_keluarga}")
	List<PendudukModel> selectPenduduks(@Param("id_keluarga") int id_keluarga);
	
	@Select("select * from keluarga where nomor_kk like concat(#{nomor_kk},'%') order by id desc limit 1")
	KeluargaModel cekKeluarga(@Param("nomor_kk") String nomor_kk);
	
	@Insert("insert into keluarga(nomor_kk, alamat, rt, rw, id_kelurahan, is_tidak_berlaku) "
			+ " values(#{nomor_kk}, #{alamat}, #{rt}, #{rw}, #{id_kelurahan}, 0)")
	void addKeluarga(KeluargaModel keluarga);
	
	@Select("select * from kelurahan")
	List<KelurahanModel> selectAllKelurahan();
	
	@Select("select * from keluarga where nomor_kk = #{nomor_kk}")
	KeluargaModel selectKeluarga2(@Param("nomor_kk") String nomor_kk);
	
	@Update("update keluarga set nomor_kk= #{nomor_kk}, alamat = #{alamat}, rt = #{rt}, rw = #{rw}, id_kelurahan = #{id_kelurahan}, "
			+ " is_tidak_berlaku = 0 where id= #{id}")
	void updateKeluarga(KeluargaModel keluarga);
	
	@Select("select * from penduduk p, keluarga k where k.id = p.id_keluarga and k.id_kelurahan = #{id_kelurahan}")
	List<PendudukModel> selectAllPenduduk(int id_kelurahan);
	
}