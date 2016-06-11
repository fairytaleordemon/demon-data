package com.yuanbosu.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
public interface TreeRepository<T> extends BaseRepository<T> {
	@Query("select t.id from #{#entityName} t where t.deleted = ?2 and t.node.idPath like ?1% order by t.node.idPath asc")
	public abstract List<String> findSubNodesId(String paramString, boolean paramBoolean);

	@Query("select t from #{#entityName} t where t.deleted = ?2 and t.node.idPath like ?1% order by t.node.idPath asc")
	public abstract List<T> findSubNodes(String paramString, boolean paramBoolean);

	@Query("select t.node.name from #{#entityName} t where t.deleted = ?2 and t.id in (?1) order by t.node.idPath asc")
	public abstract List<String> findNodePathName(List<String> paramList, boolean paramBoolean);

	@Transactional
	@Modifying
	@Query("delete #{#entityName} t where t.node.idPath like ?1%")
	public abstract Integer deleteSubNodesByIdPath(String paramString);

	@Transactional
	@Modifying
	@Query("update #{#entityName} t set t.deleted = 1 where t.node.idPath like ?1%")
	public abstract Integer fakeDeleteSubNodesByIdPath(String paramString);

	@Transactional
	@Modifying
	@Query("update #{#entityName} t set t.node.leaf = ?1 where t.id = ?2")
	public abstract Integer updateLeaf(boolean paramBoolean, String paramString);

	@Transactional
	@Modifying
	@Query("update #{#entityName} t set t.node.idPath = CONCAT(?1,SUBSTRING(t.node.idPath,?3+1,LENGTH(t.node.idPath)-?3)) where t.node.idPath like ?2%")
	public abstract Integer updateIdpath(String paramString1, String paramString2, Integer paramInteger);

	@Query("select CONCAT(?1,SUBSTRING(t.node.idPath,?3+1,LENGTH(t.node.idPath)-?3)) from #{#entityName} t where t.node.idPath like ?2%")
	public abstract List<String> findNewIdpath(String paramString1, String paramString2, Integer paramInteger);

	@Query("select COUNT(t.id) from #{#entityName} t where t.deleted=?2 and t.node.idPath like ?1%")
	public abstract Long getSubNodesNum(String paramString, boolean paramBoolean);

	@Query("select COUNT(t.id) from #{#entityName} t where t.deleted=?2 and t.node.parentId = ?1")
	public abstract Long getSubNodeNum(String paramString, boolean paramBoolean);

	@Transactional
	@Modifying
	@Query(value = "update #{#entityName} t set t.sub_nodes_count = (select t2.num from (select COUNT(t2.id) as num from #{#entityName} t2 where t2.deleted = ?2 and t2.parent_id = ?1) t2) where t.id = ?1", nativeQuery = true)
	public abstract Integer updateSubNodesNum(String paramString, boolean paramBoolean);

	@Query("select t.node.parentId from #{#entityName} t where t.id = ?1")
	public abstract String findParentId(String paramString);

	@Query("select t.id,t.node from #{#entityName} t")
	public abstract List<Object[]> findAllTreeNode();

	@Transactional
	@Modifying
	@Query("update #{#entityName} t set t.node.idPath = ?2 where t.id = ?1")
	public abstract Integer updateIdpath(String paramString1, String paramString2);
}
