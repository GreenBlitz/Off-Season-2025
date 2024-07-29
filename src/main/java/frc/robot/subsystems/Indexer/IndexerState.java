package frc.robot.subsystems.Indexer;

public enum IndexerState {
    PASS_TO_AMP(1),
    PASS_TO_SPEAKER(0);

    public final int rotationMultiplier;

    IndexerState (int rotationMultiplier){
        this.rotationMultiplier = rotationMultiplier;
    }
}
