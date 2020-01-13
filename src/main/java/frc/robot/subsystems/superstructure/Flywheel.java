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
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/*
  For a good example on closed-loop control with the Talon,
  see https://github.com/CrossTheRoadElec/Phoenix-Examples-Languages/blob/master/Java/VelocityClosedLoop/src/main/java/frc/robot/ 
*/
public class Flywheel extends SubsystemBase {

  // define variables
  private final TalonSRX flywheelMain;
  private final VictorSPX flywheelSecondary;
  // constructor
  public Flywheel() {

    // instantiate motors
    flywheelMain = new TalonSRX(Constants.FLYWHEEL_MAIN);
    flywheelSecondary = new VictorSPX(Constants.FLYWHEEL_SECONDARY);

    // configure motor controllers
		flywheelMain.configFactoryDefault();
		/* Config the peak and nominal outputs ([-1, 1] represents [-100, 100]%) */
		flywheelMain.configNominalOutputForward(0, Constants.FLYWHEEL_CONFIG_TIMEOUT);
		flywheelMain.configNominalOutputReverse(0, Constants.FLYWHEEL_CONFIG_TIMEOUT);
		flywheelMain.configPeakOutputForward(1, Constants.FLYWHEEL_CONFIG_TIMEOUT);
        flywheelMain.configPeakOutputReverse(-1, Constants.FLYWHEEL_CONFIG_TIMEOUT);

    flywheelSecondary.follow(flywheelMain);

    // Set PIDF constants
    flywheelMain.config_kF(Constants.kPIDLoopIdx, Constants.FLYWHEEL_kF, Constants.FLYWHEEL_CONFIG_TIMEOUT);
		flywheelMain.config_kP(Constants.kPIDLoopIdx, Constants.FLYWHEEL_kP, Constants.FLYWHEEL_CONFIG_TIMEOUT);
		flywheelMain.config_kI(Constants.kPIDLoopIdx, Constants.FLYWHEEL_kI, Constants.FLYWHEEL_CONFIG_TIMEOUT);
		flywheelMain.config_kD(Constants.kPIDLoopIdx, Constants.FLYWHEEL_kD, Constants.FLYWHEEL_CONFIG_TIMEOUT);
  }

  public void shoot() {
    // Set the flywheel's speed using the talon's built in PID controller
    // It requires it to be in ticks per 100 milliseconds
    flywheelMain.set(ControlMode.Velocity, Constants.FLYWHEEL_SPEED * Constants.FLYWHEEL_TICKS_PER_ROTATION
       * Constants.FLYWHEEL_SETPOINT_CONSTANT);
  }

  public void stop() {
    flywheelMain.set(ControlMode.Velocity, 0);
  }

  @Override
  public void periodic() {

  }

}
