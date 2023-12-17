
package com.mycompany.college.transfer.application.tracker;


public class College {

    private String name;
    private String address;
    private String description;
    private String image;

    public College() {
    }

    public College(String name, String address, String description, String image) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

}
