package app.model;

import java.beans.ExceptionListener;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CsvFlightHandler {
    static class csvColumn {
        public String name;
        public ArrayList<Float> values;

        public csvColumn(String name) {
            this.name = name;
            this.values = new ArrayList<>();
        }

        public float getMaxValue() {
            return Collections.max(this.values);
        }

        public float getMinValue() {
            return Collections.min(this.values);
        }

        @Override
        public String toString() {
            return "csvColumn{ name=" + name + ", min=" +this.getMinValue() + ", max=" + this.getMaxValue() + "}";
        }
    }

    public String getXmlName() {
        return xmlName;
    }

    public void setXmlName(String xmlName) {
        this.xmlName = xmlName;
    }

    public String getCsvName() {
        return csvName;
    }

    public void setCsvName(String csvName) {
        this.csvName = csvName;
    }

    public List<csvColumn> getCsvColumns() {
        return csvColumns;
    }

    public void setCsvColumns(List<csvColumn> csvColumns) {
        this.csvColumns = csvColumns;
    }

    public List<FlightFeature> getFlightFeatures() {
        return flightFeatures;
    }

    public void setFlightFeatures(List<FlightFeature> flightFeatures) {
        this.flightFeatures = flightFeatures;
    }

    String xmlName = "flightXml.xml";
    String csvName;
    List<csvColumn> csvColumns;
    List<FlightFeature> flightFeatures;


    public CsvFlightHandler(String csvPath) {
        this.csvName = csvPath;
        this.csvColumns = new ArrayList<>();
        this.flightFeatures = new ArrayList<>();
    }

    public static void writeFeaturesToXml(List<FlightFeature> lf) {

    }

    public void readCsvFeatures() {
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader(this.csvName));
            if ((line = br.readLine()) != null) {
                // READING FIRST LINE FOR COLUMN NAMES
                String[] s1 = line.split(",");
                for (String s : s1) {
                    csvColumn column = new csvColumn(s);
                    this.csvColumns.add(column);
                }
                // READING REST OF THE LINES FOR THE VALUES
                while ((line = br.readLine()) != null) {
                    s1 = line.split(",");
                    for (int i = 0; i < s1.length; i++) {
                        this.csvColumns.get(i).values.add(Float.parseFloat(s1[i]));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(20);
        }
    }

    public void CreateListOfFeaturesFromCsvColumns() {
        for (csvColumn cc : csvColumns) {
            FlightFeature ff = new FlightFeature(cc.name, cc.getMinValue(), cc.getMaxValue());
            this.flightFeatures.add(ff);
        }
    }
    public void serializeToXML()
    {
        try{
            FileOutputStream fos = new FileOutputStream("C:\\Users\\tatio\\patam2\\src\\files\\ss.xml");
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.setExceptionListener(new ExceptionListener() {
                public void exceptionThrown(Exception e) {
                    System.out.println("Exception! :" + e.toString());
                }
            });
            for(csvColumn cc: this.csvColumns) {
                encoder.writeObject(cc);
            }
            encoder.close();
            fos.close();
        }

         catch (Exception e){

        }
    }

}