/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.chassis;

import com.revrobotics.*;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

  // define variables
  private final CANSparkMax front_left, back_left, front_right, back_right;
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

    // instantiate motor controllers
    front_left = new CANSparkMax(Constants.drive_fl, brushless);
    back_left = new CANSparkMax(Constants.drive_bl, brushless);
    front_right = new CANSparkMax(Constants.drive_fr, brushless);
    back_right = new CANSparkMax(Constants.drive_br, brushless);
    differentialDrive = new DifferentialDrive(front_left, front_right);

    // configure motor controllers
    back_left.follow(front_left);
    back_right.follow(front_right);

    front_left.setInverted(driveInverted);
    back_left.setInverted(driveInverted);
    front_right.setInverted(driveInverted);
    back_right.setInverted(driveInverted);

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
