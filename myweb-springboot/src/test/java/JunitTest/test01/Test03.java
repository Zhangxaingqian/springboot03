package JunitTest.test01;

import JunitTest.MyUnitTest;
import cn.myweb01.money01.pojo.SecondJobCategory;
import cn.myweb01.money01.service.ICategoryService;
import cn.myweb01.money01.service.IJobInfo1Service;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class Test03 extends MyUnitTest {
    @Autowired
    private ICategoryService jobInfo1Service;

    @Test
    public void test(){
        SecondJobCategory secondJobCategory = jobInfo1Service.queryJobGradeById(8);
        System.out.println("secondJobCategory = " + secondJobCategory.getSecondName());
    }
}
