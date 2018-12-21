package ba.unsa.rpr.tutorijal7;

import java.io.Serializable;
import java.util.ArrayList;

public class UN implements Serializable {
    private ArrayList<Drzava> drzave;

    public UN() {
        drzave=new ArrayList<>();
    }

    public ArrayList<Drzava> getDrzave() {
        return drzave;
    }

    public void addDrzava(Drzava d) {
        drzave.add(d);
    }

    public void setDrzave(ArrayList<Drzava> drzave) {
        this.drzave = drzave;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int b=1;
        for(Drzava drzava : drzave) {
            s.append(b).append(".").append(drzava).append("\n");
            b++;
        }
        return s.toString();
    }

    public void dodajDrzavu(Drzava drzava) {
        drzave.add(drzava);
    }
}
