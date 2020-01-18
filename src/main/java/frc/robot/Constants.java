/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

public final class Constants {

    // drivetrain
    public static final class Drivetrain {

        // motors
        public static final int DRIVE_FL = 5;
        public static final int DRIVE_FR = 1;
        public static final int DRIVE_BL = 4;
        public static final int DRIVE_BR = 8;

        // configuration
        public static final int CURRENT_LIMIT = 40;

        // kS = volts; kV = seconds / meter; kA = seconds^2 / meter
        public static final double kS = 0;
        public static final double kV = 0;
        public static final double kA = 0;

        public static final double trackWidth = 0;

        // ramsete constants, ramseteB and Zeta are in meters per second
        public static final int maxVoltage = 10;
        public static final double ramseteB = 2;
        public static final double ramseteZeta = 0.7;

        // max speed / accel
        public static final double maxSpeedMetersPerSecond = 69;
        public static final double maxAccelMetersPerSecondSquared = 420;

        // kP velocity
        public static final double kPVel = 10;
    }

    // flywheel
    public final class Flywheel {

        // motors
        public static final int MAIN = 2;
        public static final int SECONDARY = 3;

        // ports for the encoder: note that each
        // encoder actually takes up 2 ports, so
        // A and B are for the same encoder
        public static final int ENCODER_A = 1234;
        public static final int ENCODER_B = 4321;
        public static final boolean ENCODER_REVERSE_DIRECTION = false;

        // measured in rotations per minute
        public static final int SPEED = 60;

        // We need this because the talon has different PID slots
        public static final int kPIDLoopIdx = 0;

        // don't try to tune these! These are constants that are
        // stated in the documentation so that we can easily enter
        // rotations per minute.
        public static final int TICKS_PER_ROTATION = 4096;
        public static final double SETPOINT_CONSTANT = 0.001667; // 100 milliseconds / 1 min

        // PID constants for flywheel
        public static final double kP = 1;
        public static final double kI = 0;
        public static final double kD = 0;
        public static final double kS = 0;
        public static final double kV = 0;
        public static final double kA = 0;
        public static final double ERROR_TOLERANCE = 0;

        /**
         * Timeout value (in milliseconds) generally used in parameter configs
         * Non-zero to block the config until success, zero to skip checking
         */
        public static final int CONFIG_TIMEOUT = 30;

    }

    // camera
    public final class Camera {

        public static final int CAMERA1 = 0;
        public static final int CAMERA2 = 1;

        public static final int CAMERA1_RES_X = 640;
        public static final int CAMERA1_RES_Y = 480;
        public static final int CAMERA2_RES_X = 640;
        public static final int CAMERA2_RES_Y = 480;

    }

}
