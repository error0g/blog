package cn.error0.dao;


import cn.error0.entity.SortInfo;

import java.util.List;

public interface SortInfoDao {

    int insert(SortInfo record);

    void delete(Long id);

    SortInfo selectOne(Long id);

    void updateSortInfo(SortInfo sortInfo);

    int selectSortNum();

    int selectArticleNum();

    List<SortInfo> selectAllSortInfo();
}
