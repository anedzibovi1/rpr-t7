package ba.unsa.rpr.tutorijal7;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tutorijal {


    public static ArrayList<Grad> ucitajGradove() throws FileNotFoundException {
        ArrayList<Grad> novi=new ArrayList<>();
        Scanner ulaz;
        try {
            ulaz = new Scanner(new FileReader("mjerenja.txt"));
        } catch(FileNotFoundException e) {
            throw e;
        }
        ulaz.useDelimiter("\n");
        try {
            Grad grad=new Grad();
            while (ulaz.hasNext()) {
                String gradovi = ulaz.next();
                String[] podaci = gradovi.split(",");
                grad.setNaziv(podaci[0]);
                List<Double> temperature = new ArrayList<>();
                for(char c : grad.getNaziv().toCharArray())
                    if (!Character.isAlphabetic(c) && !Character.isWhitespace(c))
                        throw new IllegalArgumentException("Neispravno ime grada");
                for (int i = 1; i < podaci.length; i++) {
                    for(char c : podaci[i].toCharArray())
                        if (Character.isAlphabetic(c))
                            throw new IllegalArgumentException("Neispravne temperature");
                    temperature.add(Double.parseDouble(podaci[i]));
                }
                    novi.add(new Grad(grad.getNaziv(), temperature.toArray(new Double[0])));
            }
        }
        catch (IllegalArgumentException e) { System.out.print(e); }
        return novi;
    }

    public static UN ucitajXml(ArrayList<Grad> gradovi) {
        ArrayList<Drzava> dr = new ArrayList<Drzava>();
        UN un = new UN();
        Document xmldoc = null;
        try {
            DocumentBuilder docReader = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            xmldoc = docReader.parse(new File("drzave.xml"));
        } catch (Exception e) {
            throw new IllegalArgumentException("Greska pri otvaranju XML dokumenta");
        }
            Element element = xmldoc.getDocumentElement();
            NodeList drzave = element.getChildNodes();
            for (int i = 0; i < drzave.getLength(); i++) {
                Node drzavaNode = drzave.item(i);
                if (drzavaNode instanceof Element) {
                    Drzava nova = new Drzava();
                    Grad grad = new Grad();
                    Element drzavaElement = (Element) drzavaNode;
                    NodeList glavniGradElementi = drzavaElement.getElementsByTagName("glavniGrad");
                    if (glavniGradElementi.getLength() > 0) {
                        Element glavniElement = (Element) drzavaElement.getElementsByTagName("glavnigrad").item(0);
                        grad.setNaziv(glavniElement.getElementsByTagName("naziv").item(0).getTextContent());
                        grad.setBrojStanovnika(Integer.valueOf(glavniElement.getElementsByTagName("brojStanovnika").item(0).getTextContent()));
                        String imeGrada = glavniElement.getElementsByTagName("naziv").item(0).getTextContent();
                        boolean ima = false;
                        int index = -1;
                        for (int j = 0; i < gradovi.size(); j++) {
                            if (gradovi.get(j).getNaziv().equals(imeGrada)) {
                                ima = true;
                                index = j;
                            }
                            if (ima) {
                                grad.setTemperature(gradovi.get(index).getTemperature());
                                grad.setVel(gradovi.get(index).getVel());
                            }
                        }
                    }
                    nova.setGlavniGrad(grad);
                    nova.setBrojStanovnika(Integer.valueOf(drzavaElement.getElementsByTagName("brojStanovnika").item(0).getTextContent()));
                    nova.setNaziv(drzavaElement.getElementsByTagName("naziv").item(0).getTextContent());
                    nova.setJedinicaZaPovrsinu(drzavaElement.getElementsByTagName("jedinicaZaPovrsinu").item(0).getTextContent());
                    nova.setPovrsina(Double.valueOf(drzavaElement.getElementsByTagName("povrsina").item(0).getTextContent()));
                    dr.add(nova);

                }
            }
        un.setDrzave(dr);
        return un;
    }


    public static void zapisiXml(UN un) throws FileNotFoundException{
        try {
            XMLEncoder izlaz = new XMLEncoder(new FileOutputStream("un.xml"));
            izlaz.writeObject(un);
            izlaz.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        double[] temperature1 = {4, -12, 22, 13, -3};
        double[] temperature2 = {-5, 11, 23, 30};
        Grad g1 = new Grad("Sarajevo", 10500, temperature1, 5);
        Grad g2 = new Grad("Berlin", 8992832, temperature2, 4);
        Drzava d1 = new Drzava("BiH", 2003040, 100000, "km2", g1);
        Drzava d2 = new Drzava("Njemacka", 143200000, 9900000, "km2", g2);
        UN un = new UN();
        un.dodajDrzavu(d1);
        un.dodajDrzavu(d2);
        try {
            ucitajGradove();
            zapisiXml(un);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}


