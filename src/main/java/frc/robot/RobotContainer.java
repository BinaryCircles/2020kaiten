/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.cameraSubSystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.chassis.Chassis;

public class RobotContainer {

  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  public static Chassis s_chassis;

  public static cameraSubSystem s_camera;

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  public static final XboxController driveController = new XboxController(1);

  public RobotContainer() {
    s_chassis = new Chassis();

    configureButtonBindings();
  }

  private void configureButtonBindings() {
    new JoystickButton(driveController, XboxController.Button.kX.value).whenPressed(s_camera::set_camera2)
    .whenReleased(s_camera::set_camera1);
  }

  //inline comment for camera

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
