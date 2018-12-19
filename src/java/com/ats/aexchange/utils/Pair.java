package com.ats.aexchange.utils;

import java.io.Serializable;

/**
 * A utility container class used to group a pair of related objects.
 *
 * @author <a href="mailto:wenzhi.li@misys.com">Wenzhi Li</a>
 */

public final class Pair<A,B> implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public A first = null;
    public B second = null;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    /* (non-Javadoc)
      * @see java.lang.Object#hashCode()
      */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((first == null) ? 0 : first.hashCode());
        result = prime * result + ((second == null) ? 0 : second.hashCode());
        return result;
    }

    /* (non-Javadoc)
      * @see java.lang.Object#equals(java.lang.Object)
      */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair other = (Pair) obj;
        if (this.first == null) {
            if (other.first != null) {
                return false;
            }
        } else if (!this.first.equals(other.first)) {
            return false;
        }
        if (this.second == null) {
            if (other.second != null) {
                return false;
            }
        } else if (!this.second.equals(other.second)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString () {
        return String.format("%s,%s", first, second);
    }    
}
