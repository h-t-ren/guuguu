package cn.ecust.bs.guuguu.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import cn.ecust.bs.guuguu.domain.User;

/**
 * @author Hongtao Ren
 * email: hongtao.ren@gmail.com
 * created: 2013-6-25
 */
public interface UserRepository  extends GraphRepository<User>{	
	public User findByLogin(String login);
}
