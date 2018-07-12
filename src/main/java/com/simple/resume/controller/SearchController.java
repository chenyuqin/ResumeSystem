package com.simple.resume.controller;

import com.simple.resume.VO.SkillSearchVO;
import com.simple.resume.VO.UserInfoVO;
import com.simple.resume.common.JsonResult;
import com.simple.resume.pojo.Objective;
import com.simple.resume.pojo.Resume;
import com.simple.resume.pojo.Skill;
import com.simple.resume.pojo.User;
import com.simple.resume.service.*;
import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    ResumeService resumeService;

    @Autowired
    ObjectiveService objectiveService;

    @Autowired
    SkillService skillService;

    @Autowired
    ResumeTextService resumeTextService;

    @RequestMapping(value = "/skill", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String searchBySkill(@RequestParam("name") String name) {

        List<Integer> userIDs = skillService.findUserIDByName(name);
        List<SkillSearchVO> skillSearchVOs = new ArrayList<>();
        for (Integer userID : userIDs) {
            SkillSearchVO skillSearchVO = new SkillSearchVO();
            try {
                Resume resume = resumeService.findByUserID(userID);
                skillSearchVO.setS_sex(resume.getSex() == 0 ? "男" : "女");
                skillSearchVO.setDeliver_time(resume.getDeliverTime().toString().substring(0, resume.getDeliverTime().toString().length()-2));
                BeanUtils.copyProperties(skillSearchVO, resume);

                Objective objective = objectiveService.findByResumeId(resume.getId());
                BeanUtils.copyProperties(skillSearchVO, objective);

                List<Skill> skills = skillService.findAllByUserID(userID);
                StringBuilder sb = new StringBuilder();
                for (Skill skill : skills) {
                    sb.append(skill.getName() + "、");
                }
                skillSearchVO.setSkills(sb.deleteCharAt(sb.length() - 1).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            skillSearchVOs.add(skillSearchVO);
        }
        JSON json = JSONSerializer.toJSON(new JsonResult<List<SkillSearchVO>>(0, "按技能查询成功!", skillSearchVOs));
        return json.toString();
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String all(@RequestParam("keyWord") String keyWord) {

        List<Integer> userIDs = resumeTextService.findByKeyWord(keyWord);
        List<Resume> resumeList = new ArrayList<>();
        for (Integer userID : userIDs) {
            Resume resume = resumeService.findByUserID(userID);
            if (resume.getStatus() != 3) {
                resumeList.add(resume);
            }
        }
        JSON json = JSONSerializer.toJSON(new JsonResult<List<Resume>>(0, "全文检索成功!", resumeList));
        return json.toString();
    }

}
