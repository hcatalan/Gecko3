package org.openqa.selenium;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class JAXB{
    public static void main(String[] args) throws JAXBException {

        Estadistica estadistica = new Estadistica();
        estadistica.setName("Estadistica");


        String xmlFilePath = "ruta/del/archivo.xml";

        JAXBContext context = JAXBContext.newInstance(Estadistica.class);
        Marshaller marshaller = context.createMarshaller();

        marshaller.marshal(estadistica, new File(xmlFilePath));
    }
}