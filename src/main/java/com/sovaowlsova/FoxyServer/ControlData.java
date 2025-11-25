package com.sovaowlsova.FoxyServer;

public class ControlData {
    private int steering;
    private int throttle;

    public ControlData() {}
    public ControlData(int throttle, int steering) {
        this.throttle = throttle;
        this.steering = steering;
    }

    public int getThrottle() {
        return throttle;
    }

    public int getSteering() {
        return steering;
    }

    public void setThrottle(int throttle) {
        this.throttle = throttle;
    }

    public void setSteering(int steering) {
        this.steering = steering;
    }
}
