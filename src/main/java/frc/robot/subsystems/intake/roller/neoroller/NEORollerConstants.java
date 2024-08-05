package frc.robot.subsystems.intake.roller.neoroller;

import au.grapplerobotics.LaserCan;

public class NEORollerConstants {

    public static final LaserCan.RegionOfInterest LASER_CAN_REGION_OF_INTEREST = new LaserCan.RegionOfInterest(8, 8, 16, 16);
    public static final LaserCan.TimingBudget TIMING_BUDGET = LaserCan.TimingBudget.TIMING_BUDGET_20MS;
    public static final LaserCan.RangingMode RANGE_MODE = LaserCan.RangingMode.SHORT;
    public static final double INTAKE_WIDTH = 500;
}
