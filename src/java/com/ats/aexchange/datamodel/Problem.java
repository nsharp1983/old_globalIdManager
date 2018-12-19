package com.ats.aexchange.datamodel;

/**
 *
 * @author : Shantanu Paul
 */
public class Problem {
    private String  probName;
    private String  probCode;
    private String  probCodeSystem;
    private String  probCodeVersion;

    public String getProbName() {
        return probName;
    }

    public void setProbName(String probName) {
        this.probName = probName;
    }

    public String getProbCode() {
        return probCode;
    }

    public void setProbCode(String probCode) {
        this.probCode = probCode;
    }

    public String getProbCodeSystem() {
        return probCodeSystem;
    }

    public void setProbCodeSystem(String probCodeSystem) {
        this.probCodeSystem = probCodeSystem;
    }

    public String getProbCodeVersion() {
        return probCodeVersion;
    }

    public void setProbCodeVersion(String probCodeVersion) {
        this.probCodeVersion = probCodeVersion;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Problem)) return false;

        final Problem problem = (Problem) o;

        if (probCode != null ? !probCode.equals(problem.probCode) : problem.probCode != null) return false;
        if (probCodeSystem != null ? !probCodeSystem.equals(problem.probCodeSystem) : problem.probCodeSystem != null) return false;
        if (probCodeVersion != null ? !probCodeVersion.equals(problem.probCodeVersion) : problem.probCodeVersion != null) return false;
        if (probName != null ? !probName.equals(problem.probName) : problem.probName != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (probName != null ? probName.hashCode() : 0);
        result = 29 * result + (probCode != null ? probCode.hashCode() : 0);
        result = 29 * result + (probCodeSystem != null ? probCodeSystem.hashCode() : 0);
        result = 29 * result + (probCodeVersion != null ? probCodeVersion.hashCode() : 0);
        return result;
    }
}
