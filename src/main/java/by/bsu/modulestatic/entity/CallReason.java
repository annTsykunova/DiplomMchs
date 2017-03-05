package by.bsu.modulestatic.entity;

/**
 * Created by Anna on 03.12.2016.
 */
public class CallReason {
    private int reasonId;
    private int version;
    private String nameReason;

    public CallReason() {
    }

    public CallReason(int reasonId, int version, String nameReason) {
        this.reasonId = reasonId;
        this.version = version;
        this.nameReason = nameReason;
    }

    public int getReasonId() {
        return reasonId;
    }

    public void setReasonId(int reasonId) {
        this.reasonId = reasonId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getNameReason() {
        return nameReason;
    }

    public void setNameReason(String nameReason) {
        this.nameReason = nameReason;
    }
}
