package kr.co.mproject.cont;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import kr.co.mproject.model.dao.ProDAO;
import kr.co.mproject.model.dto.ProDTO;
import kr.co.mproject.model.dto.UserDTO;

@WebServlet("/project.do")
public class ProjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String code = "";
	private ProDAO pDao;
	private ProDTO pDto;
	private UserDTO uDto;
	private List<ProDTO> list;
	private String path;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		code = request.getParameter("code");
		request.getContextPath();

		/**
		 * 리스트 호출 시
		 */
		if (code.equals("list")) {
			pDao = new ProDAO();
			list = pDao.listviewProject();
			request.setAttribute("list", list);
			String url = "/jsp/pro_list.jsp";
			moveForward(request, response, url);

		}

		/**
		 * 프로젝트 상세 뷰 호출 시
		 */
		else if (code.equals("view")) {
			pDao = new ProDAO();
			Map<String, Object> map = new HashMap<String, Object>();
			map = pDao.viewProject(Integer.parseInt(request.getParameter("p_id")));
			pDto = new ProDTO();
			uDto = new UserDTO();
			pDto = (ProDTO) map.get("pDto");
			uDto = (UserDTO) map.get("uDto");
			request.setAttribute("pDto", pDto);
			request.setAttribute("uDto", uDto);
			String url = "/jsp/pro_view.jsp";
			moveForward(request, response, url);

		}
		/**
		 * 수정을 위해 기존 DB 내용 불러 오기
		 */
		else if (code.equals("mod")){
			int p_id =0;
			pDao = new ProDAO();
			if(!request.getParameter("p_id").equals("")){
				p_id = Integer.parseInt(request.getParameter("p_id"));
			}
			pDto = pDao.modLoad(p_id);
			
			//유저id list 출력 
			List<String> ulist = pDao.searchUid();
			request.setAttribute("list", ulist);
			request.setAttribute("pDto", pDto);
			String url = "jsp/pro_modify.jsp";
			moveForward(request, response, url);
		}
		/**
		 * 추가 전에 useer id를 불러오기위한것.
		 */
		else if (code.equals("search_u_id")){
			pDao = new ProDAO();
			List<String> ulist = pDao.searchUid();
			request.setAttribute("list", ulist);
			
			String url = "/jsp/pro_make.jsp";
			moveForward(request, response, url);
		
		}
		/**
		 * 검색 시 호출
		 */
		else if (code.equals("search_plist")){
			response.setContentType("application/json;charset=utf-8");
			System.out.println("검색 호출");
			System.out.println("파라미터"+request.getParameter("s_obj"));
			System.out.println("파라미터2"+request.getParameter("s_searchQuery"));

			JSONObject jobj = new JSONObject();
			JSONArray jobjarr = new JSONArray();
			pDto = new ProDTO();
			pDao = new ProDAO();
			String s_type= request.getParameter("s_type");
			String s_query = request.getParameter("s_query");
			list = pDao.searchquery(s_type, s_query);
			
			jobj.put("list", list);
			jobjarr.put(jobj);
			
			System.out.println("jobarr"+jobjarr);
			PrintWriter pw = response.getWriter(); 
			pw.print(jobjarr.toString());
			pw.close();
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		path = request.getContextPath()+"/";

		// /project.do?code=add
		request.setCharacterEncoding("UTF-8");

		code = request.getParameter("code");
		System.out.println("code : " + code);
		/**
		 * pro_make.jsp 에서 입력 폼에서 post로 넘어 왔을때
		 */
		if (code.equals("add")) {

			pDao = new ProDAO();
			pDto = new ProDTO();

			pDto.setU_id(request.getParameter("u_id"));
			pDto.setP_type(request.getParameter("p_type"));
			pDto.setP_subject(request.getParameter("p_subject"));
			pDto.setP_contents(request.getParameter("p_contents"));
			pDto.setP_priority(request.getParameter("p_priority"));
			
			int p_upper = 0;
			if (!request.getParameter("p_upper").equals("")) {
				p_upper = Integer.parseInt(request.getParameter("p_upper"));
			}
			pDto.setP_upper(p_upper);
			pDto.setP_sdate(request.getParameter("p_sdate"));
			pDto.setP_edate(request.getParameter("p_edate"));

			int p_depth = 0;
			if (!request.getParameter("p_depth").equals("")) {
				p_depth = Integer.parseInt(request.getParameter("p_depth"));
			}
			pDto.setP_depth(p_depth);

			pDto.setP_progress(Integer.parseInt(request.getParameter("p_progress")));

			int chk = pDao.addProject(pDto);
			if (chk == 1) {
				
				response.sendRedirect(path+"project.do?code=list");
			} else {
				// 에러 페이지로 리다이렉트
				response.sendError(500);
			}

		}
		/**
		 * 수정완료 호출 시
		 */
		else if (code.equals("modify")) {

			pDao = new ProDAO();
			pDto = new ProDTO();
			
			pDto.setU_id(request.getParameter("u_id"));
			int p_id = 0;
			if(!request.getParameter("p_id").equals("")){
				p_id = Integer.parseInt(request.getParameter("p_id"));
				pDto.setP_id(p_id);
			}
			pDto.setP_type(request.getParameter("p_type"));
			pDto.setP_subject(request.getParameter("p_subject"));
			pDto.setP_contents(request.getParameter("p_contents"));
			pDto.setP_priority(request.getParameter("p_priority"));
			int p_upper = 0;
			if (!request.getParameter("p_upper").equals("")) {
				p_upper = Integer.parseInt(request.getParameter("p_upper"));
				pDto.setP_upper(p_upper);
			}
			pDto.setP_sdate(request.getParameter("p_sdate"));
			pDto.setP_edate(request.getParameter("p_edate"));

			int p_depth = 0;
			if (!request.getParameter("p_depth").equals("")) {
				p_depth = Integer.parseInt(request.getParameter("p_depth"));
			}
			pDto.setP_depth(p_depth);

			pDto.setP_progress(Integer.parseInt(request.getParameter("p_progress")));
			
			int chk = 0;
			
			chk = pDao.updateProject(pDto);
			
			if(chk == 1){
				response.sendRedirect(path+"/project.do?code=view&p_id="+pDto.getP_id());
			}else{
				response.sendError(500);
			}
			
		}
		/**
		 * 삭제 호출 시
		 */
		else if (code.equals("del")) {

			pDao = new ProDAO();
			int chk = pDao.removeProject(Integer.parseInt(request.getParameter("p_id")));
			if (chk == 1) {
				response.sendRedirect(path+"/project.do?code=list");
			} else {
				response.sendError(500);
			}
		}

	}

	private void moveForward(HttpServletRequest rq, HttpServletResponse rs, String url) throws IOException {
		
		RequestDispatcher rd = rq.getRequestDispatcher(url);
		try {
			rd.forward(rq, rs);
		} catch (ServletException | IOException e) {
			rs.sendError(404);
		}
	}

}
