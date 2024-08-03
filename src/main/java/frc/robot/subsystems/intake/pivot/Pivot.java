package frc.robot.subsystems.intake.pivot;

import frc.utils.GBSubsystem;
import org.littletonrobotics.junction.Logger;

import static frc.robot.subsystems.intake.pivot.PivotConstants.CLOSED_POSITION;
import static frc.robot.subsystems.intake.pivot.PivotConstants.LOG_PATH;
import static frc.robot.subsystems.intake.pivot.PivotConstants.OPEN_POSITION;

public class Pivot extends GBSubsystem {

    private PivotInputsAutoLogged inputs;
    private IPivot pivot;
    private PivotState state;
    private PivotCommands pivotCommands;
    public Pivot() {
        super(LOG_PATH);

        this.inputs = new PivotInputsAutoLogged();
        this.pivot = PivotFactory.createPivot();
        this.state = PivotState.CLOSED;
        this.pivotCommands = new PivotCommands(this);
    }

    public void setPower (double power){
        pivot.setPower(power);
    }

    public void setVoltage (double voltage){
        pivot.setVoltage(voltage);
    }

    @Override
    protected void subsystemPeriodic() {
        pivot.updateInputs(inputs);
        Logger.processInputs(getLogPath(),inputs);
        Logger.recordOutput(getLogPath() + "state", state);
        switch (state){
            case OPEN -> handleOpen();
            case CLOSED -> handleClosed();
        }
    }

    public void setState(PivotState state) {
        this.state = state;
    }

    private void handleOpen (){
        pivot.setPosition(OPEN_POSITION);
    }
    private void handleClosed (){
        pivot.setPosition(CLOSED_POSITION);
    }

    public PivotCommands getPivotCommands() {
        return pivotCommands;
    }

}
