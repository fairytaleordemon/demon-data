package com.yuanbosu.data.jpa.service;

import java.util.List;

import com.yuanbosu.data.common.dto.Form;
import com.yuanbosu.data.jpa.domain.model.TreeModel;

public abstract interface TreeService<Model extends TreeModel> extends BaseService<Model>
{
  public abstract Model loadWithCopy(Form<Model> paramForm, Model paramModel);

  public abstract Model saveWithCopy(Form<Model> paramForm, Model paramModel);

  public abstract List<String> getNodePathName(String paramString);

  public abstract List<String> findSubNodesId(String paramString);

  public abstract List<Model> findSubNodes(String paramString);

  public abstract Integer updateLeaf(boolean paramBoolean, String paramString);

  public abstract Integer updateSubNodesNum(String paramString, boolean paramBoolean);

  public abstract Integer resetIdPath(int paramInt);
}
