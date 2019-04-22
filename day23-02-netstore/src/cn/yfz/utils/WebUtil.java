package cn.yfz.utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import cn.yfz.domain.Category;

public class WebUtil {

	public static <T> T fillBean(HttpServletRequest request, Class<T> class1) {
		// TODO Auto-generated method stub
		try {
			T bean=class1.newInstance();
			BeanUtils.populate(bean, request.getParameterMap());
			return bean;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} 
	}
	
}
