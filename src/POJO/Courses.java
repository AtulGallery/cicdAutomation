package POJO;

import java.util.List;

public class Courses {
	private List<WebAutomation> webAutomation;
	public List<WebAutomation> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<WebAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public List<api> getApi() {
		return api;
	}
	public void setApi(List<POJO.api> api) {
		this.api = api;
	}
	public List<mobile> getMobile() {
		return mobile;
	}
	public void setMobile(List<POJO.mobile> mobile) {
		this.mobile = mobile;
	}
	private List<api> api;
	private List<mobile> mobile;

}
