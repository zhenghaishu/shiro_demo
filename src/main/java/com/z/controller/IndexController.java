package com.z.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.support.json.JSONUtils;
import com.z.util.EncryptUtil;

@Controller
public class IndexController {
	@RequestMapping("/index.html")
	public ModelAndView getIndex(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}
	
	@RequestMapping("/login.html")
	public ModelAndView login() throws Exception {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	@RequestMapping("/loginsuccess.html")
	public ModelAndView loginsuccess() throws Exception {
		ModelAndView mav = new ModelAndView("loginsuccess");
		return mav;
	}
	
	@RequestMapping(value = "newpage1.html")
	public String shownewpage1() {
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser.hasRole("administrator")){
			return "newPage";
		}else{
			return "pagenofound";
		}
	}
	
	@RequestMapping("/newpage2.html")
	public String shownewpage2() {
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser.isPermitted("newPage2.html")){
			return "newPage";
		}else{
			return "pagenofound";
		}
	}
	
	@RequestMapping("/error.html")
	public String error() {
		return "error";
	}

    @RequestMapping(value="/checkLogin.json",method=RequestMethod.POST)  
    @ResponseBody 
    @RequiresRoles("admin")
    public String checkLogin(String username,String password) {  
    	Map<String, Object> result = new HashMap<String, Object>();
    	
    	try{
    		UsernamePasswordToken token = new UsernamePasswordToken(username, EncryptUtil.encryptMD5(password));  
            Subject currentUser = SecurityUtils.getSubject();  
            if (!currentUser.isAuthenticated()){
                token.setRememberMe(true);  
                currentUser.login(token);//验证角色和权限  
            } 
    	}catch(Exception ex){
    		ex.printStackTrace(); 		
    	}
    	
        result.put("success", true);
        return JSONUtils.toJSONString(result);  
    } 
    
    @RequestMapping(value="/logout.json",method=RequestMethod.POST)    
    @ResponseBody    
    public String logout() {   
    	Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);
        Subject currentUser = SecurityUtils.getSubject();       
        currentUser.logout();    
        return JSONUtils.toJSONString(result);
    } 
    
    @RequestMapping(value="/newpage1.json",method=RequestMethod.POST)    
    @ResponseBody    
    public String newpage1() {   
    	Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);   
        return JSONUtils.toJSONString(result);
    } 
    
    @RequestMapping(value="/newpage2.json",method=RequestMethod.POST)    
    @ResponseBody    
    public String newpage2() {   
    	Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);   
        return JSONUtils.toJSONString(result);
    } 
}
