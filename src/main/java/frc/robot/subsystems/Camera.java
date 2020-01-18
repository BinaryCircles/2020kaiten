/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.cscore.VideoSource.ConnectionStrategy;
public class Camera extends SubsystemBase {

  // uses two cameras that can be toggled between
  UsbCamera camera1;
  UsbCamera camera2;

  // server that accepts input from the camera
  VideoSink server;

  public Camera() {
    // initialize camera
    camera1 = CameraServer.getInstance().startAutomaticCapture(Constants.camera.camera1_dev_num);
    camera2 = CameraServer.getInstance().startAutomaticCapture(Constants.camera.camera2_dev_num);

    // initialize server
    server = CameraServer.getInstance().getServer();

    // set resolution for the two cameras
    camera1.setResolution(Constants.camera.camera1_resolution_x,Constants.camera.camera1_resolution_y);
    camera2.setResolution(Constants.camera.camera2_resolution_x,Constants.camera.camera2_resolution_y);

    // when toggling between cameras doesn't turn the other camera's connect off
    camera1.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
    camera2.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
  }

  // set camera1
  public void set_camera1(){
    server.setSource(camera1);
  }

  // set camera2
  public void set_camera2(){
    server.setSource(camera2);
  }


  @Override
  public void periodic() { }
  
}
