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
import frc.robot.subsystems.ElevatorClimb;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.V4BClimb;
import frc.robot.subsystems.chassis.Chassis;

public class RobotContainer {

  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  public static Chassis s_chassis;
  public static ElevatorClimb s_climb;

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  public static final XboxController driveController = new XboxController(1);
  public static final XboxController operatorController = new XboxController(2);

  public RobotContainer() {
    s_chassis = new Chassis();
    s_climb = new ElevatorClimb();

    configureButtonBindings();
  }

  private void configureButtonBindings() {
    new JoystickButton(operatorController, XboxController.Button.kA.value).whenPressed(s_climb::startDeployClimbArm)
            .whenReleased(s_climb::stopDeployClimbArm);
    new JoystickButton(operatorController, XboxController.Button.kB.value).whenPressed(s_climb::startWinch)
            .whenReleased(s_climb::stopWinch);
    new JoystickButton(operatorController, operatorController.getPOV(0)).whenPressed(() -> s_climb.calculateInput(5));
    new JoystickButton(operatorController, operatorController.getPOV(90)).whenPressed(() -> s_climb.calculateInput(4));
    new JoystickButton(operatorController, operatorController.getPOV(270)).whenPressed(() -> s_climb.calculateInput(3));
    new JoystickButton(operatorController, operatorController.getPOV(180)).whenPressed(s_climb::dropElevator);
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
