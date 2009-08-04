package menu;

import com.google.apphosting.api.ApiProxy;

public class TestEnvironment implements ApiProxy.Environment {
	public String getAppId() {
		return "Unit Tests";
	}

	public String getVersionId() {
		return "1.0";
	}

	public void setDefaultNamespace(String s) {
	}

	public String getRequestNamespace() {
		return null;
	}

	public String getDefaultNamespace() {
		return null;
	}

	public String getAuthDomain() {
		return "gmail.com";
	}

	public boolean isLoggedIn() {
		return true;
	}

	public String getEmail() {
		return "testuser@gmail.com";
	}

	public boolean isAdmin() {
		return false;
	}
}
