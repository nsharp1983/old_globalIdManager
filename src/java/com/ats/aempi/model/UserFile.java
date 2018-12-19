package com.ats.aempi.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * UserFile entity. 
 * @author yrh 2012-03-09
 */
@Entity
@Table(name = "user_file")
@SequenceGenerator(name="user_file_seq", sequenceName="user_file_seq")
public class UserFile extends BaseObject implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 6170253797278224912L;;//待修改,暂时未知,2012-03-08
	
	private Integer userFileId;
	private AppUser appUser;
	private String name;
	private String filename;
	private String importedInd;
	private Date dateCreated;
	private String custom1;
	private String custom2;
	private String custom3;
	private String custom4;
	private String custom5;

	// Constructors

	/** default constructor */
	public UserFile() {
	}

	/** minimal constructor */
	public UserFile(String name,String filename) {
		this.name = name;
		this.filename = filename;
		this.importedInd="N";

	}

	/** full constructor */
	public UserFile(Integer userFileId, AppUser appUser, String name,
			String filename, String importedInd, Date dateCreated,
			String custom1, String custom2, String custom3, String custom4,
			String custom5) {
		this.userFileId = userFileId;
		this.appUser = appUser;
		this.name = name;
		this.filename = filename;
		this.importedInd = importedInd;
		this.dateCreated = dateCreated;
		this.custom1 = custom1;
		this.custom2 = custom2;
		this.custom3 = custom3;
		this.custom4 = custom4;
		this.custom5 = custom5;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_file_seq") 
	@Column(name = "user_file_id", unique = true, nullable = false)
	public Integer getUserFileId() {
		return this.userFileId;
	}

	public void setUserFileId(Integer userFileId) {
		this.userFileId = userFileId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "filename", nullable = false)
	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "imported_ind", nullable = false, length = 1)
	public String getImportedInd() {
		return this.importedInd;
	}

	public void setImportedInd(String importedInd) {
		this.importedInd = importedInd;
	}

	@Column(name = "date_created", nullable = false, length = 8)
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
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
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof UserFile))
			return false;
		UserFile castOther = (UserFile) other;
		return new EqualsBuilder()
		.append(userFileId, castOther.userFileId)
		.append(filename,castOther.filename)
		.append(importedInd, castOther.importedInd)
		.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(userFileId).append(filename).append(importedInd).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("userFileId", userFileId)
		.append("filename",filename)
		.append("importedInd",importedInd)
		.toString();
	}
}
