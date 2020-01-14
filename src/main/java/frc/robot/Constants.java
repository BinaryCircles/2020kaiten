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

    public final class Flywheel {
        public static final int MAIN = 2;
        public static final int SECONDARY = 3;

        // measured in rotations per minute
        public static final int SPEED = 60;

        // We need this because the talon has different PID slots
        public static final int kPIDLoopIdx = 0;
        
        // Don't try to tune these! These are constants that are
        // stated in the documentation so that we can easily enter
        // rotaitons per minute.
        public static final int TICKS_PER_ROTATION = 4096;
        public static final double SETPOINT_CONSTANT = 0.001667; // 100 milliseconds / 1 min

        // PID Constants for closed loop flywheel
        public static final double kP = 1;
        public static final double kI = 0;
        public static final double kD = 0;
        public static final double kF = 0;
        public static final double kA = 0;
        /**
         * Timeout value (in milliseconds) generally used in parameter configs
         * Non-zero to block the config until success, zero to skip checking 
         */
        public static final int CONFIG_TIMEOUT = 30;
    }
    // flywheel


    // drivetrain current limiting
    public static final int CURRENT_LIMIT_AMPS_DRIVE = 40;

    // controller IDs
    public static final int DRIVE_CONTROLLER_PORT = 1;
    public static final int OPERATOR_CONTROLLER_PORT = 2;
	

}
