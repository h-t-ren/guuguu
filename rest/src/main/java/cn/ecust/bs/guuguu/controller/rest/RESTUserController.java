package cn.ecust.bs.guuguu.controller.rest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ecust.bs.guuguu.domain.User;
import cn.ecust.bs.guuguu.repo.UserRepository;
import cn.ecust.bs.guuguu.service.UserService;
import cn.ecust.bs.guuguu.ws.domain.GuuGuuUser;
import cn.ecust.bs.guuguu.ws.domain.Result;
import cn.ecust.bs.guuguu.ws.domain.Role;

@Controller
@Transactional(readOnly = true)
@RequestMapping("/rest/user")
public class RESTUserController {
	
	@Autowired private UserRepository userRepository;
	@Autowired private UserService userService;
	@RequestMapping(value="{login}/info", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody GuuGuuUser userInfo(@PathVariable("login") String login) {
		User user= userRepository.findByLogin(login);
		GuuGuuUser gu = new GuuGuuUser();
		gu.setName(user.getName());
		gu.setEmail(user.getEmail());
		gu.setMobile(user.getMobile());
		return gu;
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public @ResponseBody
	Result register(@RequestBody GuuGuuUser user) {
		 User usera =  userRepository.findByLogin(user.getEmail());
		 Result result =new Result();
		 //reg
		 if (usera == null) {
			 usera = new User();
			 usera.setCreated(new Date());
			 usera.setEmail(user.getEmail());
			 usera.setName(user.getName());
			 usera.setLogin(user.getEmail());
			 usera.setMobile(user.getMobile());
			 usera.setRoles(new Role[] { Role.ROLE_USER });
			 usera.setPassword(user.getPassword());
			 userService.register(usera);
			 result.setResult("OK");
			 return result;
			}
		 else
		 {
			 result.setResult("the user is already exist!");
			 return result;
		 }
	}
}
