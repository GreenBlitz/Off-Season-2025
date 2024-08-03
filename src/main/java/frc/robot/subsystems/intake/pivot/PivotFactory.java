package frc.robot.subsystems.intake.pivot;

import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.subsystems.intake.pivot.simulation.SimulationPivot;
import frc.robot.subsystems.intake.pivot.talonfx.TalonFXPivot;

import static frc.robot.Robot.ROBOT_TYPE;

public class PivotFactory {

    public static IPivot createPivot (){
        return switch (ROBOT_TYPE){
            case SIMULATION -> new SimulationPivot();
            case REAL -> new TalonFXPivot();
            case REPLAY -> new IPivot() {
                @Override
                public void setVoltage(double voltage) {

                }

                @Override
                public void setPower(double power) {

                }

                @Override
                public void setPosition(Rotation2d targetPosition) {

                }

                @Override
                public void updateInputs(PivotInputsAutoLogged inputs) {

                }
            };
        };

    }


}
