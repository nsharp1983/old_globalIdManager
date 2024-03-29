package com.ats.aempi.model;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Criteria extends BaseObject
{
	private static final long serialVersionUID = 7534673028743277151L;

	private List<Criterion> criteria;
	
	public Criteria() {
		criteria = new ArrayList<Criterion>();
	}
	
	public void addCriterion(Criterion criterion) {
		criteria.add(criterion);
	}

	public List<Criterion> getCriteria() {
		return criteria;
	}

	public void setCriteria(List<Criterion> criteria) {
		this.criteria = criteria;
	}
	
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Criteria))
			return false;
		Criteria castOther = (Criteria) other;
		return new EqualsBuilder().append(criteria, castOther.criteria).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(criteria).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("criteria", criteria).toString();
	}
}
