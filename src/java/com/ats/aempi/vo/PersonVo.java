package com.ats.aempi.vo;

import com.ats.aempi.model.NewPerson;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonVo implements java.io.Serializable {

// Fields
private static final long serialVersionUID = -6061320465621019356L;
    private long personId;
    private String name;
    private String givenName;
    private String identityNo;
    private String dateOfBirth;
    private String genderName;
    private String empi;
    private String custom16;

    public PersonVo(){

    }

    public PersonVo(NewPerson newPerson) {
        this.personId = newPerson.getPersonId();
        this.name = newPerson.getName();
        this.givenName = newPerson.getGivenName();
        this.identityNo = newPerson.getIdentityNo();
        if (newPerson.getDateOfBirth() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            this.dateOfBirth = sdf.format(newPerson.getDateOfBirth());
        }
        this.genderName =newPerson.getGenderName();
        this.empi = newPerson.getEmpi();
        this.custom16 = newPerson.getCustom16();
    }


    public String getCustom16() {
        return custom16;
    }

    public void setCustom16(String custom16) {
        this.custom16 = custom16;
    }

    public String getEmpi() {
        return empi;
    }

    public void setEmpi(String empi) {
        this.empi = empi;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }
}
