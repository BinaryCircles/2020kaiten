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

  public static final int CURRENT_LIMIT_AMPS_DRIVE = 40;

  // flywheel
  public static final int FLYWHEEL_MAIN = 2;
  public static final int FLYWHEEL_SECONDARY = 3;

  public static final int CURRENT_LIMIT_AMPS_FLYWHEEL = 40;
  public static final double FLYWHEEL_SPEED = 1;

  // indexer
  public static final int INDEXER_MOTOR = 11;
  public static final int INDEXER_LIDAR = 12;

  public static final double INDEXER_SPEED = 0.2;
  public static final double INDEXER_HEIGHT = 6.8; // distance at which ball is undetected
  public static final double INDEXER_MIN_HEIGHT = 0.6; // peak height of ball in indexer

  // intake
  public static final int INTAKE_MAIN = 6;

  public static final int INTAKE_ARM_FRONT = 11;
  public static final int INTAKE_ARM_BACK = 12;

  public static final double INTAKE_SPEED = 1;

  // controller IDs
  public static final int DRIVE_CONTROLLER_PORT = 1;
  public static final int OPERATOR_CONTROLLER_PORT = 2;

  // initial values
  public static final int INITIAL_BALL_COUNT = 0;

}
