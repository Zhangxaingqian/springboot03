package cn.myweb01.money01.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ICategoryService {
    //获取一级分类
    String queryFirstJobCategoryList() throws JsonProcessingException;
    //根据一级分类获取二级分类
    String querySecondJobCategoryListById(Integer firstId) throws JsonProcessingException;
}
