package io.middaymeal.app;

public class SchoolModel {
    private String school_id;
    private String school_name;
    private String district;
    private String state;
    private String address;

    public SchoolModel(String school_id, String school_name, String district, String state, String address) {
        this.school_id = school_id;
        this.school_name = school_name;
        this.district = district;
        this.state = state;
        this.address = address;
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
