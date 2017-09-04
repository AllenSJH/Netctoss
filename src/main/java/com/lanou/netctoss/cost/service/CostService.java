package com.lanou.netctoss.cost.service;

import com.github.pagehelper.PageInfo;
import com.lanou.netctoss.cost.bean.Cost;

import java.util.List;

/**
 * Created by dllo on 17/8/30.
 */
public interface CostService {
    List<Cost> findCost();
    PageInfo<Cost> querypage(Integer pageNum, Integer pageSize);

    void addCost(Cost cost);
    Integer delete(Integer id);
    Cost findById(int id);
    void updateCost(Cost cost);

    void enable(Cost cost);
}
