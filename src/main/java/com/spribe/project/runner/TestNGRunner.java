package com.spribe.project.runner;

import com.spribe.project.config.ConfigManager;
import org.testng.TestNG;
import org.testng.xml.XmlPackage;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.Collections;

public class TestNGRunner {
    public static void main(String[] args) {
        int threads = ConfigManager.getThreadCount();

        XmlSuite suite = new XmlSuite();
        suite.setName("API Suite");
        suite.setParallel(XmlSuite.ParallelMode.METHODS);
        suite.setThreadCount(threads);

        XmlTest test = new XmlTest(suite);
        test.setName("API Tests");
        test.setPackages(Collections.singletonList(new XmlPackage("com.spribe.tests.users")));

        TestNG testng = new TestNG();
        testng.setXmlSuites(Collections.singletonList(suite));
        testng.run();
    }
}