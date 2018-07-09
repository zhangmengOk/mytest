package com.itheima.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
/**
 * Shiro的授权操作
 * @author lenovo
 *
 */
public class Demo2 {

	public static void main(String[] args) {
		//1.创建SecurityManager工厂
		//必须导入的org.apache.shiro.mgt.SecurityManager
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		
		//2.创建SecurityManager
		SecurityManager securityManager = factory.getInstance();
		
		//3.初始化SecurityUtils
		SecurityUtils.setSecurityManager(securityManager);
		
		//4.获取Subject对象
		Subject subject = SecurityUtils.getSubject();
		
		//5.执行认证操作
		//AuthenticationToken：封装用户输入的用户和密码
		AuthenticationToken token = new UsernamePasswordToken("eric", "123456");
		
		try {
			subject.login(token);
			
			//获取principal数据
			Object principal = subject.getPrincipal();
			System.out.println(principal);
			
			//保存用户信息到session中
			
			System.out.println("认证（登录）成功...");
			
			
			//演示如何授权
			//一、基于资源的授权
			//需求：判断当前登录的用户，有没有用户添加的权限
			//isPermitted:授权方法
			System.out.println(subject.isPermitted("user:add"));
			System.out.println(subject.isPermitted("user:update"));
			System.out.println(subject.isPermitted("user:delete"));
			
			//二、基于角色的授权（了解）
			System.out.println(subject.hasRole("admin"));
			
			
		} catch (UnknownAccountException e) {
			//e.printStackTrace();
			System.out.println("账户不存在...");
		} catch (IncorrectCredentialsException e) {
			//e.printStackTrace();
			System.out.println("密码错误...");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("其他错误...");
		}
	}
}
