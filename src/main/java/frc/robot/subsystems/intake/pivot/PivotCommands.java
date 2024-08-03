package frc.robot.subsystems.intake.pivot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;

public class PivotCommands {
    private Pivot pivot;

    public PivotCommands (Pivot pivot){
        this.pivot = pivot;
    }
    public Command openGripper (){
        return new FunctionalCommand(
                () -> {},
                () -> pivot.setState(PivotState.OPEN),
                (interrupted) -> {},
                () -> false,
                pivot
        ).withName("open pivot");
    }

    public Command closeGripper (){
        return new FunctionalCommand(
                () -> {},
                () -> pivot.setState(PivotState.CLOSED),
                (interrupted) -> {},
                () -> false,
                pivot
        ).withName("close pivot");
    }

    public Command setPower (){
        return new FunctionalCommand(
                () -> {},
                () -> pivot.setPower(1),
                (interrupted) -> {},
                () -> false,
                pivot
        ).withName("power");
    }
}
