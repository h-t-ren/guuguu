package cn.ecust.bs.guuguu.andriod;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

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
	private String username="hongtao.ren@gmail.com";
	private String password="1977921";
	//如果QQ登陆 username应该是OpenId, password 是accesstoken, 每次QQ登陆成功之后需要更新password
	
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
				user = getUserFromView();
               // user = getRestTemplate().getForObject(URLS.userInfoURL, GuuGuuUser.class,user.getEmail());
                ResponseEntity<GuuGuuUser> response = getRestTemplate().exchange(URLS.userInfoURL, HttpMethod.GET, new HttpEntity<Object>(getHeaders(username,password)), GuuGuuUser.class,user.getEmail());
                return response.getBody();
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
              //  Result result = getRestTemplate().postForObject(URLS.userRegisterURL, user, 
               //		Result.class);
				HttpEntity<GuuGuuUser> requestEntity = new HttpEntity<GuuGuuUser>(user, getHeaders(username,password));
                ResponseEntity<Result> response = getRestTemplate().exchange(URLS.userRegisterURL, HttpMethod.POST, requestEntity,Result.class);
				return response.getBody();
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

