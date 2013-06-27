package cn.ecust.bs.guuguu.repo;

import org.springframework.data.neo4j.repository.GraphRepository;
import cn.ecust.bs.guuguu.domain.Meeting;


/**
 * @author Hongtao Ren
 * email: hongtao.ren@gmail.com
 * created: 2013-6-25
 */
public interface MeetingRepository  extends GraphRepository<Meeting>{	
}
