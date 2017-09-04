package com.lanou.netctoss.cost.mapper;

import com.lanou.netctoss.cost.bean.Cost;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dllo on 17/8/30.
 */
@Repository
public interface CostMapper {


    List<Cost> findCost();

    void addCost(Cost cost);
    void delete(@Param("did") int id);
    Cost findById(@Param("fid") int id);
    void update(Cost cost);

    void enable(Cost cost);
}
