/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.superstructure;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Flywheel extends SubsystemBase {

  // define variables
  private final CANSparkMax flywheelMain, flywheelSecondary;

  // constructor
  public Flywheel() {

    // configuration
    CANSparkMaxLowLevel.MotorType brushless = CANSparkMaxLowLevel.MotorType.kBrushless;

    // instantiate motors
    flywheelMain = new CANSparkMax(Constants.FLYWHEEL_MAIN, brushless);
    flywheelSecondary = new CANSparkMax(Constants.FLYWHEEL_SECONDARY, brushless);

    // configure motor controllers
    flywheelMain.setSmartCurrentLimit(Constants.CURRENT_LIMIT_AMPS_FLYWHEEL);
    flywheelSecondary.setSmartCurrentLimit(Constants.CURRENT_LIMIT_AMPS_FLYWHEEL);

    flywheelSecondary.follow(flywheelMain);

  }

  public void shoot() {
    flywheelMain.set(Constants.FLYWHEEL_SPEED);
  }

  public void stop() {
    flywheelMain.set(0);
  }

  @Override
  public void periodic() {

  }
}
