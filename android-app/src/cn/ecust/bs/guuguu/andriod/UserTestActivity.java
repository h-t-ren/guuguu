package cn.ecust.bs.guuguu.andriod;

import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import cn.ecust.bs.guuguu.ws.domain.GuuGuuUser;
import cn.ecust.bs.guuguu.ws.domain.Result;

public class UserTestActivity extends AbstractAsyncActivity {

	private GuuGuuUser user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_test);
        
        final Button obtainButton = (Button) findViewById(R.id.obtain);
        obtainButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                	new GuuGuuUserInfoTask().execute();	
                }
        });
        
        final Button registerButton = (Button) findViewById(R.id.register);
        registerButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                	new GuuGuuUserRegisterTask().execute();	
                }
        });
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	getMenuInflater().inflate(cn.ecust.bs.guuguu.andriod.R.menu.main, menu);
	return true;
    }

    
    private GuuGuuUser getUserFromView()
    {
    	 user = new GuuGuuUser();
    	 String mobile =((EditText)findViewById(R.id.mobile)).getText().toString().trim();
    	 String userName =((EditText)findViewById(R.id.userName)).getText().toString().trim();
    	 String password =((EditText)findViewById(R.id.passWord)).getText().toString().trim();
    	 String email =((EditText)findViewById(R.id.email)).getText().toString().trim();
    	 user.setEmail(email);
    	 user.setMobile(mobile);
    	 user.setPassword(password);
    	 user.setUserName(userName);
    	 return user;
    }
    
    private void refreshResults(GuuGuuUser user)
    {
    	((EditText)findViewById(R.id.userName)).setText(user.getUserName());
    	((EditText)findViewById(R.id.email)).setText(user.getEmail());
    	((EditText)findViewById(R.id.mobile)).setText(user.getMobile());
    }
    

	private class GuuGuuUserInfoTask extends AsyncTask<Void, Void, GuuGuuUser> {

		@Override
		protected GuuGuuUser doInBackground(Void... params) {
			try {
				user=getUserFromView();
				RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                user = restTemplate.getForObject(URLS.userInfoURL, GuuGuuUser.class,user.getEmail());
				return user;
			} catch (Exception e) {
				Log.e(TAG, e.getMessage(), e);
			}

			return null;
		}
		@Override
		protected void onPostExecute(GuuGuuUser user) {
			dismissProgressDialog();
			refreshResults(user);
		}


	}

	
	private class GuuGuuUserRegisterTask extends AsyncTask<Void, Void, Result> {

		@Override
		protected Result doInBackground(Void... params) {
			try {
				user=getUserFromView();
				RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                Result result =restTemplate.postForObject(URLS.userRegisterURL, user, 
                		Result.class);
				return result;
			} catch (Exception e) {
				Log.e(TAG, e.getMessage(), e);
			}

			return null;
		}
		@Override
		protected void onPostExecute(Result result) {
			dismissProgressDialog();
		}


	}
}

