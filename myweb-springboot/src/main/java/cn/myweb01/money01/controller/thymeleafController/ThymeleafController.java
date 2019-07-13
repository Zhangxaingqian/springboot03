package cn.myweb01.money01.controller.thymeleafController;

import cn.myweb01.money01.pojo.JobInfo1;
import cn.myweb01.money01.pojo.User;
import cn.myweb01.money01.service.IJobInfo1Service;
import cn.myweb01.money01.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("thymeleaf")
public class ThymeleafController {
    private final static Logger log= LoggerFactory.getLogger(ThymeleafController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IJobInfo1Service jobInfo1Service;

    @GetMapping("/users")
    public String all(ModelMap model) {
        // 查询用户
        List<User> users = this.userService.queryAllUsers();
        User user=users.get(0);
        // 放入模型
        model.addAttribute("user", user);
        // 返回模板名称（就是classpath:/templates/目录下的html文件名）
        return "users";
    }

    @GetMapping("{id}.html")
    public String jobDetail(@PathVariable("id") Integer id, ModelMap model) {
        // 根据id查询工作表的详情
        JobInfo1 jobInfo1 = jobInfo1Service.selectDetailJobInfo1ById(id);
        // 放入模型,属性名类似map中的key,可以在页面中通过key获取
        model.addAttribute("jobInfo1", jobInfo1);
        // 返回模板名称（就是classpath:/templates/目录下的html文件名）
        return "jobDetails";
    }

}
