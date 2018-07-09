package com.itheima.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
/**
 * Shiro的认证操作
 * @author lenovo
 *
 */
public class Demo1 {

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
