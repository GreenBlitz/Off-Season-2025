package frc.robot.subsystems.intake.pivot;

import frc.robot.subsystems.intake.pivot.simulation.SimulationPivot;
import frc.utils.GBSubsystem;

import static frc.robot.subsystems.intake.pivot.PivotConstants.LOG_PATH;

public class Pivot extends GBSubsystem {

    private PivotInputsAutoLogged inputs;
    private IPivot pivot;
    public Pivot() {
        super(LOG_PATH);

        this.inputs = new PivotInputsAutoLogged();
        this.pivot = new SimulationPivot();
    }

    @Override
    protected void subsystemPeriodic() {
        pivot.updateInputs(inputs);
    }

    public void setPower (double power){
        pivot.setPower(power);
    }

    public void setVoltage (double voltage){
        pivot.setVoltage(voltage);
    }



}
