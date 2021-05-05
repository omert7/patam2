package app.tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import app.model.algorithms.TimeSeries;

public class testClassloader {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String input="",className="";
		System.out.println("enter a class directory");
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		try {
			input=in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // get user input
		System.out.println("enter the class name");
		try {
			className=in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// load class directory
		URLClassLoader urlClassLoader;
		try {
			urlClassLoader = URLClassLoader.newInstance(new URL[] {
			 new URL("file://"+input)
			});
			Class<?> c=urlClassLoader.loadClass(className);
			TimeSeries ts=(TimeSeries)c.newInstance();
			 ts=new TimeSeries("C:\\Users\\guyle\\Desktop\\gitProjects\\patam2\\src\\files\\reg_flight.csv");
			
			
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}

// C:\Users\guyle\eclipse-workspace\algorithms\src\app\model\algorithms\TimeSeries.java
// algorithms.TimeSeries
