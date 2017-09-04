package com.lanou.netctoss.cost.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.netctoss.cost.bean.Cost;
import com.lanou.netctoss.cost.mapper.CostMapper;
import com.lanou.netctoss.cost.service.CostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.List;

/**
 * Created by dllo on 17/8/30.
 */
@Service
public class CostServiceImpl implements CostService {

    @Resource
    private CostMapper costMapper;


    public List<Cost> findCost() {
        return costMapper.findCost();
    }

    public PageInfo<Cost> querypage(Integer pageNum, Integer pageSize) {
        pageNum = pageNum ==null ? 1: pageNum;
        pageSize = pageSize == null ? 3 :pageSize;

        PageHelper.startPage(pageNum,pageSize);
        List<Cost> list = costMapper.findCost();
        PageInfo<Cost> pageInfo = new PageInfo<Cost>(list);

        return pageInfo;
    }

    public void addCost(Cost cost) {
        costMapper.addCost(cost);
    }
    public Integer delete(Integer id) {

        costMapper.delete(id);

        return id;
    }
    public Cost findById(int id) {
        return costMapper.findById(id);
    }
    public void updateCost(Cost cost) {
        costMapper.update(cost);
    }

    public void enable(Cost cost) {
        costMapper.enable(cost);
    }


}
