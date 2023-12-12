package com.example.mysql.mapper;

import com.example.mysql.entity.Ebook_snapshot;
import com.example.mysql.entity.Ebook_snapshotExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Ebook_snapshotMapper {
    long countByExample(Ebook_snapshotExample example);

    int deleteByExample(Ebook_snapshotExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Ebook_snapshot record);

    int insertSelective(Ebook_snapshot record);

    List<Ebook_snapshot> selectByExample(Ebook_snapshotExample example);

    Ebook_snapshot selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Ebook_snapshot record, @Param("example") Ebook_snapshotExample example);

    int updateByExample(@Param("record") Ebook_snapshot record, @Param("example") Ebook_snapshotExample example);

    int updateByPrimaryKeySelective(Ebook_snapshot record);

    int updateByPrimaryKey(Ebook_snapshot record);
}