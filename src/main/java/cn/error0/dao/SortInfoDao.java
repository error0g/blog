package cn.error0.dao;


import cn.error0.entity.SortInfo;

public interface SortInfoDao {
    int insert(SortInfo record);

    SortInfo selectOne(Long id);
}
