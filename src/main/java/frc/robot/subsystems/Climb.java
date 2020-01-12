/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.ArmFeedforward;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDSubsystem;
import frc.robot.Constants;

public class Climb extends ProfiledPIDSubsystem {

  // initialize the motors
  private final WPI_TalonSRX deployTalon;
  private final WPI_TalonSRX winchTalon;
  private final WPI_VictorSPX deployVictor;
  private final WPI_VictorSPX winchVictor;
  private final Encoder climbArmEncoder;
  private double lastError;
  private double error;
  private double diffError;
  ProfiledPIDController controller;
  private final ArmFeedforward climbArmFeedForward;

  // constructor
  public Climb()  {
    super(new ProfiledPIDController(Constants.ARM_kP, Constants.ARM_kI, Constants.ARM_kD,
            new TrapezoidProfile.Constraints(Constants.MAX_VELOCITY_RAD_PER_SEC, Constants.MAX_ACCEL)), 0);
    controller = new ProfiledPIDController(Constants.ARM_kP, Constants.ARM_kI, Constants.ARM_kD,
            new TrapezoidProfile.Constraints(Constants.MAX_VELOCITY_RAD_PER_SEC, Constants.MAX_ACCEL));
    // define the motor controllers
    deployTalon = new WPI_TalonSRX(Constants.DEPLOY_TALON);
    deployVictor = new WPI_VictorSPX(Constants.DEPLOY_VICTOR);
    winchTalon = new WPI_TalonSRX(Constants.WINCH_TALON);
    winchVictor = new WPI_VictorSPX(Constants.WINCH_VICTOR);

    // set victors to follow talons
    deployVictor.follow(deployTalon);
    winchVictor.follow(winchTalon);

    // initialize encoder
    climbArmEncoder = new Encoder(Constants.CLIMB_DEPLOY_DIO, Constants.CLIMB_DEPLOY_DIO2);

    // initialize feedforward
    climbArmFeedForward = new ArmFeedforward(Constants.kS, Constants.kV, Constants.kA);

    // set dpp of encoder
    climbArmEncoder.setDistancePerPulse(Constants.DISTANCE_PER_PULSE);

    lastError = 0;
    error = 0;
    diffError = 0;
  }

  /* Basic PID loop
  public void climbArmDeployPID(double input) {
    while(Math.abs(error) > 5) {
      error = input - climbArmEncoder.get();
      diffError = error - lastError;
      double output = Constants.ARM_kP * error + Constants.ARM_kD * diffError + Constants.ARM_kF_GRAV;
      deployTalon.set(output);
      lastError = error;
    }
  } */

  public void calculateInput(double input) {
    double output = (Math.asin(input/(Constants.CLIMB_ARM_LENGTH_1 + Constants.CLIMB_ARM_LENGTH_2)));
    goToSetpoint(output);
  }

  // theoretically the way to apply the ProfiledPIDController
  public void goToSetpoint(double goalPose) {
    double calculateChange = controller.calculate(climbArmEncoder.getDistance(), goalPose);
    double climbFF = climbArmFeedForward.calculate(controller.getSetpoint().position,
            controller.getSetpoint().velocity);
    deployTalon.setVoltage(calculateChange + climbFF);
  }

  @Override
  public void useOutput(double output, TrapezoidProfile.State setpoint) {

  }

  @Override
  public double getMeasurement() {
    return climbArmEncoder.getDistance() + Constants.ARM_OFFSET;
  }



  public void startDeployClimbArm() {
    deployTalon.set(Constants.DEPLOY_SPEED);
}

  public void stopDeployClimbArm() {
    deployTalon.set(0);
  }

  public void startWinch() {
    winchTalon.set(Constants.WINCH_SPEED);
  }

  public void stopWinch() {
    winchTalon.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
