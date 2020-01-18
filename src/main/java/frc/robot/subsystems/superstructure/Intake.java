/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.superstructure;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.Constants;
import frc.robot.RobotContainer;


public class Intake extends SubsystemBase {

  //define variables
  private final WPI_TalonSRX intake;
  private final DoubleSolenoid intakeArm;

  //constructor
  public Intake() {

    // instantiate objects
    intake = new  WPI_TalonSRX(Constants.INTAKE_MAIN);
    intakeArm = new DoubleSolenoid(Constants.INTAKE_ARM_FRONT,Constants.INTAKE_ARM_BACK);

    // configure motor controllers
    intake.setSafetyEnabled(false);

  }

  public void startVore() {
    if (!RobotContainer.s_indexer.indexerFull) {
      intakeArm.set(DoubleSolenoid.Value.kForward);
      intake.set(Constants.INTAKE_SPEED);
    } else {
      stopVore();
    }
  }

  public void stopVore() {
    intakeArm.set(DoubleSolenoid.Value.kReverse);
    intake.set(0);
  }

  @Override
  public void periodic() { }

}
