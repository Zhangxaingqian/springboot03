package cn.myweb01.money01.service;

import cn.myweb01.money01.pojo.PageBean;

public interface IElasticsearchService {
    //分页查询工作表1的信息
    PageBean queryJobByPage(Integer curPage, String jname);
}
