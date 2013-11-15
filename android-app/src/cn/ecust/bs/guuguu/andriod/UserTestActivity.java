package cn.ecust.bs.guuguu.andriod;

import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

import org.springframework.web.client.RestTemplate;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import cn.ecust.bs.guuguu.ws.domain.GuuGuuUser;

public class UserTestActivity extends Activity {

	private GuuGuuUser user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_test);
        
        final Button obtainButton = (Button) findViewById(R.id.obtain);
        obtainButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                	user=getUserFromView();
                	RestTemplate restTemplate = new RestTemplate(true);
                    restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                    user = restTemplate.getForObject("http://219.220.221.183:8080/guuguu-rest/rest/user/{email}/info", GuuGuuUser.class,user.getEmail());
                    ((EditText)findViewById(R.id.userName)).setText(user.getUserName()+111);
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
}

