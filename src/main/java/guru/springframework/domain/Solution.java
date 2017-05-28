package guru.springframework.domain;

import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Optional;

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

    @Column(name="func")
    private String function;

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

    @Column(name="img_id")
    private String imageId;

    @Column(name="img_name")
    private String imageName;

    @Column(name="file")
    private byte[] file;

    @Column(name="time")
    private Timestamp time;

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

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
