/**
 * 
 */
package cn.ecust.bs.guuguu.domain;

/**
 * @author Hongtao Ren
 * email: hongtao.ren@gmail.com
 * created: 2013-6-25
 */
import java.io.Serializable;

import org.springframework.data.neo4j.annotation.GraphId;

@SuppressWarnings("serial")
public abstract class Entity implements Serializable {

	@GraphId
	Long id;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Entity node = (Entity) o;
		if (id == null)
			return super.equals(o);
		return id.equals(node.id);

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {

		return id != null ? id.hashCode() : super.hashCode();
	}

}