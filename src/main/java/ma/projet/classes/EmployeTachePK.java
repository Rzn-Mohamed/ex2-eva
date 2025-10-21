package ma.projet.classes;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EmployeTachePK implements Serializable {
    
    private int employeId;
    private int tacheId;

    public EmployeTachePK() {
    }

    public EmployeTachePK(int employeId, int tacheId) {
        this.employeId = employeId;
        this.tacheId = tacheId;
    }

    public int getEmployeId() {
        return employeId;
    }

    public void setEmployeId(int employeId) {
        this.employeId = employeId;
    }

    public int getTacheId() {
        return tacheId;
    }

    public void setTacheId(int tacheId) {
        this.tacheId = tacheId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeTachePK)) return false;
        EmployeTachePK that = (EmployeTachePK) o;
        return employeId == that.employeId && tacheId == that.tacheId;
    }

    @Override
    public int hashCode() {
        return 31 * employeId + tacheId;
    }
}
