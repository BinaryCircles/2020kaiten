/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public final class Constants {

    public final class Drivetrain {
        // drivetrain
        public static final int drive_fl = 5;
        public static final int drive_fr = 1;
        public static final int drive_bl = 4;
        public static final int drive_br = 8;
    }

    // climb
    public static final class ClimbConstants {
        public static final int DEPLOY_TALON = 6;
        public static final int WINCH_TALON = 7;
        public static final int DEPLOY_VICTOR = 9;
        public static final int WINCH_VICTOR = 10;

        // climb motor max speeds
        public static final int DEPLOY_SPEED = 1;
        public static final int WINCH_SPEED = 1;

        // climb encoders
        public static final int CLIMB_DEPLOY_DIO = 1;
        public static final int CLIMB_DEPLOY_DIO2 = 2;

        // climb PID constants
        public static final int ARM_kF_GRAV = 0;
        public static final int ARM_kP = 0;
        public static final int ARM_kI = 0;
        public static final int ARM_kD = 0;

        // climb motion profiling constants
        public static final int MAX_VELOCITY_RAD_PER_SEC = 0;
        public static final int MAX_ACCEL = 0;

        // Encoder distance per pulse
        public static final int PULSES_PER_ROTATION = 4096;
        public static final double DISTANCE_PER_PULSE = (2.0 * Math.PI) / PULSES_PER_ROTATION;

        // starting arm position
        public static final double ARM_OFFSET = 0;

        // feedforward constants; kS = volts; kV = volts * sec / radians; kA = volts * sec^2 / radians (optional)
        public static final double kS = 0.0;
        public static final double kV = 0.0;
        public static final double kA = 0.0;

        // climb arm lengths
        public static final double CLIMB_ARM_LENGTH_1 = 69;
        public static final double CLIMB_ARM_LENGTH_2 = 420;
        public static final double CLIMB_ELEVATOR_LINEAR_SPEED = 0;
    }

    // climb
    public static final class ClimbConstants {
        public static final int DEPLOY_TALON = 6;
        public static final int WINCH_TALON = 7;
        public static final int DEPLOY_VICTOR = 9;
        public static final int WINCH_VICTOR = 10;

        // climb motor max speeds
        public static final int DEPLOY_SPEED = 1;
        public static final int WINCH_SPEED = 1;

        // climb encoders
        public static final int CLIMB_DEPLOY_DIO = 1;
        public static final int CLIMB_DEPLOY_DIO2 = 2;

        // climb PID constants
        public static final int ARM_kF_GRAV = 0;
        public static final int ARM_kP = 0;
        public static final int ARM_kI = 0;
        public static final int ARM_kD = 0;

        // climb motion profiling constants
        public static final int MAX_VELOCITY_RAD_PER_SEC = 0;
        public static final int MAX_ACCEL = 0;

        // Encoder distance per pulse
        public static final int PULSES_PER_ROTATION = 4096;
        public static final double DISTANCE_PER_PULSE = (2.0 * Math.PI) / PULSES_PER_ROTATION;

        // starting arm position
        public static final double ARM_OFFSET = 0;

        // feedforward constants; kS = volts; kV = volts * sec / radians; kA = volts * sec^2 / radians (optional)
        public static final double kS = 0.0;
        public static final double kV = 0.0;
        public static final double kA = 0.0;

        // climb arm lengths
        public static final double CLIMB_ARM_LENGTH_1 = 69;
        public static final double CLIMB_ARM_LENGTH_2 = 420;
        public static final double CLIMB_ELEVATOR_LINEAR_SPEED = 0;
    }

}
