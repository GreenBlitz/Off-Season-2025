package frc.robot.subsystems.Indexer;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;

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
        return new FunctionalCommand(
                () -> {},
                () -> indexer.rotateByState(),
                (isInterrupted) -> indexer.stop(),
                () -> false,
                indexer
        );
    }

    public Command rollByPower (double power){
        return new FunctionalCommand(
                () -> {},
                () -> indexer.rotateByPower(power),
                (isInterrupted) -> indexer.stop(),
                () -> false,
                indexer
        );
    }

}
