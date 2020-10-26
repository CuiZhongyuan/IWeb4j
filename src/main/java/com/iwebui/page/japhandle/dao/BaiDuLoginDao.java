package com.iwebui.page.japhandle.dao;

import com.iwebui.entity.Logincase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface BaiDuLoginDao extends JpaRepository<Logincase, Long> {
    @Transactional
    @Modifying
    @Query( "UPDATE Logincase SET urlPath = :urlPath WHERE id = :id" )
    void updateLogincase(@Param( "urlPath" ) String urlPath, @Param( "id" ) Long id);

    @Transactional
    @Modifying
    @Query( "UPDATE Logincase SET actual= :actual WHERE id = :id" )
    void updateActual(@Param("actual") String actual,@Param("id") Long id);
}
