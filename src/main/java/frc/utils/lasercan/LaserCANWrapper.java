package frc.utils.lasercan;

import au.grapplerobotics.ConfigurationFailedException;
import au.grapplerobotics.LaserCan;

public class LaserCANWrapper {

    private final LaserCan laserCan;

    public LaserCANWrapper(int controllerAreNetworkBusId) {
        laserCan = new LaserCan(controllerAreNetworkBusId);
    }

    public boolean setRangingMode(LaserCan.RangingMode mode){
        try {
            laserCan.setRangingMode(mode);
        }
        catch (ConfigurationFailedException e) {
            System.out.println(e.toString());
            return false;
        }
        return true;
    }

    public boolean setRegionOfInterest(LaserCan.RegionOfInterest regionOfInterest){
        try {
            laserCan.setRegionOfInterest(regionOfInterest);
        }
        catch (ConfigurationFailedException e) {
            System.out.println(e.toString());
            return false;
        }
        return true;
    }

    public boolean setTimingBudget(LaserCan.TimingBudget timingBudget) {
        try {
            laserCan.setTimingBudget(timingBudget);
        }
        catch (ConfigurationFailedException e) {
            System.out.println(e.toString());
            return false;
        }
        return true;
    }

    public LaserCan.Measurement getMeasurement (){
        return laserCan.getMeasurement();
    }

}
