package com.oa.controller;


import com.oa.model.*;
import com.oa.serviceInterface.UserServiceInterface;
import com.oa.utils.EncrypUtil2;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class userController {
    @Autowired
    private UserServiceInterface userService;


    @RequestMapping("/login.do")
    public String login(String no, String pass, HttpSession session) {
        String password = EncrypUtil2.md5Pass(pass);
        TUser user = userService.login(no, password);
        if (user != null) {
            user.setFlag(2);
            userService.xiugai(user);
            TUser user1 = userService.queryUser(user.getId());
            session.setAttribute("User", user1);

            return "/index";
        }
        return "/login";
    }

    @RequestMapping("/userloginout.do")
    public String quitOut(HttpSession session) {
        System.out.println("tuichu===========");
        TUser user = (TUser) session.getAttribute("User");
        user.setFlag(1);
        userService.xiugai(user);
        session.invalidate();
        return "/login";
    }

    @RequestMapping("/querySessionUser.do")
    @ResponseBody
    public TUser querySessionUser(HttpSession session) {

        TUser user = (TUser) session.getAttribute("User");

        return user;
    }


    @RequestMapping("/usermenu.do")
    @ResponseBody
    public List<TauthorityVo> userMenu(HttpSession session) {
        TUser user = (TUser) session.getAttribute("User");
        Integer id = user.getId();
        List<TauthorityVo> list = userService.queryMenu(id);
        return list;

    }


    @RequestMapping("/uploadphoto.do")
    @ResponseBody
    public Map<String, Object> uploda(MultipartFile file, HttpServletRequest request, HttpSession session) throws IOException {

        Map<String, Object> map = new HashMap<>();
        /*设置路径前缀*/
        String contextPath = request.getServletContext().getRealPath("/media/images");
      /*  设置路径*/
        String path = contextPath + "/" + file.getOriginalFilename();
       /* 路过传过来的文件不等于空*/
        if (!file.isEmpty()) {
           /* 把路径穿进去*/
            file.transferTo(new File(path));
          /*  返回给前端一个0表示成*/
            map.put("code", 0);
    /*    去域对象中获取数据装填到tuser里面去*/
            TUser user = (TUser) session.getAttribute("User");
            /*设置图片路径*/
            user.setHeadphoto(path);
          /*  设置好之后存到user域里面去然后在去前端获取*/
            session.setAttribute("User", user);
            /*然后在吧数据库的路径也给修改了*/
            String p = "media/images" + "/" + file.getOriginalFilename();
            int i = userService.updateById(p, user.getId());
            return map;
        } else
            /*失败返回11*/
            map.put("code", 11);
        return map;
    }


    @RequestMapping("/updatepassword.do")
    @ResponseBody
    public Map<String, Object> updatepassword(HttpSession session, String oldpassword, String password) {
        System.out.println("控制器层新密码" + password);
        TUser user = (TUser) session.getAttribute("User");
        Map<String, Object> map = new HashMap<>();
        Integer id = user.getId();
        int i = userService.updatePassword(id, oldpassword, password);
        map.put("code", i);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/userall.do")
    @ResponseBody
    public ResultVo queryAll(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer limit,
                             @RequestParam(defaultValue = "") Integer flag,
                             @RequestParam(defaultValue = "") String no) {
        ResultVo resultVo = new ResultVo();
        int count = userService.Usercount();
        resultVo.setCount(count);
        List<TUserRoleVo> list = userService.queryTUserRoleVo((page - 1) * limit, limit, flag, no);
        resultVo.setData(list);

        return resultVo;
    }

    @RequestMapping("/activeuser.do")
    @ResponseBody
    public ResultVo activeuser(Integer id, Integer flag) {
        ResultVo resultVo = new ResultVo();
        int i = userService.updateFlag(id, flag);
        if (i > 0) {
            resultVo.setCode(0);
        } else {
            resultVo.setCode(1111);
        }

        return resultVo;
    }

    @RequestMapping("/allroles.do")
    @ResponseBody
    public List<TRole> allroles() {

        List<TRole> list = userService.queryAllRoles();


        return list;
    }

    @RequestMapping("/userroleedit.do")
    @ResponseBody
    public ResultVo up(Integer id, Integer[] rids) {
        ResultVo resultVo = new ResultVo();
        int i = userService.delAndins(id, rids);

        if (i > 0) {
            resultVo.setCode(0);
        } else {
            resultVo.setCode(1111);
        }

        return resultVo;
    }

    @RequestMapping("/rolepage.do")
    @ResponseBody
    public ResultVo queryrole(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "5") Integer limit
    ) {
        ResultVo resultVo = new ResultVo();
        int count = userService.cont();
        resultVo.setCount(count);
        List<TRole> list = userService.queryrole((page - 1) * limit, limit);
        resultVo.setData(list);


        return resultVo;
    }

    @RequestMapping("/queryroleByid.do")
    @ResponseBody
    public TRole tRole(Integer rid) {
        TRole queryrole = userService.queryroleid(rid);

        System.out.println(queryrole);
        return queryrole;
    }

    @RequestMapping("/editrole.do")
    @ResponseBody
    public Object troleupd(String id, String name, String info) {

        int roleup = userService.roleup(name, info, id);
        System.out.println(roleup);
        return 123;
    }

    @RequestMapping("/roledel.do")
    @ResponseBody
    public ResultVo troledel(Integer id) {
        int roledel = userService.roledel(id);
        System.out.println(roledel);
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(1000);
        return resultVo;
    }

    @ResponseBody
    @RequestMapping("/queryroleauthority.do")
    public ResultVo resultVo() {

        ResultVo resultVo = new ResultVo();
        List<ZtreeVo> querauth = userService.queryRoleZtree();
        resultVo.setData(querauth);
        return resultVo;
    }

    @RequestMapping("/editroleauthority.do")
    @ResponseBody
    public ResultVo res(Integer rid, @Param("aids") Integer[] aids) {
        ResultVo resultVo = new ResultVo();
        userService.inserrole(rid, aids);
        resultVo.setCode(0);
        return resultVo;

    }

    @RequestMapping("/authpage.do")

    @ResponseBody
    public ResultVo atuh() {

        ResultVo resultVo = new ResultVo();
        List<TAuthority> ta = userService.ta();
        resultVo.setData(ta);
        System.out.println(resultVo);
        return resultVo;

    }

}
