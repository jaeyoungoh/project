package kr.co.mproject.cont;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class test
 */
@WebServlet("/test.do")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	        //sampleData= here you can get data from database

	        //writing data to json
	        response.setContentType("application/json;charset=utf-8");

	        JSONObject mainJson = new JSONObject();
	        JSONArray array = new JSONArray();
	        JSONObject contents =  new JSONObject();

	        contents.put("contents", "컨텐츠 입니다.");
	        array.put(contents);

	        mainJson.put("resultJson", array);
	        
	        System.out.println("json 파일 내용 ㅇㅇㅇ"+ mainJson.toString());

	        PrintWriter pw = response.getWriter(); 
	        pw.print(mainJson.toString());
	        pw.close();

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
