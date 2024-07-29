package frc.robot.subsystems.Indexer;

public interface IIndexer {

    void setPower(double power);

    void setVoltage(double voltage);

    void setVelocity(double Velocity);

    void setBrake();

    void setCoast();
    void updateInputs (IndexerInputsAutoLogged inputs);

}
