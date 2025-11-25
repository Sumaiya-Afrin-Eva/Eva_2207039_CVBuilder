package com.example.cv_builder;

import javafx.beans.property.*;

public class CVData {
    private final IntegerProperty id = new SimpleIntegerProperty(this, "id", -1);
    private final StringProperty fullName = new SimpleStringProperty(this, "fullName", "");
    private final StringProperty jobTitle = new SimpleStringProperty(this, "jobTitle", "");
    private final StringProperty email = new SimpleStringProperty(this, "email", "");
    private final StringProperty phone = new SimpleStringProperty(this, "phone", "");
    private final StringProperty address = new SimpleStringProperty(this, "address", "");
    private final StringProperty projects = new SimpleStringProperty(this, "projects", "");
    private final StringProperty skills = new SimpleStringProperty(this, "skills", "");
    private final StringProperty languages = new SimpleStringProperty(this, "languages", "");
    private final StringProperty experience = new SimpleStringProperty(this, "experience", "");
    private final StringProperty education = new SimpleStringProperty(this, "education", "");
    private final StringProperty imageUrl = new SimpleStringProperty(this, "imageUrl", "");

    public int getId() { return id.get(); }
    public void setId(int value) { id.set(value); }
    public IntegerProperty idProperty() { return id; }

    public String getFullName() { return fullName.get(); }
    public void setFullName(String v) { fullName.set(v); }
    public StringProperty fullNameProperty() { return fullName; }

    public String getJobTitle() { return jobTitle.get(); }
    public void setJobTitle(String v) { jobTitle.set(v); }
    public StringProperty jobTitleProperty() { return jobTitle; }

    public String getEmail() { return email.get(); }
    public void setEmail(String v) { email.set(v); }
    public StringProperty emailProperty() { return email; }

    public String getPhone() { return phone.get(); }
    public void setPhone(String v) { phone.set(v); }
    public StringProperty phoneProperty() { return phone; }

    public String getAddress() { return address.get(); }
    public void setAddress(String v) { address.set(v); }
    public StringProperty addressProperty() { return address; }

    public String getProjects() { return projects.get(); }
    public void setProjects(String v) { projects.set(v); }
    public StringProperty projectsProperty() { return projects; }

    public String getSkills() { return skills.get(); }
    public void setSkills(String v) { skills.set(v); }
    public StringProperty skillsProperty() { return skills; }

    public String getLanguages() { return languages.get(); }
    public void setLanguages(String v) { languages.set(v); }
    public StringProperty languagesProperty() { return languages; }

    public String getExperience() { return experience.get(); }
    public void setExperience(String v) { experience.set(v); }
    public StringProperty experienceProperty() { return experience; }

    public String getEducation() { return education.get(); }
    public void setEducation(String v) { education.set(v); }
    public StringProperty educationProperty() { return education; }

    public String getImageUrl() { return imageUrl.get(); }
    public void setImageUrl(String v) { imageUrl.set(v); }
    public StringProperty imageUrlProperty() { return imageUrl; }
}