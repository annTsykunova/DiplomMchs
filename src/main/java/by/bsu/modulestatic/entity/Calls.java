package by.bsu.modulestatic.entity;

import java.sql.Timestamp;

/**
 * Created by Anna on 08.12.2016.
 */
public class Calls {
    private int callsId;
    private Timestamp date;
    private int reasonId;
    private int vecclassId;
    private int regionId;
    private int count;

    public Calls() {
    }

    public Calls(int callsId, Timestamp date, int reasonId, int vecclassId, int regionId) {
        this.callsId = callsId;
        this.date = date;
        this.reasonId = reasonId;
        this.vecclassId = vecclassId;
        this.regionId = regionId;
    }

    public int getCallsId() {
        return callsId;
    }

    public void setCallsId(int callsId) {
        this.callsId = callsId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getReasonId() {
        return reasonId;
    }

    public void setReasonId(int reasonId) {
        this.reasonId = reasonId;
    }

    public int getVecclassId() {
        return vecclassId;
    }

    public void setVecclassId(int vecclassId) {
        this.vecclassId = vecclassId;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
