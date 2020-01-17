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

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/*
  This is a closed-loop flywheel that uses two motor controllers
  (a talon and a victor) and the WPIlib PID controller
  as opposed to the Talon's built-in PID controller).
*/
public class Flywheel extends SubsystemBase {

  private final TalonSRX flywheelMain;
  private final VictorSPX flywheelSecondary;
  private final PIDController pidController;
  Encoder encoderMain;
  
  public Flywheel() {
    // instantiate motors
    flywheelMain = new TalonSRX(Constants.Flywheel.MAIN);
    flywheelSecondary = new VictorSPX(Constants.Flywheel.SECONDARY);

    // Set motor controll
		flywheelMain.configFactoryDefault();
		/* Config the peak and nominal outputs ([-1, 1] represents [-100, 100]%) */
		flywheelMain.configNominalOutputForward(0, Constants.Flywheel.CONFIG_TIMEOUT);
		flywheelMain.configNominalOutputReverse(0, Constants.Flywheel.CONFIG_TIMEOUT);
		flywheelMain.configPeakOutputForward(1, Constants.Flywheel.CONFIG_TIMEOUT);
        flywheelMain.configPeakOutputReverse(-1, Constants.Flywheel.CONFIG_TIMEOUT);

    flywheelSecondary.follow(flywheelMain);

    // Encoder takes 2 ports
    encoderMain = new Encoder(Constants.Flywheel.ENCODER_A, 
      Constants.Flywheel.ENCODER_B, Constants.Flywheel.ENCODER_REVERSE_DIRECTION);

    // Set PIDF constants
    pidController = new PIDController(Constants.Flywheel.kP, Constants.Flywheel.kI, Constants.Flywheel.kD);

  }

  public void shoot() {
    // Set the flywheel's speed using the talon's built in PID controller
    // It requires it to be in ticks per 100 milliseconds
    flywheelMain.set(ControlMode.PercentOutput, 
      pidController.calculate(encoderMain.getRate(), Constants.Flywheel.TARGET_SPEED) + Constants.Flywheel.kF);
  }

  public void stop() {
    flywheelMain.set(ControlMode.Velocity, 0);
    encoderMain.close();
  }

  @Override
  // this method is as empty as my DM's
  public void periodic() {

  }

}
