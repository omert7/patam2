package app.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SimulatorPlayer {
    private String ip;
    private long port;
    private double speed;
    private FloatProperty timeStamp;

    public SimulatorPlayer(FlightSettings fs) {
        this.ip = fs.getSimulatorIp();
        this.port = fs.getSimulatorPort();
        this.speed = fs.getSimulatorSpeed();
        this.timeStamp = new SimpleFloatProperty();
    }


    public void stop() {
    }

    public void pause() {
    }

    public void play(double speed, double time) {
        try {
            Socket fg = new Socket("localhost", 5400);
            BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\tatio\\patam2\\src\\files\\reg_flight.csv"));
            PrintWriter out = new PrintWriter(fg.getOutputStream());
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                out.println(line);
                out.flush();
                Thread.sleep((long) (100 / this.speed));
            }
            out.close();
            in.close();
            fg.close();
        } catch (Exception e) {

        }

    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getPort() {
        return port;
    }

    public void setPort(long port) {
        this.port = port;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public float getTimeStamp() {
        return timeStamp.get();
    }

    public FloatProperty timeStampProperty() {
        return timeStamp;
    }

    public void setTimeStamp(float timeStamp) {
        this.timeStamp.set(timeStamp);
    }
}
