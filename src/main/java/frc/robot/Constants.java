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

    // PID Constants for closed loop flywheel
    public static final double FLYWHEEL_kP = 1;
    public static final double FLYWHEEL_kI = 0;
    public static final double FLYWHEEL_kD = 0;
    public static final double FLYWHEEL_kF = 0;
    public static final double FLYWHEEL_kA = 0;


    // controller IDs
    public static final int DRIVE_CONTROLLER_PORT = 1;
    public static final int OPERATOR_CONTROLLER_PORT = 2;

}
