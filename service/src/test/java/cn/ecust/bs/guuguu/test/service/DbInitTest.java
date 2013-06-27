/**
 * 
 */
package cn.ecust.bs.guuguu.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.node.Neo4jHelper;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Hongtao Ren email: hongtao.ren@gmail.com created: 2013-6-26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/service-test-context.xml" })
@Transactional
public class DbInitTest {

	@Autowired
	private GraphDatabaseService graphDatabaseService;

	@Rollback(false)
	@BeforeTransaction
	public void clearDatabase() {
		Neo4jHelper.cleanDb(graphDatabaseService);

	}

	@Test
	@Rollback(false)
	public void populateDatabase() {

	}

}