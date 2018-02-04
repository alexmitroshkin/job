package ru.mitroshkinam.journalLpv.controller;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import ru.mitroshkinam.journalLpv.model.VP;
import ru.mitroshkinam.journalLpv.service.Service;

@ManagedBean
@SessionScoped
public class registerVPController implements Serializable{

    private static final long serialVersionUID = -7718501786681573875L;
    
    private StreamedContent resultFile;
    private InputStream stream;
    
    List<VP> vps;
    List<VP> selectedVPs;

    public List<VP> getVps() {
        this.vps = search();
        return vps;
    }

    public void setVps(List<VP> vps) {
        this.vps = vps;
    }

    public List<VP> getSelectedVPs() {
        return selectedVPs;
    }

    public void setSelectedVPs(List<VP> selectedVPs) {
        this.selectedVPs = selectedVPs;
    }
    
    public String formedVPforSelected(){
        List<VP> selected = this.selectedVPs;        
        return "sucess";
    }
    
    public void deleteSelectedVPs(){
        FacesMessage message = null;
        StringBuilder notSucess = new StringBuilder("");
        for (VP vp : selectedVPs) {
            if (vp.getFormVP() == 1) notSucess.append(vp.getRegNumber()).append(" ");
        }
        if (notSucess.toString().equals("")){
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Успешно",  null);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка",  "По регномерам :" + notSucess.toString() + " сформирована ВП");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public StreamedContent getResultFile(){        
        return new DefaultStreamedContent(stream, "image/jpg", "downloaded_optimus.jpg");
    }
    
    public void error() {
        stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/screen.jpg");
        if (stream != null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Contact admin."));
            throw new AbortProcessingException();
        }
        
    }
    
    private List<VP> search(){
        Service service = new Service();
        return service.searchVP();
    }
}
