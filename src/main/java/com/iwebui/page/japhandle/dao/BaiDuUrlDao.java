package com.iwebui.page.japhandle.dao;

import com.iwebui.entity.PageMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaiDuUrlDao extends JpaRepository<PageMsg, Long> {
}
