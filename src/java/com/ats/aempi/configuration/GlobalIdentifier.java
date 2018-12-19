package com.ats.aempi.configuration;

import com.ats.aempi.model.BaseObject;
import com.ats.aempi.model.IdentifierDomainDict;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class GlobalIdentifier extends BaseObject
{
	private static final long serialVersionUID = -4959981332457798934L;

	private boolean assignGlobalIdentifier;
	private String namespaceIdentifier;
	private String universalIdentifier;
	private String universalIdentifierType;
	
	public boolean isAssignGlobalIdentifier() {
		return assignGlobalIdentifier;
	}

	public void setAssignGlobalIdentifier(boolean assignGlobalIdentifier) {
		this.assignGlobalIdentifier = assignGlobalIdentifier;
	}

	public String getNamespaceIdentifier() {
		return namespaceIdentifier;
	}

	public void setNamespaceIdentifier(String namespaceIdentifier) {
		this.namespaceIdentifier = namespaceIdentifier;
	}

	public String getUniversalIdentifier() {
		return universalIdentifier;
	}

	public void setUniversalIdentifier(String universalIdentifier) {
		this.universalIdentifier = universalIdentifier;
	}

	public String getUniversalIdentifierType() {
		return universalIdentifierType;
	}

	public void setUniversalIdentifierType(String universalIdentifierType) {
		this.universalIdentifierType = universalIdentifierType;
	}

	public IdentifierDomainDict getIdentifierDomain() {
		IdentifierDomainDict identifierDomain = new IdentifierDomainDict();
		identifierDomain.setNamespaceIdentifier(getNamespaceIdentifier());
		identifierDomain.setUniversalIdentifier(getUniversalIdentifier());
		identifierDomain.setUniversalIdentifierTypeCode(getUniversalIdentifierType());
		return identifierDomain;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof GlobalIdentifier))
			return false;
		GlobalIdentifier castOther = (GlobalIdentifier) other;
		return new EqualsBuilder().append(assignGlobalIdentifier,
				castOther.assignGlobalIdentifier).append(namespaceIdentifier,
				castOther.namespaceIdentifier).append(universalIdentifier,
				castOther.universalIdentifier).append(universalIdentifierType,
				castOther.universalIdentifierType).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(assignGlobalIdentifier).append(
				namespaceIdentifier).append(universalIdentifier).append(
				universalIdentifierType).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("assignGlobalIdentifier",
				assignGlobalIdentifier).append("namespaceIdentifier",
				namespaceIdentifier).append("universalIdentifier",
				universalIdentifier).append("universalIdentifierType",
				universalIdentifierType).toString();
	}

}
