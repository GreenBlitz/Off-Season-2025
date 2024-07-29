package frc.robot.subsystems.Indexer;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;

public class IndexerCommandsBuilder {

    private Indexer indexer;

    protected IndexerCommandsBuilder(Indexer indexer){
        this.indexer = indexer;
    }

    public Command setStateToAmp (){
        return new InstantCommand(() -> indexer.setState(IndexerState.PASS_TO_AMP));
    }

    public Command setStateSpeaker (){
        return new InstantCommand(() -> indexer.setState(IndexerState.PASS_TO_SPEAKER));
    }

    public Command rollByState (){
        return new RunCommand(() -> indexer.rotateByState(), indexer);
    }

    public Command rollByPower (double power){
        return new RunCommand(() -> indexer.rotateByPower(power), indexer);
    }

}
