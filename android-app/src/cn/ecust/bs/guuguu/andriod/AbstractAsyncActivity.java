package cn.ecust.bs.guuguu.andriod;

import org.springframework.http.ContentCodingType;
import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.app.Activity;
import android.app.ProgressDialog;

public abstract class AbstractAsyncActivity extends Activity implements
		AsyncActivity {

	protected static final String TAG = AbstractAsyncActivity.class
			.getSimpleName();

	private ProgressDialog progressDialog;

	private boolean destroyed = false;

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.destroyed = true;
	}

	public void showLoadingProgressDialog() {
		this.showProgressDialog("Loading. Please wait...");
	}

	public void showProgressDialog(CharSequence message) {
		if (this.progressDialog == null) {
			this.progressDialog = new ProgressDialog(this);
			this.progressDialog.setIndeterminate(true);
		}

		this.progressDialog.setMessage(message);
		this.progressDialog.show();
	}

	public void dismissProgressDialog() {
		if (this.progressDialog != null && !this.destroyed) {
			this.progressDialog.dismiss();
		}
	}

	
	public HttpHeaders getHeaders(String username, String password)
	{
		HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAcceptEncoding(ContentCodingType.IDENTITY);
		requestHeaders.setAuthorization(authHeader);
		//requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return requestHeaders;
	}
	public RestTemplate getRestTemplate() {
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(
				new MappingJacksonHttpMessageConverter());
		return restTemplate;
	}
}
