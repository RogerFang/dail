package com.dail.controller;

import com.dail.entity.SysRole;
import com.dail.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Roger on 2016/12/11.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username,
                        String password,
                        Boolean rememberMe,
                        HttpSession session,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        Model model){
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(rememberMe);
        String error = null;
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            error = "用户名/密码错误";
            // e.printStackTrace();
        } catch (IncorrectCredentialsException e){
            error = "用户名/密码错误";
            // e.printStackTrace();
        } catch (LockedAccountException e){
            error = "用户被锁定";
            // e.printStackTrace();
        } catch (AuthenticationException e){
            error = "其他错误: " + e.getMessage();
            // e.printStackTrace();
        }

        if (error == null){
            // 将登入用户放入session
            SysUser user = (SysUser) subject.getPrincipal();
            session.setAttribute("sessionUid", user.getId());

            SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
            if (savedRequest != null){
                String p_uri = savedRequest.getRequestURI();
                return "redirect:"+p_uri;
            }else {
                return "redirect:/";
            }
        }else {
            model.addAttribute("username", username);
            model.addAttribute("error", error);

            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            return "login";
        }
    }
}
