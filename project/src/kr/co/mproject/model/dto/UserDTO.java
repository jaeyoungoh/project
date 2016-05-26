package kr.co.mproject.model.dto;

public class UserDTO {
	
	private String u_id;
	private String u_pw;
	private String u_email;
	private String u_name;
	private String AUTH_YN;
	
	
	
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getU_email() {
		return u_email;
	}
	public void setU_email(String u_email) {
		this.u_email = u_email;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getU_pw() {
		return u_pw;
	}
	public void setU_pw(String u_pw) {
		this.u_pw = u_pw;
	}
	public String getAUTH_YN() {
		return AUTH_YN;
	}
	public void setAUTH_YN(String aUTH_YN) {
		AUTH_YN = aUTH_YN;
	}
	
	

}
