/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.chassis;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.chassis.Drivetrain;

public class Drive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  // define variables
  private final Drivetrain s_drive;
  private final XboxController driveController;

  private double linearVelocity, angularVelocity;
  private double isQuickTurn;

  // constructor
  public Drive(Drivetrain subsystem) {
    s_drive = subsystem;
    driveController = RobotContainer.driveController;
    addRequirements(subsystem);
    s_drive.setDefaultCommand(this);
  }

  @Override
  public void execute() {

    linearVelocity = RobotContainer.getTriggerOutput(driveController);
    angularVelocity = RobotContainer.getLeftJoystickAxis(driveController);

    // open loop method
    //s_drive.curveDrive(linearVelocity, angularVelocity, driveController.getXButton());

    // closed loop method
    s_drive.closedCurveDrive(linearVelocity, angularVelocity, driveController.getXButton());

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
