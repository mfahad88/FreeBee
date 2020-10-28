package com.example.freebee.models.Contact;

public class ContactVO {
    private String ContactImage;
    private String ContactName;
    private String ContactNumber;

    public ContactVO(String contactImage, String contactName, String contactNumber) {
        ContactImage = contactImage;
        ContactName = contactName;
        ContactNumber = contactNumber;
    }

    public String getContactImage() {
        return ContactImage;
    }

    public void setContactImage(String contactImage) {
        this.ContactImage = ContactImage;
    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        ContactName = contactName;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "ContactVO{" +
                "ContactImage='" + ContactImage + '\'' +
                ", ContactName='" + ContactName + '\'' +
                ", ContactNumber='" + ContactNumber + '\'' +
                '}';
    }
}