package by.bsu.modulestatic.entity;

/**
 * Created by Anna on 03.12.2016.
 */
public class VechicleClass {
    private int classId;
    private int version;
    private String name;
    private String description;
    private int vechicleId;

    public VechicleClass() {

    }

    public VechicleClass(int classId, int version, String name, String description, int vechicleId) {
        this.classId = classId;
        this.version = version;
        this.name = name;
        this.description = description;
        this.vechicleId = vechicleId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVechicleId() {
        return vechicleId;
    }

    public void setVechicleId(int vechicleId) {
        this.vechicleId = vechicleId;
    }
}
