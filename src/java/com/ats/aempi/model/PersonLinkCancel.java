package com.ats.aempi.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * PersonLinkCancel entity. 
 * @author yrh 2012-03-09
 */
@Entity
@Table(name = "Person_link_cancel")
@SequenceGenerator(name="PERSON_LINK_CANCEL_SEQ", sequenceName="PERSON_LINK_CANCEL_SEQ")
public class PersonLinkCancel extends BaseObject implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 0;//待修改,暂时未知,2012-03-08
	
	private Long PersonLinkCancelId;
	private String empi;
	private Long personId;
	private Date dateCreated;



	// Constructors

	/** default constructor */
	public PersonLinkCancel() {
	}

	/** minimal constructor */
	public PersonLinkCancel(Long PersonLinkCancelId,
			String empi, Long personId,
			Date dateCreated) {
		this.PersonLinkCancelId = PersonLinkCancelId;
		this.empi = empi;
		this.personId = personId;
		this.dateCreated = dateCreated;
	}



	// Property accessors
	@Id
	@Column(name = "Person_link_Cancel_id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERSON_LINK_CANCEL_SEQ")
	public Long getPersonLinkCancelId() {
		return this.PersonLinkCancelId;
	}

	public void setPersonLinkCancelId(Long PersonLinkCancelId) {
		this.PersonLinkCancelId = PersonLinkCancelId;
	}

	@Column(name = "EMPI")
	public String getEmpi(){
		return this.empi;
	}
	
	public void setEmpi(String empi){
		this.empi = empi;
	}
	
	@Column(name = "PERSON_ID")
	public Long getPersonId(){
		return this.personId;
	}
	
	public void setPersonId(Long personId){
		this.personId = personId;
	}
	
	@Column(name = "DATE_CREATED")
	public Date getDateCreated(){
		return this.dateCreated;
	}
	
	public void setDateCreated(Date dateCreated){
		this.dateCreated = dateCreated;
	}
	
	

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof PersonLinkCancel))
			return false;
		PersonLinkCancel castOther = (PersonLinkCancel) other;
		return new EqualsBuilder()
		.append(PersonLinkCancelId, castOther.PersonLinkCancelId)
		.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(PersonLinkCancelId).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("PersonLinkCancelId", PersonLinkCancelId)
		.toString();
	}
}
