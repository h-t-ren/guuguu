package cn.ecust.bs.guuguu.repo;

import org.springframework.data.neo4j.repository.GraphRepository;
import cn.ecust.bs.guuguu.domain.MeetingTime;


/**
 * @author Hongtao Ren
 * email: hongtao.ren@gmail.com
 * created: 2013-6-25
 */
public interface MeetingTimeRepository  extends GraphRepository<MeetingTime>{	
}
