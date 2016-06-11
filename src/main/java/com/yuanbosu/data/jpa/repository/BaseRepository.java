package com.yuanbosu.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, String>, JpaSpecificationExecutor<T> {
	@Transactional
	@Modifying
	@Query("update #{#entityName} u set u.deleted = 1 where u.id = ?1")
	public abstract Integer fakeDeleteById(String paramString);

	@Transactional
	@Modifying
	@Query("update #{#entityName} u set u.deleted = 1 where u.id in (?1)")
	public abstract Integer fakeDeleteByIds(List<String> paramList);
}