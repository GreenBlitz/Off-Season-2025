package frc.robot.subsystems.Indexer;

import frc.robot.subsystems.Indexer.simulationindexer.SimulationIndexer;
import frc.utils.GBSubsystem;
import org.littletonrobotics.junction.Logger;

public class Indexer extends GBSubsystem {

    private IIndexer indexer;
    private IndexerInputsAutoLogged inputs = new IndexerInputsAutoLogged();
    private IndexerState state;
    private IndexerCommandsBuilder commandsBuilder;

    public Indexer(String logPath) {
        super(logPath);

        this.indexer = new SimulationIndexer();
        this.state = IndexerState.PASS_TO_SPEAKER;

        this.commandsBuilder = new IndexerCommandsBuilder(this);
    }

    @Override
    protected void subsystemPeriodic() {

        indexer.updateInputs(inputs);
        Logger.processInputs(getLogPath(), inputs);
        Logger.recordOutput(getLogPath() + "state", state);
    }

    public void setState(IndexerState state) {
        this.state = state;
    }

    public void rotateByVelocity(double velocity) {
        indexer.setVelocity(velocity);
    }

    public void rotateByPower(double power) {
        indexer.setPower(power);
    }

    public void rotateByState() {
        rotateByPower(state.rotationMultiplier * IndexerConstants.PASSING_POWER);
    }
    public void stop (){
        indexer.setPower(0);
    }

    public IndexerCommandsBuilder getCommandsBuilder (){
        return commandsBuilder;
    }

}
