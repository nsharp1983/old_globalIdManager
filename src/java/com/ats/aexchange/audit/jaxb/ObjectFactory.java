package com.ats.aexchange.audit.jaxb;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ats.aexchange.audit.jaxb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ats.aexchange.audit.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AuditMessage }
     * 
     */
    public AuditMessage createAuditMessage() {
        return new AuditMessage();
    }

    /**
     * Create an instance of {@link ParticipantObjectIdentificationType }
     * 
     */
    public ParticipantObjectIdentificationType createParticipantObjectIdentificationType() {
        return new ParticipantObjectIdentificationType();
    }

    /**
     * Create an instance of {@link AuditSourceIdentificationType }
     * 
     */
    public AuditSourceIdentificationType createAuditSourceIdentificationType() {
        return new AuditSourceIdentificationType();
    }

    /**
     * Create an instance of {@link EventIdentificationType }
     * 
     */
    public EventIdentificationType createEventIdentificationType() {
        return new EventIdentificationType();
    }

    /**
     * Create an instance of {@link AuditMessage.ActiveParticipant }
     * 
     */
    public AuditMessage.ActiveParticipant createAuditMessageActiveParticipant() {
        return new AuditMessage.ActiveParticipant();
    }

    /**
     * Create an instance of {@link TypeValuePairType }
     * 
     */
    public TypeValuePairType createTypeValuePairType() {
        return new TypeValuePairType();
    }

    /**
     * Create an instance of {@link ActiveParticipantType }
     * 
     */
    public ActiveParticipantType createActiveParticipantType() {
        return new ActiveParticipantType();
    }

    /**
     * Create an instance of {@link CodedValueType }
     * 
     */
    public CodedValueType createCodedValueType() {
        return new CodedValueType();
    }

    /**
     * Create an instance of {@link ParticipantObjectIdentificationType.ParticipantObjectIDTypeCode }
     * 
     */
    public ParticipantObjectIdentificationType.ParticipantObjectIDTypeCode createParticipantObjectIdentificationTypeParticipantObjectIDTypeCode() {
        return new ParticipantObjectIdentificationType.ParticipantObjectIDTypeCode();
    }

    /**
     * Create an instance of {@link AuditSourceIdentificationType.AuditSourceTypeCode }
     * 
     */
    public AuditSourceIdentificationType.AuditSourceTypeCode createAuditSourceIdentificationTypeAuditSourceTypeCode() {
        return new AuditSourceIdentificationType.AuditSourceTypeCode();
    }

}
