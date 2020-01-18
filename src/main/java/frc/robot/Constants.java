/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

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

}
