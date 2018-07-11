package com.simple.resume.controller;

import com.simple.resume.VO.ResumeListVO;
import com.simple.resume.VO.UserInfoVO;
import com.simple.resume.common.JsonResult;
import com.simple.resume.pojo.Objective;
import com.simple.resume.pojo.Resume;
import com.simple.resume.service.ObjectiveService;
import com.simple.resume.service.ResumeService;
import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    ResumeService resumeService;

    @Autowired
    ObjectiveService objectiveService;

    @RequestMapping(value = "/resumeList", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String resumeList(@RequestParam("status") Integer status) {
        List<Resume> resumeList = resumeService.findAllByStatus(status);
        List<ResumeListVO> resumeListVOs = new ArrayList<>();
        for (Resume resume : resumeList) {
            ResumeListVO resumeListVO = new ResumeListVO();
            try {
                resumeListVO.setS_sex(resume.getSex() == 0 ? "男" : "女");
                resumeListVO.setDeliver_time(resume.getDeliverTime().toString().substring(0, resume.getDeliverTime().toString().length()-2));
                BeanUtils.copyProperties(resumeListVO, resume);
                Objective objective = objectiveService.findByResumeId(resume.getId());
                BeanUtils.copyProperties(resumeListVO, objective);
                resumeListVOs.add(resumeListVO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        JSON json = JSONSerializer.toJSON(new JsonResult<List<ResumeListVO>>(0, "查询未处理简历列表成功", resumeListVOs));
        return json.toString();
    }

}
