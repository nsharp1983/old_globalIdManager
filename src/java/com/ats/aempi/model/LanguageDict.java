package com.ats.aempi.model;

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
 * LanguageDict entity. 
 * @author yrh 2012-03-09
 */
@Entity
@Table(name = "language_dict", schema = "ATS_DICT")
public class LanguageDict extends BaseObject implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 0;//待修改,暂时未知,2012-03-08
	
	private Integer languageCd;
	private String languageName;
	private String languageDescription;
	private String languageCode;
	private String comments;
	private String custom1;
	private String custom2;
	private String custom3;
	private String custom4;
	private String custom5;
	private Set<Person> persons = new HashSet<Person>(0);

	// Constructors

	/** default constructor */
	public LanguageDict() {
	}

	/** minimal constructor */
	public LanguageDict(Integer languageCd, String languageName,
			String languageCode) {
		this.languageCd = languageCd;
		this.languageName = languageName;
		this.languageCode = languageCode;
	}

	/** full constructor */
	public LanguageDict(Integer languageCd, String languageName,
			String languageDescription, String languageCode, String comments,
			String custom1, String custom2, String custom3, String custom4,
			String custom5, Set<Person> persons) {
		this.languageCd = languageCd;
		this.languageName = languageName;
		this.languageDescription = languageDescription;
		this.languageCode = languageCode;
		this.comments = comments;
		this.custom1 = custom1;
		this.custom2 = custom2;
		this.custom3 = custom3;
		this.custom4 = custom4;
		this.custom5 = custom5;
		this.persons = persons;
	}

	// Property accessors
	@Id
	@Column(name = "language_cd", unique = true, nullable = false)
	public Integer getLanguageCd() {
		return this.languageCd;
	}

	public void setLanguageCd(Integer languageCd) {
		this.languageCd = languageCd;
	}

	@Column(name = "language_name", nullable = false, length = 64)
	public String getLanguageName() {
		return this.languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	@Column(name = "language_description")
	public String getLanguageDescription() {
		return this.languageDescription;
	}

	public void setLanguageDescription(String languageDescription) {
		this.languageDescription = languageDescription;
	}

	@Column(name = "language_code", nullable = false, length = 64)
	public String getLanguageCode() {
		return this.languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "languageDict")
	public Set<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}
	
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof LanguageDict))
			return false;
		LanguageDict castOther = (LanguageDict) other;
		return new EqualsBuilder()
		.append(languageCd, castOther.languageCd)
		.append(languageName,castOther.languageName)
		.append(languageCode, castOther.languageCode)
		.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(languageCd).append(languageName).append(languageCode).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("languageCd", languageCd)
		.append("languageName",languageName)
		.append("languageCode",languageCode)
		.toString();
	}

}
