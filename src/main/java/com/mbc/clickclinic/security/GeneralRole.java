package com.mbc.clickclinic.security;

public enum GeneralRole {
    ADMIN("ADMIN"),
    PATIENT("PATIENT"),
    MEDECIN("MEDECIN"),
    SECRETAIRE("SECRETAIRE");

    private String role = "";

    GeneralRole(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String toString(){
        return role;
    }
}
