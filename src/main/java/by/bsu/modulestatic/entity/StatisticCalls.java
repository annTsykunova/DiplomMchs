package by.bsu.modulestatic.entity;


import java.sql.Timestamp;

public class StatisticCalls {
    private int callsId;
    private Timestamp date;
    private String regionName;
    private String vechicleName;
    private String reasonName;
    private int count;

    public StatisticCalls() {
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getCallsId() {
        return callsId;
    }

    public void setCallsId(int callsId) {
        this.callsId = callsId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getVechicleName() {
        return vechicleName;
    }

    public void setVechicleName(String vechicleName) {
        this.vechicleName = vechicleName;
    }

    public String getReasonName() {
        return reasonName;
    }

    public void setReasonName(String reasonName) {
        this.reasonName = reasonName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
