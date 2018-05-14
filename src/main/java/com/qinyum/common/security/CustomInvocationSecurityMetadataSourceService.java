package com.qinyum.common.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import com.qinyum.system.menu.mapper.SysMenuMapper;
import com.qinyum.system.menu.model.SysMenu;
import com.qinyum.system.role.mapper.SysRoleMapper;
import com.qinyum.system.role.model.SysRole;
import com.qinyum.system.user.mapper.SysUserRoleMapper;

@Service
public class CustomInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

	@Autowired
	private SysRoleMapper roleMapper;

	@Autowired
	private SysUserRoleMapper userRolemapper;

	@Autowired
	private SysMenuMapper menuMapper;

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return new ArrayList<ConfigAttribute>();
	}

	/**
	 * 被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次。
	 * PostConstruct在构造函数之后执行,init()方法之前执行。 此处一定要加上@PostConstruct注解
	 *
	 * 注意: 形成以URL为Key,权限列表为Value的Map时，注意Key和Value的对应性，
	 * 避免Value的不正确对应形成重复，这样会导致没有权限的也能访问到不该访问的资源
	 */
	@PostConstruct
	private void loadResourceDefine() {
		// 在Web服务器启动时，提取系统中的所有权限。
		List<SysRole> roles = roleMapper.findAll();
		List<String> roleNames = new ArrayList<String>();
		if (roles != null && roles.size() > 0) {
			for (SysRole role : roles) {
				roleNames.add(role.getEname());
			}
		}

		/*
		 * 应当是资源为key， 权限为value。 资源通常为url， 权限就是那些以ROLE_为前缀的角色。 一个资源可以由多个权限来访问。
		 *
		 */
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		for (String roleName : roleNames) {
			// 将角色名封装一个安全配置？？？这样理解有点不准确， 说白了就是一个角色名，只是为了和框架整合换一种说法
			ConfigAttribute configAttribute = new SecurityConfig(roleName);
			List<String> urlList = new ArrayList<String>();
			List<SysMenu> resourceList = menuMapper.findMenuByRoleEname(roleName);
			if (resourceList != null && resourceList.size() > 0) {// 如果不加判断，这里如果
				for (SysMenu resource : resourceList) {
					urlList.add(resource.getHref());
				}
			}
			for (String res : urlList) {
				String url = res;
				/*
				 * 判断资源文件和权限的对应关系，如果已经存在相关的资源url， 则要通过该url为key提取出权限集合，将权限增加到权限集合中。
				 *
				 */
				if (resourceMap.containsKey(url)) {
					Collection<ConfigAttribute> value = resourceMap.get(url);
					value.add(configAttribute);
					resourceMap.put(url, value);
				} else {
					Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
					atts.add(configAttribute);
					resourceMap.put(url, atts);// 一个URL对应多种角色
				}
			}

		}

		List<SysMenu> menus = menuMapper.findMenuForHref(); // 查询所有的访问级菜单
		for (SysMenu menu : menus) {
			String url = menu.getHref();
			if (!resourceMap.keySet().contains(url)) { // 如果菜单没有授予角色就给一个none
				ConfigAttribute configAttribute = new SecurityConfig("none");
				Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
				atts.add(configAttribute);
				resourceMap.put(url, atts);
			}
		}
		Iterator<String> iterator = resourceMap.keySet().iterator();
		while (iterator.hasNext()) {
			String resURL = iterator.next();
			System.out.println("url＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝" + resURL);
		}
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// object 是一个URL，被用户请求的url。
		ConfigAttribute configAttribute = new SecurityConfig("none");
		Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
		atts.add(configAttribute);
		FilterInvocation filterInvocation = (FilterInvocation) object;
		if (resourceMap == null) {
			loadResourceDefine();
		}
		Iterator<String> iterator = resourceMap.keySet().iterator();
		while (iterator.hasNext()) {
			String resURL = iterator.next();
			// System.out.println("url2＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝"+resURL);
			// 优化请求路径后面带参数的部分
			// RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
			// if (requestMatcher.matches(filterInvocation.getHttpRequest())) {
			// return resourceMap.get(resURL);
			// }
			if(filterInvocation.getHttpRequest().getRequestURI().startsWith(resURL)) {
				return resourceMap.get(resURL);
			}
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
