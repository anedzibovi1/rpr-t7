package ba.unsa.rpr.tutorijal7;

import java.io.Serializable;

public class Grad implements Serializable {
    private String naziv;
    private int brojStanovnika;
    private double[] temperature;
    private int vel;

    public Grad() {
        vel=0;
        temperature=new double[1000];
    }
    public Grad(String naziv, Double[] temp) {
        this.naziv = naziv;
        this.vel = temp.length;
        this.temperature=new double[vel];
        for (int i = 0; i < vel; i++)
            this.temperature[i] = temp[i];
        brojStanovnika=0;

    }
    public Grad(String naziv, int brojStanovnika, double[] temperature, int velicina) {
        this.naziv = naziv;
        this.brojStanovnika = brojStanovnika;
        this.temperature = temperature;
        this.vel = velicina;
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

    public int getVel() {
        return vel;
    }

    public void setVel(int vel) {
        this.vel = vel;
    }

    public double[] getTemperature() {
        return temperature;
    }

    public void setTemperature(double[] temperature) {
        this.vel=temperature.length;
        this.temperature=temperature;
    }


    public void addTemperature(double temp) {
        if (temperature==null) {
            temperature = new double[1];
            temperature[0] = temp;
            vel=1;
        }
        else {
            if (temperature.length == vel) {
                double [] nova = new double[vel * 2];
                System.arraycopy(temperature, 0, nova, 0, temperature.length);
                nova[vel] = temp;
                temperature = nova;
                vel++;
            }
            else {
                temperature[vel] = temp;
                vel++;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(naziv + " [" + brojStanovnika + "] : ");
        for (int i = 0; i < temperature.length; i++) {
            if (i != temperature.length - 1)
                s.append(temperature[i]).append(", ");
            else
                s.append(temperature[i]);
        }
        return s.toString();
    }

}
