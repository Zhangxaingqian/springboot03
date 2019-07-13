package cn.myweb01.money01.controller.elasticsearch;

import cn.myweb01.money01.pojo.PageBean;
import cn.myweb01.money01.pojo.ResultInfo;
import cn.myweb01.money01.service.IElasticsearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("es")
public class EsController {
    private final static Logger log= LoggerFactory.getLogger(EsController.class);

        @Autowired
        private IElasticsearchService elasticsearchService;
    /*
    * 搜索工作*/
    @PostMapping("search")
    @ResponseBody
    public ResultInfo search(@RequestParam(value = "curPage", defaultValue = "1")Integer curPage,
                            @RequestParam(value = "jname", required = false)String jname ) {
        log.info("使用elasticsearch进行搜索开始");
        log.info("根据当前页和搜索内容查询招聘信息开始,当前页:"+curPage+"搜索内容"+jname);
        ResultInfo resultInfo = null;
        try {
            System.out.println("kkk");
            //从es的索引库查询所有的招聘分页信息
            PageBean pageBean = elasticsearchService.queryJobByPage(curPage,jname);

            //查询成功
            resultInfo = new ResultInfo(true, pageBean, null);

        } catch (Exception e) {
            e.printStackTrace();
            resultInfo = new ResultInfo(false, null, "服务器忙，请稍后再试！");
        }
        log.info("根据当前页和搜索内容查询招聘信息结束");
        return resultInfo;
    }

}
