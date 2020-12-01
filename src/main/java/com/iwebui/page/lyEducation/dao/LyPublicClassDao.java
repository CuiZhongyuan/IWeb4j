package com.iwebui.page.lyEducation.dao;

import com.iwebui.entity.PageMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LyPublicClassDao extends JpaRepository<PageMsg,Long> {

}
