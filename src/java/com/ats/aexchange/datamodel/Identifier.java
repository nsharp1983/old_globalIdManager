package com.ats.aexchange.datamodel;


/**
 * This class represents an ID assigning authority in an IHE affinity domain.
 *
 * @author yrh-2012-03-19
 */
public class Identifier {

    private String namespaceId = null;//机构名
    private String universalId = null;//机构域id
    private String universalIdType = null;

    private String universalKey = null;

    /**
     * 创建一机构识别,包含机构名,机构ID,机构类型
     *
     * @param namespace     机构名
     * @param universal     机构ID
     * @param universalType 机构类型
     */
    public Identifier(String namespace, String universal, String universalType) {
        if (namespace != null) {
            namespaceId = namespace.trim();
        }
        if (universal != null) {
            universalId = universal.trim();
        }
    
        if (universalType != null) {
            universalIdType = universalType.trim();
        }
        if (universalId != null) {
            universalKey = universalId;
            if (universalIdType != null) {
                universalKey = universalKey + "&" + universalIdType;
            }
        }
    }

    /**
     * Get the symbol namespace ID.
     *
     * @return The symbolic namespace ID
     */
    public String getNamespaceId() {
        return namespaceId;
    }

    /**
     * Set the symbol namespace ID.
     *
     * @param namespace The symbolic namespace ID
     */
    public void setNamespaceId(String namespace) {
        if (namespace != null) {
            this.namespaceId = namespace.trim();
        } else {
            this.namespaceId = null;
        }
    }

    /**
     * Get the universal ID for this assigning authority.
     *
     * @return The universal ID
     */
    public String getUniversalId() {
        return universalId;
    }

    /**
     * Set the universal ID for this assigning authority.
     *
     * @param universal The universal ID
     */
    public void setUniversalId(String universal) {
        if (universal != null) {
            universalId = universal.trim();
        } else {
            universalId = null;
        }

        if (universalId != null) {
            universalKey = universalId;
            if (universalIdType != null) {
                universalKey = universalKey + "&" + universalIdType;
            }
        }
    }

    /**
     * Get the type of the universal ID for this assigning authority.
     *
     * @return The universal ID type
     */
    public String getUniversalIdType() {
        return universalIdType;
    }

    /**
     * Set the type of the universal ID for this assigning authority.
     *
     * @param univeralIdType The universal ID type
     */
    public void setUniversalIdType(String universalIdType) {
        if (universalIdType != null) {
            this.universalIdType = universalIdType.trim();
        } else {
            this.universalIdType = null;
        }

        if (universalId != null) {
            universalKey = universalId;
            if (universalIdType != null) {
                universalKey = universalKey + "&" + universalIdType;
            }
        }
    }

    /**
     * Get a name for this assigning authority.  Used in debugging
     * and log messages.
     *
     * @return A human-readable name for this assigning authority
     */
    public String getAuthorityNameString() {
        StringBuffer sb = new StringBuffer();
        if (namespaceId != null) {
            sb.append(namespaceId);
        }
        if (universalId != null) {
            sb.append('&');
            sb.append(universalId);
            if (universalIdType != null) {
                sb.append('&');
                sb.append(universalIdType);
            }
        }
        return sb.toString();
    }

    /**
     * See if two identifiers represent the same assigning authority.
     */
    public boolean equals(Object other) {
        if (other instanceof Identifier) {
            Identifier id = (Identifier) other;
            if ((id.getNamespaceId() != null) && (namespaceId != null)) {
                return namespaceId.equalsIgnoreCase(id.getNamespaceId());
            }
            if ((id.getUniversalId() != null) && (id.getUniversalIdType() != null)) {
                if ((universalId != null) && (universalIdType != null)) {
                    return (universalId.equalsIgnoreCase(id.getUniversalId()) &&
                            universalIdType.equalsIgnoreCase(id.getUniversalIdType()));
                }
            }
        }
        return false;
    }

    /**
     * Calcualte the hashCode of this Identifier object
     *
     * @return The hashCode
     */
    public int hashCode() {
        int result = 17;
        if (namespaceId != null) {
            result = 37 * result + namespaceId.hashCode();
        }
        if (universalId != null) {
            result = 37 * result + universalId.hashCode();
        }
        if (universalIdType != null) {
            result = 37 * result + universalIdType.hashCode();
        }

        return result;
    }

}
