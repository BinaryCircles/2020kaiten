/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public final class Constants {

    // drivetrain
    public static final int DRIVE_FL = 5;
    public static final int DRIVE_FR = 1;
    public static final int DRIVE_BL = 4;
    public static final int DRIVE_BR = 8;

    // flywheel
    public static final int FLYWHEEL_MAIN = 2;
    public static final int FLYWHEEL_SECONDARY = 3;

    // drivetrain current limiting
    public static final int CURRENT_LIMIT_AMPS_DRIVE = 40;

    // flywheel current limiting
    public static final int CURRENT_LIMIT_AMPS_FLYWHEEL = 40;

    // flywheel speed is measured in rotations per minute.
    public static final int FLYWHEEL_SPEED = 60;

    // We need this because the talon has different PID slots
    public static final int kPIDLoopIdx = 0;

    // Don't try to tune these! These are constants that are
    // stated in the documentation so that we can easily enter
    // rotaitons per minute.
    public static final int FLYWHEEL_TICKS_PER_ROTATION = 4096;
    public static final double FLYWHEEL_SETPOINT_CONSTANT = 0.001667; // 100 milliseconds / 1 min

    // PID Constants for closed loop flywheel
    public static final double FLYWHEEL_kP = 1;
    public static final double FLYWHEEL_kI = 0;
    public static final double FLYWHEEL_kD = 0;
    public static final double FLYWHEEL_kF = 0;
    public static final double FLYWHEEL_kA = 0;
    /**
	 * Timeout value (in milliseconds) generally used in parameter configs
     * Non-zero to block the config until success, zero to skip checking
     */
    public static final int FLYWHEEL_CONFIG_TIMEOUT = 30;

public final class Constants {

    public static final class Drivetrain {
        // drivetrain
        public static final int drive_fl = 5;
        public static final int drive_fr = 1;
        public static final int drive_bl = 4;
        public static final int drive_br = 8;

        // kS = volts; kV = seconds / meter; kA = seconds^2 / meter
        public static final double kS = 0;
        public static final double kV = 0;
        public static final double kA = 0;

        public static final double trackWidth = 0;
        public static final DifferentialDriveKinematics driveKinematics = new DifferentialDriveKinematics(trackWidth);

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

    // controller IDs
    public static final int DRIVE_CONTROLLER_PORT = 1;
    public static final int OPERATOR_CONTROLLER_PORT = 2;


    public static final int camera1_resolution_x = 640;
    public static final int camera1_resolution_y = 480;
    public static final int camera2_resolution_x = 640;
    public static final int camera2_resolution_y = 480;
}
