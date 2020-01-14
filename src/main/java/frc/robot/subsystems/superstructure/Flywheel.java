/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.superstructure;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Flywheel extends SubsystemBase {

  // define variables
  private final TalonSRX flywheelMain;
  private final VictorSPX flywheelSecondary;

  private final PIDController pidController;

  // constructor
  public Flywheel() {

    // instantiate motors
    flywheelMain = new TalonSRX(Constants.Flywheel.MAIN);
    flywheelSecondary = new VictorSPX(Constants.Flywheel.SECONDARY);

    // configure motor controllers
		flywheelMain.configFactoryDefault();

    // config the peak and nominal outputs ([-1, 1] represents [-100, 100]%)
		flywheelMain.configNominalOutputForward(0, Constants.FLYWHEEL_CONFIG_TIMEOUT);
		flywheelMain.configNominalOutputReverse(0, Constants.FLYWHEEL_CONFIG_TIMEOUT);
		flywheelMain.configPeakOutputForward(1, Constants.FLYWHEEL_CONFIG_TIMEOUT);
    flywheelMain.configPeakOutputReverse(-1, Constants.FLYWHEEL_CONFIG_TIMEOUT);

    flywheelSecondary.follow(flywheelMain);

    // set PIDF constants

		/* Config the peak and nominal outputs ([-1, 1] represents [-100, 100]%) */
		flywheelMain.configNominalOutputForward(0, Constants.Flywheel.CONFIG_TIMEOUT);
		flywheelMain.configNominalOutputReverse(0, Constants.Flywheel.CONFIG_TIMEOUT);
		flywheelMain.configPeakOutputForward(1, Constants.Flywheel.CONFIG_TIMEOUT);
        flywheelMain.configPeakOutputReverse(-1, Constants.Flywheel.CONFIG_TIMEOUT);

    flywheelSecondary.follow(flywheelMain);

    // Set PIDF constants
    flywheelMain.config_kF(Constants.Flywheel.kPIDLoopIdx, Constants.Flywheel.kF, Constants.Flywheel.CONFIG_TIMEOUT);
		flywheelMain.config_kP(Constants.Flywheel.kPIDLoopIdx, Constants.Flywheel.kP, Constants.Flywheel.CONFIG_TIMEOUT);
		flywheelMain.config_kI(Constants.Flywheel.kPIDLoopIdx, Constants.Flywheel.kI, Constants.Flywheel.CONFIG_TIMEOUT);
		flywheelMain.config_kD(Constants.Flywheel.kPIDLoopIdx, Constants.Flywheel.kD, Constants.Flywheel.CONFIG_TIMEOUT);
  }

  public void shoot() {
    // Set the flywheel's speed using the talon's built in PID controller
    // It requires it to be in ticks per 100 milliseconds
    flywheelMain.set(ControlMode.Velocity, Constants.Flywheel.SPEED * Constants.Flywheel.TICKS_PER_ROTATION
       * Constants.Flywheel.SETPOINT_CONSTANT);
  }

  public void stop() {
    flywheelMain.set(ControlMode.Velocity, 0);
  }

  @Override
  public void periodic() {

  }

}
