/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.chassis.Chassis;
import frc.robot.subsystems.superstructure.Flywheel;

public class RobotContainer {

  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  public static Chassis s_chassis;
  public static Flywheel s_flywheel;

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  public static final XboxController driveController = new XboxController(Constants.DRIVE_CONTROLLER_PORT);
  public static final XboxController operatorController = new XboxController(Constants.OPERATOR_CONTROLLER_PORT);

  public RobotContainer() {
    s_chassis = new Chassis();
    s_flywheel = new Flywheel();

    configureButtonBindings();
  }

  private void configureButtonBindings() {

    // create inline command to shoot/stop shooting
    new JoystickButton(operatorController, XboxController.Button.kX.value).whenPressed(s_flywheel::enable)
            .whenReleased(s_flywheel::disable);


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
