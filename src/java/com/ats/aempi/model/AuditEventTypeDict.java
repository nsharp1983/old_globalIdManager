package com.ats.aempi.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * AddressTypeDict entity. 
 * @author yrh-2012-03-06
 */
@Entity
@Table(name = "audit_event_type_dict", schema = "ATS_DICT")
public class AuditEventTypeDict extends BaseObject implements java.io.Serializable {

	// Fields
    private static final long serialVersionUID = 6170253797278224912L;
	
	public static final String ADD_PERSON_EVENT_TYPE = "ADD12345";
	public static final String DELETE_PERSON_EVENT_TYPE = "DEL";
	public static final String IMPORT_PERSON_EVENT_TYPE = "IMP";
	
	public static final String UNMERGE_PERSON_EVENT_TYPE = "UMG";
	public static final String UPDATE_PERSON_EVENT_TYPE = "UPD";
	public static final String ADD_IDENTIFIER_DOMAIN_ATTRIBUTE_EVENT_TYPE = "AIA";
	public static final String OBTAIN_UNIQUE_IDENTIFIER_DOMAIN_EVENT_TYPE = "OID";
	public static final String DELETE_IDENTIFIER_DOMAIN_ATTRIBUTE_EVENT_TYPE = "DIA";
	public static final String UPDATE_IDENTIFIER_DOMAIN_ATTRIBUTE_EVENT_TYPE = "UID";
	
	public static final String ADD_NEW_PERSON_NO_PV1_TYPE = "800001";
	
	public static final String ADD_NEW_PERSON_TYPE = "800002";
	
	public static final String ADD_OLD_PERSON_HAVE_PV1_TYPE = "800003";
	
	public static final String MODFIY_PERSON_NO_PV1_TYPE = "800004";
	
	public static final String MODFIY_PERSON_TYPE = "800005";
	
	public static final String MERGE_PERSON_EVENT_TYPE = "800006";
	
	public static final String MODFIY_PERSON_PV1_TYPE = "800007";
	
	public static final String CANCEL_PERSON_PV1_TYPE = "800008";
	
	public static final String DELETE_PERSON_RECORD_TYPE = "800009";
	
	public static final String MOIFIY_PERSON_ID_TYPE = "800010";
	
	private Integer auditEventTypeCd;
	private String auditEventTypeName;
	private String auditEventTypeDescription;
	private String auditEventTypeCode;
	private String createName;
	private Date createDate;
	private String comments;
	private String custom1;
	private String custom2;
	private String custom3;
	private String custom4;
	private String custom5;
	private Set<AuditEvent> auditEvents = new HashSet<AuditEvent>(0);

	// Constructors

	/** default constructor */
	public AuditEventTypeDict() {
	}

	/** minimal constructor */
	public AuditEventTypeDict(Integer auditEventTypeCd,
			String auditEventTypeName, String auditEventTypeCode) {
		this.auditEventTypeCd = auditEventTypeCd;
		this.auditEventTypeName = auditEventTypeName;
		this.auditEventTypeCode = auditEventTypeCode;
	}

	/** full constructor */
	public AuditEventTypeDict(Integer auditEventTypeCd,
			String auditEventTypeName, String auditEventTypeDescription,
			String auditEventTypeCode, String createName, Date createDate,
			String comments, String custom1, String custom2, String custom3,
			String custom4, String custom5, Set<AuditEvent> auditEvents) {
		this.auditEventTypeCd = auditEventTypeCd;
		this.auditEventTypeName = auditEventTypeName;
		this.auditEventTypeDescription = auditEventTypeDescription;
		this.auditEventTypeCode = auditEventTypeCode;
		this.createName = createName;
		this.createDate = createDate;
		this.comments = comments;
		this.custom1 = custom1;
		this.custom2 = custom2;
		this.custom3 = custom3;
		this.custom4 = custom4;
		this.custom5 = custom5;
		this.auditEvents = auditEvents;
	}

	// Property accessors
	@Id
	@Column(name = "audit_event_type_cd", unique = true, nullable = false)
	public Integer getAuditEventTypeCd() {
		return this.auditEventTypeCd;
	}

	public void setAuditEventTypeCd(Integer auditEventTypeCd) {
		this.auditEventTypeCd = auditEventTypeCd;
	}

	@Column(name = "audit_event_type_name", nullable = false, length = 64)
	public String getAuditEventTypeName() {
		return this.auditEventTypeName;
	}

	public void setAuditEventTypeName(String auditEventTypeName) {
		this.auditEventTypeName = auditEventTypeName;
	}

	@Column(name = "audit_event_type_description")
	public String getAuditEventTypeDescription() {
		return this.auditEventTypeDescription;
	}

	public void setAuditEventTypeDescription(String auditEventTypeDescription) {
		this.auditEventTypeDescription = auditEventTypeDescription;
	}

	@Column(name = "audit_event_type_code", nullable = false, length = 64)
	public String getAuditEventTypeCode() {
		return this.auditEventTypeCode;
	}

	public void setAuditEventTypeCode(String auditEventTypeCode) {
		this.auditEventTypeCode = auditEventTypeCode;
	}

	@Column(name = "create_name", length = 64)
	public String getCreateName() {
		return this.createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	@Column(name = "create_date", length = 8)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "comments")
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "custom1")
	public String getCustom1() {
		return this.custom1;
	}

	public void setCustom1(String custom1) {
		this.custom1 = custom1;
	}

	@Column(name = "custom2")
	public String getCustom2() {
		return this.custom2;
	}

	public void setCustom2(String custom2) {
		this.custom2 = custom2;
	}

	@Column(name = "custom3")
	public String getCustom3() {
		return this.custom3;
	}

	public void setCustom3(String custom3) {
		this.custom3 = custom3;
	}

	@Column(name = "custom4")
	public String getCustom4() {
		return this.custom4;
	}

	public void setCustom4(String custom4) {
		this.custom4 = custom4;
	}

	@Column(name = "custom5")
	public String getCustom5() {
		return this.custom5;
	}

	public void setCustom5(String custom5) {
		this.custom5 = custom5;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "auditEventTypeDict")
	public Set<AuditEvent> getAuditEvents() {
		return this.auditEvents;
	}

	public void setAuditEvents(Set<AuditEvent> auditEvents) {
		this.auditEvents = auditEvents;
	}
	
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof AuditEventTypeDict))
			return false;
		AuditEventTypeDict castOther = (AuditEventTypeDict) other;
		return new EqualsBuilder().append(auditEventTypeCd, castOther.auditEventTypeCd)
				.append(auditEventTypeName, castOther.auditEventTypeName)
				.append(auditEventTypeDescription, castOther.auditEventTypeDescription)
				.append(auditEventTypeCode, castOther.auditEventTypeCode).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(auditEventTypeCd).append(auditEventTypeName).append(auditEventTypeDescription).append(
				auditEventTypeCode).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("auditEventTypeCd", auditEventTypeCd).append("auditEventTypeName", auditEventTypeName).append(
				"auditEventTypeDescription", auditEventTypeDescription).append("auditEventTypeCode", auditEventTypeCode).toString();
	}

}
