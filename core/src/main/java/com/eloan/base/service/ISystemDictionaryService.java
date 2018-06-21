package com.eloan.base.service;

import com.eloan.base.domain.SystemDictionary;
import com.eloan.base.domain.SystemDictionaryItem;
import com.eloan.base.query.PageResult;
import com.eloan.base.query.SystemDictionaryQueryObject;

import java.util.List;

public interface ISystemDictionaryService {
    PageResult queryDic(SystemDictionaryQueryObject qo);

    void saveOrUpdate(SystemDictionary sd);

    PageResult queryDicItem(SystemDictionaryQueryObject qo);

    void saveOrUpdateItem(SystemDictionaryItem item);

    List<SystemDictionary> listDics();

    List<SystemDictionaryItem> queryBySn(String sn);
}
