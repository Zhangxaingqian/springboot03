package cn.myweb01.money01.controller;

import cn.myweb01.money01.pojo.ResultInfo;
import cn.myweb01.money01.service.IProvinceCityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("provinceCity")
public class ProvinceCityController {
    private final static Logger log= LoggerFactory.getLogger(ProvinceCityController.class);

    @Autowired
    private IProvinceCityService provinceCity;
    /*获取所有的省份*/
    @RequestMapping("province")
    @ResponseBody
    public String getAllProvince() {
        log.info("获取所有的省份开始");
        String jsonData = null;
        try {
            jsonData=provinceCity.getAllProvince();
        } catch (Exception e) {
            log.info("省份查询异常"+e);
            ResultInfo resultInfo = new ResultInfo(false, null, "服务器忙，请稍后再试！");
            //将resultInfo转换为String
            try {
                //将java对象转化为json对象
                jsonData = new ObjectMapper().writeValueAsString(resultInfo);
            } catch (JsonProcessingException e1) {
                log.info("对象转化为json对象异常"+e1);
            }

        }
        return jsonData;
    }
    /*根据省份获取城市*/
    @RequestMapping("city")
    @ResponseBody
    public String getCityByProvince(String provinceCode) {
        log.info("根据省份获取城市开始,省份的名称:"+provinceCode);
        String jsonData = null;

        try {
            jsonData=provinceCity.getCitys(provinceCode);
        } catch (Exception e) {
            log.info("根据省份获取城市异常");
            ResultInfo resultInfo = new ResultInfo(false, null, "服务器忙，请稍后再试！");
            //将resultInfo转换为String
            try {
                //将java对象转化为json对象
                jsonData = new ObjectMapper().writeValueAsString(resultInfo);
            } catch (JsonProcessingException e1) {
                log.info("对象转化为json对象异常"+e1);
            }
        }
        return jsonData;
    }
    /*根据城市获取地区*/
    @RequestMapping("area")
    @ResponseBody
    public String getAreaByCity(String cityCode) {
        log.info("根据城市获取地区开始,城市的名称:"+cityCode);
        String jsonData = null;

        try {
            jsonData=provinceCity.getAreaByCity(cityCode);
        } catch (Exception e) {
            log.info("根据城市获取地区异常");
            ResultInfo resultInfo = new ResultInfo(false, null, "服务器忙，请稍后再试！");
            //将resultInfo转换为String
            try {
                //将java对象转化为json对象
                jsonData = new ObjectMapper().writeValueAsString(resultInfo);
            } catch (JsonProcessingException e1) {
                log.info("对象转化为json对象异常"+e1);
            }
        }
        return jsonData;
    }

}
