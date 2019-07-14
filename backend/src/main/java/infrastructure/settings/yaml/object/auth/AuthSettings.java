package infrastructure.settings.yaml.object.auth;

public class AuthSettings {
	private String host;
	private String clientId;
	private String clientSecret;
	private String callback;
	private String userInfoEndPoint;

	public String getHost() {
		return host;
	}
	public String getClientId() {
		return clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public String getCallback() {
		return callback;
	}
	public String getUserInfoEndPoint() {
		return userInfoEndPoint;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public void setUserInfoEndPoint(String userInfoEndPoint) {
		this.userInfoEndPoint = userInfoEndPoint;
	}
}
