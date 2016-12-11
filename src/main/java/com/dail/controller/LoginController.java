package com.dail.controller;

import com.dail.model.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Roger on 2016/12/11.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@ModelAttribute SysUser sysUser){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute SysUser sysUser,
                        @RequestParam(required = false, defaultValue = "false") Boolean rememberMe,
                        HttpSession session,
                        HttpServletRequest request,
                        RedirectAttributes redirectAttributes){
        UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUsername(), sysUser.getPassword());
        token.setRememberMe(rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            redirectAttributes.addFlashAttribute("msg", "用户名/密码错误");
        } catch (IncorrectCredentialsException e){
            redirectAttributes.addFlashAttribute("msg", "用户名/密码错误");
        } catch (LockedAccountException e){
            redirectAttributes.addFlashAttribute("msg", "用户被锁定");
        } catch (AuthenticationException e){
            redirectAttributes.addFlashAttribute("msg", "用户名/密码错误");
        }

        if (subject.isAuthenticated()){
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
            redirectAttributes.addFlashAttribute("username", sysUser.getUsername());
            token.clear();
            /*response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);*/
            return "redirect:/login";
        }
    }
}
