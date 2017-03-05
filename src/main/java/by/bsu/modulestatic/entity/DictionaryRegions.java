package by.bsu.modulestatic.entity;

/**
 * Created by Anna on 03.12.2016.
 */
public class DictionaryRegions {
    private int regionId;
    private int regionCode;
    private String regionName;

    public DictionaryRegions() {

    }

    public DictionaryRegions(int regionId, int regionCode, String regionName) {
        this.regionId = regionId;
        this.regionCode = regionCode;
        this.regionName = regionName;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public int getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(int regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}