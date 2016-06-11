package com.yuanbosu.data.jpa.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.yuanbosu.data.common.dto.Form;
import com.yuanbosu.data.jpa.domain.dto.TreeNodeForm;
import com.yuanbosu.data.jpa.domain.model.TreeModel;
import com.yuanbosu.data.jpa.domain.model.TreeNode;
import com.yuanbosu.data.jpa.repository.TreeRepository;
import com.yuanbosu.data.jpa.service.TreeService;

public abstract class TreeServiceImpl<Model extends TreeModel> extends BaseServiceImpl<Model>
		implements TreeService<Model> {
	private Logger log;
	public static final String path_split = "/";
	public static final String root_id = "0";

	public TreeServiceImpl() {
		this.log = LoggerFactory.getLogger(TreeServiceImpl.class);
	}

	public Model loadWithCopy(Form<Model> form, Model entity) {
		String modelName = entity.getClass().getName();
		TreeNodeForm<Model> treeform = (TreeNodeForm<Model>) form;

		if (treeform.getId() == null) {
			if (treeform.getParentId() == null)
				treeform.setParentId("0");
			if (treeform.getLeaf() == null)
				treeform.setLeaf(Boolean.valueOf(true));

			form.toModel(entity, false);
		} else {
			entity = (Model) detail(treeform.getId());
			Assert.notNull(entity, "Can not load Tree Model [" + modelName + "] by id [" + treeform.getId() + "]");

			this.log.debug("Load Tree Model [{}] entity [{}] ", modelName, entity.toString());

			form.toModel(entity, true);
		}

		return entity;
	}

	public Model saveWithCopy(Form<Model> form, Model entity) {
		String modelName = entity.getClass().getName();
		String msg = "Save Tree Model [" + modelName + "] ";

		TreeNodeForm<Model> treeform = (TreeNodeForm<Model>) form;

		entity = loadWithCopy(form, entity);
		String currentParentId = null;

		TreeNode node = new TreeNode();

		if (form.getId() == null) {
			String idpath = "0";

			if (!(StringUtils.trimToEmpty(treeform.getParentId()).equals("0"))) {
				TreeModel parent = (TreeModel) detail(treeform.getParentId());
				Assert.notNull(parent, "Canot find Tree Model [" + modelName + "] parent model by id ["
						+ treeform.getParentId() + "]");

				this.log.debug(msg + "by parent [{}]", parent.toString());

				idpath = StringUtils.trimToEmpty(parent.getNode().getIdPath()) + "/" + parent.getId();

				updateLeaf(false, parent.getId());
			}

			node.setIdPath(idpath);
			node.setLeaf(treeform.getLeaf());
			node.setSubNodesCount(Integer.valueOf(0));
			node.setParentId(treeform.getParentId());
		} else {
			node = entity.getNode();

			String currentId = form.getId();
			String currentIdPath = node.getIdPath();
			currentParentId = node.getParentId();

			String newParentId = treeform.getParentId();

			String msgWithId = msg + " id [" + currentId + "] ";
			String msgUpdateParent = msgWithId + "new parent id [" + newParentId + "] ";

			this.log.debug("update Tree model [{}] id [{}] idpath [{}] parentid [{}] new parentid [{}]!",
					new Object[] { modelName, currentId, currentIdPath, currentParentId, newParentId });

			if (newParentId != null) {
				Assert.isTrue((!(currentParentId.equals(newParentId))) ? true : false,
						msgUpdateParent + "can not equal current parent id [" + currentParentId + "]!");

				Assert.isTrue((!(currentId.equals(newParentId))) ? true : false, msgUpdateParent + "can not be same!");

				TreeModel newParent = (TreeModel) detail(newParentId);
				Assert.notNull(newParent, msgUpdateParent + "not exist!");
				String newIdPath = newParent.getNode().getIdPath() + "/" + newParent.getId();

				List<String> subNodesId = findSubNodesId(currentIdPath + "/" + currentId);
				this.log.debug(msgWithId + "has [{}] sub nodes!", Integer.valueOf(subNodesId.size()));

				if (!(subNodesId.isEmpty())) {
					Assert.isTrue((!(subNodesId.contains(newParentId))) ? true : false,
							msgUpdateParent + "cant not in child nodes!");

					this.log.debug(msgWithId + "update current idPath [{}] with new idPath [{}]!",
							currentIdPath + "/" + currentId, newIdPath + "/" + currentId);
					Integer updateResult = updateIdPath(newIdPath + "/" + currentId, currentIdPath + "/" + currentId);

					this.log.debug(msgWithId + "update [{}] sub nodes success!", updateResult);
				}

				node.setIdPath(newIdPath);
				node.setParentId(newParentId);

				updateLeaf(false, newParent.getId());

				if (findSubNodesId(currentIdPath).size() < 2)
					updateLeaf(true, currentParentId);
				else {
					updateLeaf(false, currentParentId);
				}

			}

		}

		if (treeform.getName() != null)
			node.setName(treeform.getName());

		entity.setNode(node);

		this.log.debug(msg + "with tree node [{}]", node.toString());
		entity = (Model) save(entity);

		if ((treeform.isCountSub()) && (treeform.getParentId() != null)) {
			updateSubNodesNum(treeform.getParentId(), false);
			this.log.debug(msg + "count sub nodes by new parent id [{}]", treeform.getParentId());

			if (currentParentId != null) {
				updateSubNodesNum(currentParentId, false);
				this.log.debug(msg + "with id [{}] count sub nodes by old parent id [{}]", form.getId(),
						currentParentId);
			}

		}

		return entity;
	}

	public List<String> getNodePathName(String idPath) {
		Splitter splitter = Splitter.on("/").trimResults();
		List<String> id = Lists.newArrayList(splitter.split(idPath));

		List<String> result = getTreeRepository().findNodePathName(id, false);
		return ((result == null) ? new ArrayList<>() : result);
	}

	public List<String> findSubNodesId(String idPath) {
		List<String> result = getTreeRepository().findSubNodesId(idPath, false);
		return ((result == null) ? new ArrayList<>() : result);
	}

	public List<Model> findSubNodes(String idPath) {
		return getTreeRepository().findSubNodes(idPath, false);
	}

	public Integer updateLeaf(boolean leaf, String id) {
		return getTreeRepository().updateLeaf(leaf, id);
	}

	public Integer updateSubNodesNum(String id, boolean deleted) {
		return getTreeRepository().updateSubNodesNum(id, deleted);
	}

public Integer resetIdPath(int treeLevel) {
//  List<Object[]> entities = getTreeRepository().findAllTreeNode();
//
//  for (Iterator<Object[]> localIterator = entities.iterator(); localIterator.hasNext(); ) { Object[] entity = (Object[])localIterator.next();
//
//    String id = (String)entity[0];
//    String idpath = ((TreeNode)entity[1]).getIdPath();
//
//    this.log.debug("Reset old idPath [{}] with id [{}] -------------------------", idpath, id);
//
//    List<Object> newIdPath = Lists.newLinkedList();
//    String parentId = id;
//    int count = treeLevel;
//
//    while ((!(parentId.equals("0"))) && (count >= 0)) {
//      try {
//        String id2 = parentId;
//        parentId = getTreeRepository().findParentId(id2);
//
//        if (parentId == null) {
//          this.log.error("Reset Fail! Can not find parentId with id [{}]", id2);
//          newIdPath.clear();
//          break label189;
//        }
//
//        newIdPath.add(parentId);
//      } catch (Exception e) {
//        this.log.error("Reset Fail! Can not find parentId with id [{}]", parentId);
//        newIdPath.clear();
//        break label189;
//      }
//
//      --count;
//    }
//
//    if (!(newIdPath.isEmpty())) {
//      label189: String newIdPathString = Joiner.on("/").skipNulls().join(Lists.reverse(newIdPath));
//      this.log.debug("Reset new idPath [{}] with id [{}]", newIdPathString, id);
//      getTreeRepository().updateIdpath(id, newIdPathString);
//    }
//  }

  return Integer.valueOf(0);
}

	private TreeRepository<Model> getTreeRepository() {
		return ((TreeRepository<Model>) getRepo());
	}

	private Integer updateIdPath(String newIdPath, String currentIdPath) {
		return getTreeRepository().updateIdpath(newIdPath, currentIdPath, Integer.valueOf(currentIdPath.length()));
	}
}
