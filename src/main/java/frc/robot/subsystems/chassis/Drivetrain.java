/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.chassis;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

  // define variables
  private CANSparkMax front_left, back_left, front_right, back_right;
  //private final WPI_TalonSRX front_left, front_right;
  //private final WPI_VictorSPX back_left, back_right;
  private CANSparkMaxLowLevel.MotorType brushless;
  private boolean driveInverted;
  private int currentLimitAmps;
  private DifferentialDrive differentialDrive;
  public static DifferentialDriveKinematics driveKinematics;

  // constructor
  public Drivetrain() {

    // configuration constants
    brushless = CANSparkMaxLowLevel.MotorType.kBrushless;
    driveInverted = false;
    currentLimitAmps = 40;

    // instantiate motor controllers (spark maxes)
    front_left = new CANSparkMax(Constants.Drivetrain.DRIVE_FL, brushless);
    back_left = new CANSparkMax(Constants.Drivetrain.DRIVE_BL, brushless);
    front_right = new CANSparkMax(Constants.Drivetrain.DRIVE_FR, brushless);
    back_right = new CANSparkMax(Constants.Drivetrain.DRIVE_BR, brushless);

    // victors/talons
    /*front_left = new WPI_TalonSRX(Constants.drive_fl);
    //back_left = new WPI_VictorSPX(Constants.drive_bl);
    front_right = new WPI_TalonSRX(Constants.drive_fr);
    //back_right = new WPI_VictorSPX(Constants.drive_br);*/

    differentialDrive = new DifferentialDrive(front_left, front_right);
    driveKinematics = new DifferentialDriveKinematics(Constants.Drivetrain.trackWidth);

    // configure motor controllers
    back_left.follow(front_left);
    back_right.follow(front_right);

    front_left.setInverted(driveInverted);
    back_left.setInverted(driveInverted);
    front_right.setInverted(driveInverted);
    back_right.setInverted(driveInverted);

    /*front_left.setSafetyEnabled(false);
    front_right.setSafetyEnabled(false);
    back_left.setSafetyEnabled(false);
    back_right.setSafetyEnabled(false);*/

    front_left.setSmartCurrentLimit(Constants.Drivetrain.CURRENT_LIMIT);
    back_left.setSmartCurrentLimit(Constants.Drivetrain.CURRENT_LIMIT);
    front_right.setSmartCurrentLimit(Constants.Drivetrain.CURRENT_LIMIT);
    back_right.setSmartCurrentLimit(Constants.Drivetrain.CURRENT_LIMIT);

  }

  // curve drive method
  public void curveDrive(double linearVelocity, double angularVelocity, boolean isQuickTurn) {
    differentialDrive.curvatureDrive(linearVelocity, angularVelocity, isQuickTurn);
  }

  @Override
  public void periodic() {

  }
}
