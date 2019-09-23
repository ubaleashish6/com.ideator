package com.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.ideator.common.ConfigProperties;
import com.ideator.common.WorkBookRead;

public class Ideator_Start {
	private int TestClassNameRowNum = 2;
	private int TestMethodNameRowNum = 3;
	private int TestMethodStatus = 4;
	WorkBookRead bookRead = new WorkBookRead();
	
	//Get class name and method name that we have to run and gives call to testng
	public void testNgRun() throws Exception {
		TestListenerAdapter adapter = new TestListenerAdapter();
		TestNG testNG = new TestNG();
		testNG.addListener(adapter);
		XmlSuite suite = new XmlSuite();
		suite.setName("The ideator Suite");
		LinkedHashSet<String> classNameList = getClassDerivedData();

		for (String className : classNameList) {
			ArrayList<Integer> classRowNums = bookRead.findRow(className);
			int classRowCount = classRowNums.size();
			ArrayList<String> methodNameList = getMethodLevelData(className,classRowNums, classRowCount);
			runMethodLevel(testNG, suite,methodNameList, className);
		}

		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		testNG.setMethodInterceptor(new MyInterceptor());
		testNG.setXmlSuites(suites);
//		System.out.println(suite.toXml().toString());
		testNG.run();
	}

	//Gives all runable methods list to the testng and excludes methods having status is "N"
	private void runMethodLevel(TestNG testNG, XmlSuite suite, ArrayList<String> methodNameList, String className) {
		XmlTest test = new XmlTest();
		test.setSuite(suite);
		XmlClass testClass = new XmlClass();
		testClass.setName(className);
		if (methodNameList.isEmpty()) {
			test.setName("Tests " + className);
		} else {
			test.setName("Tests " + methodNameList);
		}
		List<XmlClass> test_classes = new ArrayList<XmlClass>();
		/*
		 * for(String methodName:methodNameList){
		 * testClass.getIncludedMethods().add(new XmlInclude(methodName)); }
		 */ 
		testClass.setExcludedMethods(methodNameList);
		test_classes.add(testClass);
		test.setXmlClasses(test_classes);
		test.getClasses().addAll(test_classes);
		suite.addTest(test);

	}

	//Excludes the method name which status is "N" and other all method is added to given Arraylist
	private ArrayList<String> getMethodLevelData(String className, ArrayList<Integer> classRowNums, int rowIndex) throws IOException {
		ArrayList<String> arrayList = new ArrayList<String>();
		try {
			String[][] data = bookRead.ReadsheetTDD();
			for (Integer row : classRowNums) {
				if (data[row][TestMethodStatus] != null && data[row][TestMethodStatus].length() != 0) {
					String methodName = data[row][TestMethodNameRowNum];
					String methodStatus = data[row][TestMethodStatus];
					if(methodStatus.equals("Y")){
						String classname =className.substring(9); 
						ConfigProperties.setSortedMethods(classname, methodName);
					}
					if (methodStatus.equals("N")) {
						arrayList.add(methodName);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	
	//Get class name that you have to include in status 
	//It only add class name when their status is "YES"
	private LinkedHashSet<String> getClassDerivedData() throws IOException {
		int[] count = bookRead.getRow_Column_Count(2);
		int rowCount = count[0];
		LinkedHashSet<String> classnamelist = new LinkedHashSet<String>();
		try {
			String[][] data = bookRead.ReadsheetTDD();
			for (int row = 1; row < rowCount; row++) {
				String className = data[row][TestClassNameRowNum];
				classnamelist.add(className);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return classnamelist;
	}
	
//	This is Starting Point of the application that method gives call to testng
	public static void main(String[] args) throws Exception {
		Ideator_Start ideator_Start = new Ideator_Start();
		ideator_Start.testNgRun();
	}

}
