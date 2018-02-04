package ru.mitroshkinam.journalLpv.model;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class VP implements Serializable {

    private static final long serialVersionUID = 1606336866507089372L;
    
    private int pkVP;
    private String name;
    private String regNumber;
    private int formVP;

    public int getPkVP() {
        return pkVP;
    }

    public void setPkVP(int pkVP) {
        this.pkVP = pkVP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public int getFormVP() {
        return formVP;
    }

    public void setFormVP(int formVP) {
        this.formVP = formVP;
    }
    
    
}
