package cn.myweb01.money01.service.impl.elasticsearch;

import cn.myweb01.money01.pojo.JobInfo1;
import cn.myweb01.money01.pojo.SecondJobCategory;
import cn.myweb01.money01.pojo.elasticsearch.JobSearch;
import cn.myweb01.money01.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
* 创建service将工作表查出来的信息存储到索引库中,
*   也就是将查出来的信息转化为Jobserch对象*/
@Service
public class JobSearchService {

    @Autowired
    private ICategoryService categoryService;

    //创建JobSearch对象的方法
    public JobSearch createJobSearch(JobInfo1 jobInfo1){
        JobSearch jobSearch = new JobSearch();
        //根据jobgrade查询对应的中文名称
        Integer jobGrade = jobInfo1.getJobGrade();
        SecondJobCategory gradeName = categoryService.queryJobGradeById(jobGrade);
        //查询详细要求
        String job1Detail = jobInfo1.getJbob1Detail().getJob1Detail();


        //对jobsearch进行赋值
        jobSearch.setId(jobInfo1.getJob1Id());
        jobSearch.setAll(jobGrade+" "+job1Detail);
        jobSearch.setJobWage(jobInfo1.getJobWage());
        jobSearch.setJobGrade(jobInfo1.getJobGrade());
        jobSearch.setJobSite(jobInfo1.getJobSite());


        return jobSearch;
    }
}
