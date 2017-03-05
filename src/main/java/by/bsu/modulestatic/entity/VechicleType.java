package by.bsu.modulestatic.entity;

/**
 * Created by Anna on 03.12.2016.
 */
public class VechicleType {
    private int versionId;
    private int version;
    private String nameType;

    public VechicleType() {

    }

    public VechicleType(int versionId, int version, String name) {
        this.versionId = versionId;
        this.version = version;
        this.nameType = name;
    }

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int versionId) {
        this.versionId = versionId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }
}
