/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.chassis.Chassis;
import frc.robot.subsystems.superstructure.Flywheel;
import frc.robot.subsystems.superstructure.Indexer;
import frc.robot.subsystems.superstructure.Intake;

public class RobotContainer {

  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  public static Chassis s_chassis;
  public static Flywheel s_flywheel;
  public static Intake s_intake;
  public static Indexer s_indexer;

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  public static final XboxController driveController = new XboxController(Constants.DRIVE_CONTROLLER_PORT);
  public static final XboxController operatorController = new XboxController(Constants.OPERATOR_CONTROLLER_PORT);

  public RobotContainer() {
    s_chassis = new Chassis();
    s_flywheel = new Flywheel();
    s_intake = new Intake();
    s_indexer = new Indexer();

    configureButtonBindings();
  }

  private void configureButtonBindings() {

    // inline command to shoot/stop shooting
    new JoystickButton(operatorController, XboxController.Button.kX.value)
            .whenPressed(s_flywheel::shoot)
            .whenReleased(s_flywheel::stop);

    // actuate intake and indexer in parallel
    new JoystickButton(operatorController, XboxController.Button.kA.value)
            .whenPressed(new ParallelCommandGroup(
                    new InstantCommand(s_intake::startVore),
                    new RunCommand(s_indexer::runIndexerAuto, s_indexer)
            ))
            .whenReleased(new ParallelCommandGroup(
                    new InstantCommand(s_intake::stopVore),
                    new RunCommand(s_indexer::stopIndexer, s_indexer)
            ));

    // reverse indexer
    new JoystickButton(operatorController, XboxController.Button.kB.value)
            .whenPressed(new RunCommand(s_indexer::reverseIndexer, s_indexer))
            .whenReleased(new RunCommand(s_indexer::stopIndexer, s_indexer));

  }

  public static double getTriggerOutput(XboxController controller) {
    return Math.pow(controller.getTriggerAxis(GenericHID.Hand.kRight) - controller.getTriggerAxis(GenericHID.Hand.kLeft), 3);
  }

  public static double getLeftJoystickAxis(XboxController controller) {
    return controller.getX(GenericHID.Hand.kLeft);
  }

  public static double getRightJoystickAxis(XboxController controller) {
    return controller.getX(GenericHID.Hand.kRight);
  }

  public Command getAutonomousCommand() {
    return m_autoCommand;
  }
}
