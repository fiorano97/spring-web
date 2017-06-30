package dhi.ds.domain;

import javax.persistence.*;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Solution {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="sname")
    private String solutionName;

    @Column(name="sg_1")
    private String solutionGroup1;

    @Column(name="sg_2")
    private String solutionGroup2;

    @Column(name="eq")
    private String equipment;

    @Column(name="com")
    private String component;

    @Column(name="cb_1")
    private String customerBenefit1;

    @Column(name="cb_2")
    private String customerBenefit2;

    @Column(name="func", length=512)
    private String function;

    @Column(name="description", length=5000)
    private String description;

    @Column(name="maker")
    private String maker;

    @Column(name="supplier")
    private String supplier;

    @Column(name="stype")
    private String solutionType;

    @Column(name="dtype")
    private String developmentType;

    @Column(name="ref")
    private String reference;

    @Column(name="source")
    private String source;

    @Column(name="file_id")
    @ElementCollection
    private List<Integer> fileId;

    @Column(name="image_id")
    @ElementCollection
    private List<Integer> imageId;

    @Column(name = "function_info")
    @ElementCollection
    private List<String> functionInfo;

    @Column(name = "value_info")
    @ElementCollection
    private List<String> valueInfo;

    @Column(name="time")
    private Timestamp time;

    private String monitoringYn;
    private String predictionYn;
    private String diagnosisYn;
    private String optimizationYn;
    private String managementYn;
    private String efficiencyYn;
    private String flexibilityYn;
    private String availabilityYn;
    private String emissionYn;



    public Solution () {
        setTime(new Timestamp(System.currentTimeMillis()));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSolutionName() {
        return solutionName;
    }

    public void setSolutionName(String solutionName) {
        this.solutionName = solutionName;
    }

    public String getSolutionGroup1() {
        return solutionGroup1;
    }

    public void setSolutionGroup1(String solutionGroup1) {
        this.solutionGroup1 = solutionGroup1;
    }

    public String getSolutionGroup2() {
        return solutionGroup2;
    }

    public void setSolutionGroup2(String solutionGroup2) {
        this.solutionGroup2 = solutionGroup2;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getCustomerBenefit1() {
        return customerBenefit1;
    }

    public void setCustomerBenefit1(String customerBenefit1) {
        this.customerBenefit1 = customerBenefit1;
    }

    public String getCustomerBenefit2() {
        return customerBenefit2;
    }

    public void setCustomerBenefit2(String customerBenefit2) {
        this.customerBenefit2 = customerBenefit2;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getSolutionType() {
        return solutionType;
    }

    public void setSolutionType(String solutionType) {
        this.solutionType = solutionType;
    }

    public String getDevelopmentType() {
        return developmentType;
    }

    public void setDevelopmentType(String developmentType) {
        this.developmentType = developmentType;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<Integer> getFileId() {
        return fileId;
    }

    public void setFileId(List<Integer> fileId) {
        this.fileId = fileId;
    }

    public List<Integer> getImageId() {
        return imageId;
    }

    public void setImageId(List<Integer> imageId) {
        this.imageId = imageId;
    }

    public List<String> getFunctionInfo() {
        return functionInfo;
    }

    public void setFunctionInfo(List<String> functionInfo) {
        this.functionInfo = functionInfo;
    }

    public List<String> getValueInfo() {
        return valueInfo;
    }

    public void setValueInfo(List<String> valueInfo) {
        this.valueInfo = valueInfo;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getColumnCount() {
        return getClass().getDeclaredFields().length;
    }

    public String getMonitoringYn() {
        return monitoringYn;
    }

    public void setMonitoringYn(String monitoringYn) {
        this.monitoringYn = monitoringYn;
    }

    public String getPredictionYn() {
        return predictionYn;
    }

    public void setPredictionYn(String predictionYn) {
        this.predictionYn = predictionYn;
    }

    public String getDiagnosisYn() {
        return diagnosisYn;
    }

    public void setDiagnosisYn(String diagnosisYn) {
        this.diagnosisYn = diagnosisYn;
    }

    public String getOptimizationYn() {
        return optimizationYn;
    }

    public void setOptimizationYn(String optimizationYn) {
        this.optimizationYn = optimizationYn;
    }

    public String getManagementYn() {
        return managementYn;
    }

    public void setManagementYn(String managementYn) {
        this.managementYn = managementYn;
    }

    public String getEfficiencyYn() {
        return efficiencyYn;
    }

    public void setEfficiencyYn(String efficiencyYn) {
        this.efficiencyYn = efficiencyYn;
    }

    public String getFlexibilityYn() {
        return flexibilityYn;
    }

    public void setFlexibilityYn(String flexibilityYn) {
        this.flexibilityYn = flexibilityYn;
    }

    public String getAvailabilityYn() {
        return availabilityYn;
    }

    public void setAvailabilityYn(String availabilityYn) {
        this.availabilityYn = availabilityYn;
    }

    public String getEmissionYn() {
        return emissionYn;
    }

    public void setEmissionYn(String emissionYn) {
        this.emissionYn = emissionYn;
    }
}
