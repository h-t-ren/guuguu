package cn.ecust.bs.guuguu.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.ecust.bs.guuguu.domain.User;
import cn.ecust.bs.guuguu.repo.UserRepository;

@Controller
@Transactional(readOnly = true)
@RequestMapping("/rest/user")
public class RESTUserController {
	
	@Autowired private UserRepository userRepository;

	@RequestMapping(value="{login}/info", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody User userInfo(@PathVariable("login") String login) {
		return userRepository.findByLogin(login);
	}
	
}
