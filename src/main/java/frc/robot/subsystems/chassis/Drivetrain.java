/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.chassis;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.*;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
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
  private DifferentialDriveOdometry driveOdometry;

  public static double maxSpeed, maxAngularSpeed;
  private static double trackWidth, wheelRadius;
  private static int encoderResolution;

  private Encoder leftEncoder, rightEncoder;
  private AHRS navx;

  private PIDController leftController, rightController;

  // constructor
  public Drivetrain() {

    // reset
    navx.reset();

    // constants
    CANSparkMaxLowLevel.MotorType brushless = CANSparkMaxLowLevel.MotorType.kBrushless;
    driveInverted = false;

    maxSpeed = Constants.Drivetrain.maxSpeed;
    maxAngularSpeed = Constants.Drivetrain.maxAngularSpeed;
    trackWidth = Constants.Drivetrain.trackWidth;
    wheelRadius = Constants.Drivetrain.wheelRadius;
    encoderResolution = Constants.Drivetrain.encoderResolution;

    leftEncoder = new Encoder(Constants.Drivetrain.leftEncoder_a, Constants.Drivetrain.leftEncoder_b);
    rightEncoder = new Encoder(Constants.Drivetrain.rightEncoder_a, Constants.Drivetrain.rightEncoder_b);

    navx = new AHRS(SPI.Port.kMXP);

    leftController = new PIDController(Constants.Drivetrain.left_kP, Constants.Drivetrain.left_kI, Constants.Drivetrain.right_kD);
    leftController = new PIDController(Constants.Drivetrain.right_kP, Constants.Drivetrain.right_kI, Constants.Drivetrain.right_kD);

    leftEncoder.setDistancePerPulse(2 * Math.PI * wheelRadius / encoderResolution);
    rightEncoder.setDistancePerPulse(2 * Math.PI * wheelRadius / encoderResolution);

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
    driveOdometry = new DifferentialDriveOdometry(getAngle());

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

  // open loop curve drive method
  public void curveDrive(double linearVelocity, double angularVelocity, boolean isQuickTurn) {
    differentialDrive.curvatureDrive(linearVelocity, angularVelocity, isQuickTurn);
  }

  public void closedCurveDrive(double linearVelocity, double angularVelocity, boolean isQuickTurn) {

    ChassisSpeeds chassisSpeeds;
    DifferentialDriveWheelSpeeds wheelSpeeds;
    double leftOutput, rightOutput;

    if (!isQuickTurn) {
      chassisSpeeds = new ChassisSpeeds(linearVelocity, 0, angularVelocity);
      wheelSpeeds = driveKinematics.toWheelSpeeds(chassisSpeeds);

      leftOutput = leftController.calculate(leftEncoder.getRate(), wheelSpeeds.leftMetersPerSecond);
      rightOutput = rightController.calculate(rightEncoder.getRate(), wheelSpeeds.rightMetersPerSecond);
    } else {
      leftOutput = angularVelocity;
      rightOutput = -angularVelocity;
    }

    front_left.set(leftOutput);
    front_right.set(rightOutput);
  }

  // get angle from gyro
  public Rotation2d getAngle() {
    return Rotation2d.fromDegrees(-navx.getAngle());
  }

  public void updateOdometry() {
    driveOdometry.update(getAngle(), leftEncoder.getDistance(), rightEncoder.getDistance());
  }

  public Pose2d getPose() {
    return driveOdometry.getPoseMeters();
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(leftEncoder.getRate(), rightEncoder.getRate());
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    front_left.setVoltage(leftVolts);
    front_right.setVoltage(-rightVolts);
  }

  @Override
  public void periodic() {
    updateOdometry();
  }
}
