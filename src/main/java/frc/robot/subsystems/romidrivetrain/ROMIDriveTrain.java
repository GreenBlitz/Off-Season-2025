package frc.robot.subsystems.romidrivetrain;

import edu.wpi.first.units.Distance;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.MutableMeasure;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import frc.utils.GBSubsystem;

import static edu.wpi.first.units.Units.Inches;

public class ROMIDriveTrain extends GBSubsystem {

    // The Romi has the left and right motors set to
    // PWM channels 0 and 1 respectively
    private final Spark leftMotor = new Spark(0);
    private final Spark rightMotor = new Spark(1);

    // The Romi has onboard encoders that are hardcoded
    // to use DIO pins 4/5 and 6/7 for the left and right
    private final Encoder leftEncoder = new Encoder(4, 5);
    private final Encoder rightEncoder = new Encoder(6, 7);

    private MutableMeasure<Distance> rightEncoderDistance,leftEncoderDistance;

    // Set up the differential drive controller
    private final DifferentialDrive differentialDriveController =
            new DifferentialDrive(leftMotor::set, rightMotor::set);

    /** Creates a new RomiDrivetrain. */
    public ROMIDriveTrain() {
        // Use inches as unit for encoder distances
        leftEncoder.setDistancePerPulse((Math.PI * ROMIDriveTrainConstants.WHEEL_DIAMETER.in(Inches)) / ROMIDriveTrainConstants.COUNTS_PER_REVOLUTION);
        rightEncoder.setDistancePerPulse((Math.PI * ROMIDriveTrainConstants.WHEEL_DIAMETER.in(Inches)) / ROMIDriveTrainConstants.COUNTS_PER_REVOLUTION);
        resetEncoders();;

        // Invert right side since motor is flipped
        rightMotor.setInverted(true);
    }

    public void arcadeDrive(double xAxisSpeed, double zAxisRotate) {
        differentialDriveController.arcadeDrive(xAxisSpeed, zAxisRotate);
    }

    public void resetEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
    }

    public Measure<Distance> getLeftDistance() {
        return leftEncoderDistance.mut_replace(leftEncoder.getDistance(),Inches);
    }

    public Measure<Distance> getRightDistance() {
        return rightEncoderDistance.mut_replace(rightEncoder.getDistance(), Inches);
    }


    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
