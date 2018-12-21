package ba.unsa.rpr.tutorijal7;

import java.io.Serializable;

public class Drzava implements Serializable {
    private String naziv;
    private int brojStanovnika;
    private double povrsina;
    private String jedinicaZaPovrsinu;
    private Grad glavni;

    public Drzava() {
        this.glavni=new Grad();
    }

    public Drzava(String naziv, int brojStanovnika, double povrsina, String jedinicaZaPovrsinu, Grad glavniGrad) {
        this.naziv = naziv;
        this.brojStanovnika = brojStanovnika;
        this.povrsina = povrsina;
        this.jedinicaZaPovrsinu = jedinicaZaPovrsinu;
        this.glavni = glavniGrad;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public double getPovrsina() {
        return povrsina;
    }

    public void setPovrsina(double povrsina) {
        this.povrsina = povrsina;
    }

    public String getJedinicaZaPovrsinu() {
        return jedinicaZaPovrsinu;
    }

    public void setJedinicaZaPovrsinu(String jedinicaZaPovrsinu) {
        this.jedinicaZaPovrsinu = jedinicaZaPovrsinu;
    }

    @Override
    public String toString() {
        return this.getNaziv() + "[" + this.getBrojStanovnika()+ "]" + " : " + this.getPovrsina() + this.getJedinicaZaPovrsinu() + " (" + this.getGlavniGrad().getNaziv() + ") \n";
    }

    public void setGlavniGrad(Grad grad) {
        glavni=grad;
    }

    public Grad getGlavniGrad() {
        return glavni;
    }
}
