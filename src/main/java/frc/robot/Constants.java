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

    // flywheel
    public static final int FLYWHEEL_MAIN = 2;
    public static final int FLYWHEEL_SECONDARY = 3;

    // drivetrain current limiting
    public static final int CURRENT_LIMIT_AMPS_DRIVE = 40;

    // flywheel current limiting
    public static final int CURRENT_LIMIT_AMPS_FLYWHEEL = 40;

    // flywheel speed
    public static final int FLYWHEEL_SPEED = 1;

    // controller IDs
    public static final int DRIVE_CONTROLLER_PORT = 1;
    public static final int OPERATOR_CONTROLLER_PORT = 2;

}
