package com.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import com.ideator.common.ConfigProperties;
import com.ideator.util.PageClass;

public class MyInterceptor implements IMethodInterceptor {
	Collection test_methods;
	WebDriver driver;

	public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {

		List<IMethodInstance> newList = new ArrayList<IMethodInstance>(methods.size());
		PageClass pageClass = new PageClass();
		try {
			pageClass.deleteDirectory();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (IMethodInstance m : methods) {
			String className = m.getInstance().getClass().getSimpleName();
			try {
				String path =pageClass.createScreenshotFolder(className);
				ConfigProperties.setScreenshotPath(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			test_methods = ConfigProperties.getSortedMethods(className);
			// System.out.println("-******************" + test_methods);
			// System.out.println("MyInterceptor : className : " + className);
		}

		for (Object test_method : test_methods) {
			for (IMethodInstance m : methods) {
				String MethodName = m.getMethod().getMethodName();
				if (test_method.toString().equals(MethodName)) {
					newList.add(m);
				}
//				 System.out.println("MyInterceptor : MethodName : " + MethodName);

			}
		}
		return newList;
	}
}
