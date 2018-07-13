package com.simple.resume.controller;

import com.simple.resume.VO.ResumeListVO;
import com.simple.resume.VO.ResumeVO;
import com.simple.resume.VO.UserInfoVO;
import com.simple.resume.common.JsonResult;
import com.simple.resume.pojo.*;
import com.simple.resume.service.*;
import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    ResumeService resumeService;

    @Autowired
    ObjectiveService objectiveService;

    @Autowired
    EduBackgroundService eduBackgroundService;

    @Autowired
    SkillService skillService;

    @Autowired
    WorkexperienceService workexperienceService;

    @Autowired
    ResumeTextService resumeTextService;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 用于显示各类简历
     * @param status 0为未处理，1为通过，2为拒绝
     * @return
     */
    @RequestMapping(value = "/resumeList", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String resumeList(@RequestParam("status") Integer status) {
        List<Resume> resumeList = resumeService.findAllByStatus(status);
        List<ResumeListVO> resumeListVOs = new ArrayList<>();
        for (Resume resume : resumeList) {
            ResumeListVO resumeListVO = new ResumeListVO();
            try {
                resumeListVO.setS_sex(resume.getSex() == 0 ? "男" : "女");
                resumeListVO.setDeliver_time(resume.getDeliverTime().toString().substring(0, resume.getDeliverTime().toString().length() - 2));
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

    /**
     * 用于用户创建简历
     * @param params 包含简历表单的所有信息，再自行处理
     * @param userID
     * @return
     * @throws UnsupportedEncodingException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String create(@RequestParam("params") String params, @RequestParam("userID") Integer userID) throws UnsupportedEncodingException, InvocationTargetException, IllegalAccessException {
        //获得所有简历表单的参数，包括基本信息、求职意向、教育背景、项目经验、技能、自我评价
        String[] strings = params.split("&");
        //所有简历信息的实体类
        Resume resume = new Resume();//基本信息
        EduBackground eduBackground = new EduBackground();//教育背景
        Objective objective = new Objective();//求职意向
        List<Workexperience> workexperiences = new ArrayList<>();//项目经验
        List<Skill> skills = new ArrayList<>();//技能

        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < strings.length; i++) {
            String[] split = strings[i].split("=");
            map.put(split[0], URLDecoder.decode(split[1], "UTF-8"));
            if (split[0].equals("projectName")) {
                Workexperience workexperience = new Workexperience();
                workexperience.setProjectName(URLDecoder.decode(split[1], "UTF-8"));
                workexperience.setProjectDesc(URLDecoder.decode(strings[i + 1].split("=")[1], "UTF-8"));
                workexperience.setRoleDesc(URLDecoder.decode(strings[i + 2].split("=")[1], "UTF-8"));
                workexperiences.add(workexperience);
            }
            if (split[0].equals("name")) {
                Skill skill = new Skill();
                skill.setName(URLDecoder.decode(split[1], "UTF-8"));
                skill.setValue(URLDecoder.decode(strings[i + 1].split("=")[1], "UTF-8"));
                skills.add(skill);
            }
        }

        try {
            DateConverter dateConverter = new DateConverter();
            //设置日期格式
            dateConverter.setPattern("yyyy-MM-dd");
            //注册格式
            ConvertUtils.register(dateConverter, Date.class);
            BeanUtils.populate(resume, map);
            resume.setUserID(userID);
            resume.setUpdateTime(Timestamp.valueOf(df.format(new Date())));

            BeanUtils.populate(eduBackground, map);
            eduBackground.setUserID(userID);

            BeanUtils.populate(objective, map);
            objective.setUserID(userID);

            for (Workexperience workexperience : workexperiences) {
                workexperience.setUserID(userID);
            }

            for (Skill skill : skills) {
                skill.setUserID(userID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //如果用户已经保存过简历，那现在的操作就是更新
        if (resumeService.findByUserID(userID) != null) {
            Resume resume1 = resumeService.findByUserID(userID);
            resumeService.updateResume(resume);
            eduBackground.setResumeId(resume1.getId());
            eduBackgroundService.updateEdu(eduBackground);
            objective.setResumeId(resume1.getId());
            objectiveService.updateObjective(objective);
            skillService.deleteAllByResumeId(resume1.getId());
            for (Skill skill : skills) {
                skill.setResumeId(resume1.getId());
                skillService.saveSkill(skill);
            }
            workexperienceService.deleteAllByResumeId(resume1.getId());
            for (Workexperience workexperience : workexperiences) {
                workexperience.setResumeId(resume1.getId());
                workexperienceService.saveWorkexperience(workexperience);
            }

            //处理用于全文检索的长字符串
            ResumeVO resumeVO = new ResumeVO();
            BeanUtils.copyProperties(resumeVO, resume);
            resumeVO.setS_sex(resume.getSex() == 0 ? "男" : "女");
            resumeVO.setBirthday(df2.format(resume.getBirthday()));
            BeanUtils.copyProperties(resumeVO, eduBackground);
            resumeVO.setStartTime(df2.format(eduBackground.getStartTime()));
            resumeVO.setEndTime(df2.format(eduBackground.getEndTime()));
            BeanUtils.copyProperties(resumeVO, objective);
            resumeVO.setW_workstyle(objective.getWorkstyle() == 0 ? "实习" : "全职");
            resumeVO.setSkills(skills);
            resumeVO.setWorkexperiences(workexperiences);

            ResumeText resumeText = new ResumeText();
            resumeText.setResumeId(resume1.getId());
            resumeText.setUserID(userID);
            resumeText.setInfo(JSONSerializer.toJSON(resumeVO).toString().replaceAll("\r\n", " "));
            if (resumeTextService.findByResumeId(resume1.getId()) == null) {
                resumeTextService.saveResumeText(resumeText);
            } else {
                resumeTextService.updateResumeText(resumeText);
            }

        } else {
            //新创建简历
            resumeService.saveResume(resume);
            eduBackground.setResumeId(resume.getId());
            eduBackgroundService.saveEdu(eduBackground);
            objective.setResumeId(resume.getId());
            objectiveService.saveObjective(objective);
            for (Skill skill : skills) {
                skill.setResumeId(resume.getId());
                skillService.saveSkill(skill);
            }
            for (Workexperience workexperience : workexperiences) {
                workexperience.setResumeId(resume.getId());
                workexperienceService.saveWorkexperience(workexperience);
            }

            //处理用于全文检索的长字符串
            ResumeVO resumeVO = new ResumeVO();
            BeanUtils.copyProperties(resumeVO, resume);
            resumeVO.setS_sex(resume.getSex() == 0 ? "男" : "女");
            resumeVO.setBirthday(df2.format(resume.getBirthday()));
            BeanUtils.copyProperties(resumeVO, eduBackground);
            resumeVO.setStartTime(df2.format(eduBackground.getStartTime()));
            resumeVO.setEndTime(df2.format(eduBackground.getEndTime()));
            BeanUtils.copyProperties(resumeVO, objective);
            resumeVO.setW_workstyle(objective.getWorkstyle() == 0 ? "实习" : "全职");
            resumeVO.setSkills(skills);
            resumeVO.setWorkexperiences(workexperiences);

            ResumeText resumeText = new ResumeText();
            resumeText.setResumeId(resume.getId());
            resumeText.setUserID(userID);
            resumeText.setInfo(JSONSerializer.toJSON(resumeVO).toString().replaceAll("\r\n", " "));
            if (resumeTextService.findByResumeId(resume.getId()) == null) {
                resumeTextService.saveResumeText(resumeText);
            } else {
                resumeTextService.updateResumeText(resumeText);
            }
        }
        return JSONSerializer.toJSON(new JsonResult<>()).toString();
    }

    /**
     * 预览简历
     * @param userID
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping(value = "/preview", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String preview(@RequestParam("userID") Integer userID) throws InvocationTargetException, IllegalAccessException {
        ResumeVO resumeVO = new ResumeVO();
        Resume resume = resumeService.findByUserID(userID);
        if (resume == null) {
            JSON json = JSONSerializer.toJSON(new JsonResult(1, "未找到简历!", null));
            return json.toString();
        }
        BeanUtils.copyProperties(resumeVO, resume);
        resumeVO.setS_sex(resume.getSex() == 0 ? "男" : "女");
        resumeVO.setBirthday(df2.format(resume.getBirthday()));

        EduBackground eduBackground = eduBackgroundService.findByResumeId(resume.getId());
        BeanUtils.copyProperties(resumeVO, eduBackground);
        resumeVO.setStartTime(df2.format(eduBackground.getStartTime()));
        resumeVO.setEndTime(df2.format(eduBackground.getEndTime()));

        Objective objective = objectiveService.findByResumeId(resume.getId());
        BeanUtils.copyProperties(resumeVO, objective);
        resumeVO.setW_workstyle(objective.getWorkstyle() == 0 ? "实习" : "全职");

        List<Skill> skills = skillService.findAllByUserID(userID);
        resumeVO.setSkills(skills);
        List<Workexperience> workexperiences = workexperienceService.findAllByUserID(userID);
        resumeVO.setWorkexperiences(workexperiences);

        JSON json = JSONSerializer.toJSON(new JsonResult<ResumeVO>(0, "简历加载成功!", resumeVO));
        return json.toString();
    }

    /**
     * 用户投递简历
     * @param userID
     * @return
     */
    @RequestMapping(value = "/deliver", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deliver(@RequestParam("userID") Integer userID) {
        Resume resume = resumeService.findByUserID(userID);
        if (resume.getStatus() != 3) {
            JSON json = JSONSerializer.toJSON(new JsonResult(1, "该简历已经投递!", null));
            return json.toString();
        }
        resume.setStatus(0);
        resume.setDeliverTime(Timestamp.valueOf(df.format(new Date())));
        resumeService.updateResume(resume);
        JSON json = JSONSerializer.toJSON(new JsonResult(0, "简历投递成功,请耐心等待邮件回复!", null));
        return json.toString();
    }

    /**
     * 修改用户的简历状态
     * @param userID
     * @param status
     * @return
     */
    @RequestMapping(value = "/operatorStatus", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String operatorStatus(@RequestParam("userID") Integer userID, @RequestParam("status") Integer status) {
        Resume resume = resumeService.findByUserID(userID);
        resume.setStatus(status);
        resumeService.updateResume(resume);
        if (status == 1) {
            JSON json = JSONSerializer.toJSON(new JsonResult(0, "简历处理成功，处理结果为 [通过] !", null));
            return json.toString();
        } else {
            JSON json = JSONSerializer.toJSON(new JsonResult(0, "简历处理成功，处理结果为 [拒绝] !", null));
            return json.toString();
        }

    }
}
