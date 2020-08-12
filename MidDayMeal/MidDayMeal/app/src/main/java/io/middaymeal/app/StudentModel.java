package io.middaymeal.app;

public class StudentModel {
    private String id;
    private String first_name;
    private String last_name;
    private String father_name;
    private String isAttandance;
    private String photo;

    public StudentModel(String id, String first_name, String last_name, String father_name, String isAttandance, String photo) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.father_name = father_name;
        this.isAttandance = isAttandance;
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getIsAttandance() {
        return isAttandance;
    }

    public void setIsAttandance(String isAttandance) {
        this.isAttandance = isAttandance;
    }
}
