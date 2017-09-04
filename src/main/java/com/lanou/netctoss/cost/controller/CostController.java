package com.lanou.netctoss.cost.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.netctoss.cost.bean.Cost;
import com.lanou.netctoss.cost.service.CostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 17/8/30.
 */
@Controller
public class CostController {

    @Resource
    private CostService costService;

    //主页
    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }
    //跳转资费列表
    @RequestMapping(value = "/fee/fee_list")
    public String feelist(){
        return "/fee/fee_list";
    }


//    @RequestMapping(value = "/findCost")
//    @ResponseBody
//    public List<Cost> findCost(){
//       List<Cost> list = costService.findCost();
//        System.out.println(list);
//        return list;
//    }

    //分页显示数据
    @RequestMapping(value = "/pagetest")
    @ResponseBody
    public PageInfo<Cost> pagetest(@RequestParam("pageNum")Integer pageNum,
                                   @RequestParam("pageSize")Integer pageSize,
                                   HttpServletRequest request){

        PageInfo<Cost> page = costService.querypage(pageNum, pageSize);
        request.setAttribute("url","/pagetest");
        return page;
    }
    //跳转添加资费界面
    @RequestMapping(value = "/fee/fee_add")
    public String addCostUI(){
        return "/fee/fee_add";
    }

    //添加资费
    @RequestMapping(value = "/addCost")
    public String addCost(Cost cost){
        cost.setStatus("1");
        cost.setCreatime(new Timestamp(System.currentTimeMillis()));
        costService.addCost(cost);
        return "redirect:/fee/fee_list";
    }

    //把id存在session中
    @RequestMapping(value = "fee/fee_modi.html")
    public String CostId(@RequestParam(value = "cost_id") Integer id, HttpSession session) {
        Cost cost = costService.findById(id);
        session.setAttribute("cost", cost);
        return "fee/fee_modi";
    }

    //删除
    @RequestMapping(value = "/deleteFee")
    @ResponseBody
    public Map<String,Integer> delete(@RequestParam(value = "detail_id") Integer id) {
        Integer did = costService.delete(id);
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("did",did);
        return  map ;
    }

     //取出id修改
    @RequestMapping(value = "fee/modi")
    public String Update(Cost cost,HttpSession session) {
        Cost cost1 = (Cost) session.getAttribute("cost");
        cost.setCost_id(cost1.getCost_id());
        costService.updateCost(cost);
        return "redirect:/fee/fee_list";

    }
    //修改界面的回显
    @RequestMapping(value = "/returncost")
    @ResponseBody
    public Map<String,Object> returncost(HttpSession session ){
        Cost cost = (Cost) session.getAttribute("cost");
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("returncost",cost);
        return map;
    }

    @RequestMapping(value = "/enable")
    @ResponseBody
    public Cost enable(Cost cost, HttpSession session){
        Cost cost1 = (Cost) session.getAttribute("cost");
        cost.setCost_id(cost1.getCost_id());
        costService.enable(cost);
        cost.setStartime(new Timestamp(System.currentTimeMillis()));
        return cost;

    }







}
