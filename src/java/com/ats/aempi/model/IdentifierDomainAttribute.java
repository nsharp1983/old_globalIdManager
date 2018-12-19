package com.ats.aempi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * IdentifierDomainAttribute entity.
 * 
 * @author <a href="mailto:yimin.xie@sysnetint.com">Yimin Xie</a>
 */
@Entity
@Table(name = "identifier_domain_attribute")
@SequenceGenerator(name="identifier_attribute_seq", sequenceName="identifier_attribute_seq")
public class IdentifierDomainAttribute implements Serializable
{
	private static final long serialVersionUID = 2611151383140968220L;

	private Integer identifierDomainAttributeId;
	private Integer identifierDomainId;
	private String attributeName;
	private String attributeValue;
	
	/** default constructor */
	public IdentifierDomainAttribute() {
	}

	/** minimal constructor */
	public IdentifierDomainAttribute(Integer identifierDomainAttributeId, Integer identifierDomainId) {
		this.identifierDomainAttributeId = identifierDomainAttributeId;
		this.identifierDomainId = identifierDomainId;
	}

	/** full constructor */
	public IdentifierDomainAttribute(Integer identifierDomainId, String attributeName, String attributeValue) {
		this.identifierDomainId = identifierDomainId;
		this.attributeName = attributeName;
		this.attributeValue = attributeValue;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="identifier_attribute_seq") 	
	@Column(name = "identifier_domain_attribute_id", unique = true, nullable = false)
	public Integer getIdentifierDomainAttributeId() {
		return identifierDomainAttributeId;
	}

	public void setIdentifierDomainAttributeId(Integer identifierDomainAttributeId) {
		this.identifierDomainAttributeId = identifierDomainAttributeId;
	}

	@Column(name = "identifier_domain_id", nullable = false)
	public Integer getIdentifierDomainId() {
		return identifierDomainId;
	}

	public void setIdentifierDomainId(Integer identifierDomainId) {
		this.identifierDomainId = identifierDomainId;
	}

	@Column(name = "attribute_name")
	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	@Column(name = "attribute_value")
	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof IdentifierDomainAttribute))
			return false;
		IdentifierDomainAttribute castOther = (IdentifierDomainAttribute) other;
		return new EqualsBuilder().append(identifierDomainAttributeId,
				castOther.identifierDomainAttributeId).append(
				identifierDomainId, castOther.identifierDomainId).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(identifierDomainAttributeId)
				.append(identifierDomainId).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("identifierDomainAttributeId",
				identifierDomainAttributeId).append("identifierDomainId",
				identifierDomainId).append("attributeName", attributeName)
				.append("attributeValue", attributeValue).toString();
	}	
}
