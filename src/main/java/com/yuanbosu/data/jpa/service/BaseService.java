package com.yuanbosu.data.jpa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;

import com.yuanbosu.common.domain.Result;
import com.yuanbosu.data.common.dto.Form;
import com.yuanbosu.data.jpa.domain.dto.Search;
import com.yuanbosu.data.jpa.domain.model.BaseModel;

public interface BaseService<Model extends BaseModel>
{
	@SuppressWarnings("unchecked")
	public default Class<Model> createBean()
    throws InstantiationException, IllegalAccessException
  {
    return ((Class<Model>)((java.lang.reflect.ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
  }

  public abstract Page<Model> pageList(Search<Model> paramSearch);

  public abstract List<Model> list(Search<Model> paramSearch);

  public abstract Model detail(Search<Model> paramSearch);

  public abstract Model detail(String paramString);

  public abstract Model loadWithCopy(Form<Model> paramForm, Model paramModel);

  public abstract Model saveForm(Form<Model> paramForm, Model paramModel);

  public abstract Model saveForm(Form<Model> paramForm)
    throws Exception;

  public abstract List<Model> saveForm(List<Form<Model>> paramList)
    throws Exception;

  public abstract List<Model> saveForm(List<Form<Model>> paramList, List<Model> paramList1)
    throws Exception;

  public abstract Result<Model> saveForm(Form<Model> paramForm, BindingResult paramBindingResult, String paramString, int paramInt1, int paramInt2)
    throws Exception;

  public abstract Result<String> saveFormFetchPk(Form<Model> paramForm, BindingResult paramBindingResult, String paramString, int paramInt1, int paramInt2)
    throws Exception;

  public abstract Model save(Model paramModel);

  public abstract Model saveWithoutFlush(Model paramModel);

  public abstract void flush();

  public abstract List<Model> save(List<Model> paramList);

  public abstract List<Model> saveWithoutFlush(List<Model> paramList);

  public abstract void delete(String paramString);

  public abstract void delete(Model paramModel);

  public abstract void delete(List<Model> paramList);

  public abstract Integer fakeDeleteById(String paramString);

  public abstract Integer fakeDeleteById(String[] paramArrayOfString);

  public abstract Long count(Search<Model> paramSearch);

  public abstract boolean exist(Search<Model> paramSearch);

  public abstract boolean exist(String paramString, Search<Model> paramSearch);

  public abstract Model fakeDelete(String paramString);

  public abstract Model fakeDelete(Model paramModel);

  public abstract List<Model> fakeDelete(List<Model> paramList);
}
