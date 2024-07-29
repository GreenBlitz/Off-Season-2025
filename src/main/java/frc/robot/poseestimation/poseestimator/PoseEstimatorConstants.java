package frc.robot.poseestimation.poseestimator;

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.Vector;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.numbers.N3;

public class PoseEstimatorConstants {

    public static double ODOMETRY_FREQUENCY_HERTZ = 200.0;

    public static final double POSE_BUFFER_SIZE_SECONDS = 2.0;

    protected static String POSE_LOG_PATH = "Robot Pose";


    /**
     * The vector represents how ambiguous each value is.
     * The first value represents how ambiguous is the x,
     * the second one for the y, and the third one is for the theta (rotation).
     * Increase these numbers to trust the estimate less.
     */
    protected static final Vector<N3> ODOMETRY_STANDARD_DEVIATIONS = VecBuilder.fill(0.003, 0.003, 0.0002);

    protected static final Pose2d DEFAULT_POSE = new Pose2d(2, 5, new Rotation2d());

}
