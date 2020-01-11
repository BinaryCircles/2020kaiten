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
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

  // define variables
  private final CANSparkMax front_left, back_left, front_right, back_right;
  //private final WPI_TalonSRX front_left, front_right;
  //private final WPI_VictorSPX back_left, back_right;
  private final CANSparkMaxLowLevel.MotorType brushless;
  private final boolean driveInverted;
  private final int currentLimitAmps;
  private final DifferentialDrive differentialDrive;

  // constructor
  public Drivetrain() {

    // configuration constants
    brushless = CANSparkMaxLowLevel.MotorType.kBrushless;
    driveInverted = false;
    currentLimitAmps = 40;

    // instantiate motor controllers (spark maxes)
    front_left = new CANSparkMax(Constants.drive_fl, brushless);
    back_left = new CANSparkMax(Constants.drive_bl, brushless);
    front_right = new CANSparkMax(Constants.drive_fr, brushless);
    back_right = new CANSparkMax(Constants.drive_br, brushless);

    // victors/talons
    /*front_left = new WPI_TalonSRX(Constants.drive_fl);
    //back_left = new WPI_VictorSPX(Constants.drive_bl);
    front_right = new WPI_TalonSRX(Constants.drive_fr);
    //back_right = new WPI_VictorSPX(Constants.drive_br);*/

    differentialDrive = new DifferentialDrive(front_left, front_right);

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

    front_left.setSmartCurrentLimit(currentLimitAmps);
    back_left.setSmartCurrentLimit(currentLimitAmps);
    front_right.setSmartCurrentLimit(currentLimitAmps);
    back_right.setSmartCurrentLimit(currentLimitAmps);

  }

  // curve drive method
  public void curveDrive(double linearVelocity, double angularVelocity, boolean isQuickTurn) {
    differentialDrive.curvatureDrive(linearVelocity, angularVelocity, isQuickTurn);
  }

  @Override
  public void periodic() {

  }
}
