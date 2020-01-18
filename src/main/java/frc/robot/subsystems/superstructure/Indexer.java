/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.superstructure;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.cuforge.libcu.Lasershark;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Indexer extends SubsystemBase {

  // define variables
  private static WPI_VictorSPX indexerMotor;
  private static Lasershark indexerLIDAR;
  public static int ballCount = Constants.INITIAL_BALL_COUNT;
  public static boolean ballInIntake = false;
  public static boolean indexerFull = false;
  public static double currentDistance;
  public static double distanceDerivative;
  public static double lastDistance = 0;

  // constructor
  public Indexer() {

    // instantiate objects
    indexerMotor = new WPI_VictorSPX(Constants.INDEXER_MOTOR);
    indexerLIDAR = new Lasershark(Constants.INDEXER_LIDAR);

  }

  // input balls into indexer with automatic ball detection
  public void runIndexerAuto() {

    currentDistance = indexerLIDAR.getDistanceInches();
    distanceDerivative = currentDistance - lastDistance;

    // at the fifth ball, run until its intook and then stop the belt
    if (indexerFull) {

      // once the derivative changes (a new ball enters) slow the belt
      slowIndexer();

    // if a ball is pressed against the entrance of the intake
    } else if (ballInIntake) {
      indexerMotor.set(Constants.INDEXER_SPEED); // run the belt
    }

    // increment ball count when the peak of a ball is detected
    if (currentDistance < Constants.INDEXER_MIN_HEIGHT) {
      ballCount++;
    }

    // output capacity to shuffleboard
    SmartDashboard.putBoolean("indexer full", indexerFull);

  }

  // run the indexer slowly for the last ball
  public void slowIndexer() {
    indexerMotor.set(Constants.INDEXER_SPEED / 2);
  }

  // reverse the indexer
  public void reverseIndexer() {
    indexerMotor.set(-Constants.INDEXER_SPEED);

    // reduce ball count
    if (currentDistance < Constants.INDEXER_MIN_HEIGHT) {
      ballCount--;
    }
  }

  // stop the indexer
  public void stopIndexer() {
    indexerMotor.set(0);
  }

  @Override
  public void periodic() {


    // if the distance is less than max range, a ball is ready to be indexed
    if (currentDistance < Constants.INDEXER_HEIGHT) {
      ballInIntake = true;
    } else {
      ballInIntake = false;
    }

    // check if indexer is full
    if (ballCount == 5) {
      indexerFull = true;
    } else {
      indexerFull = false;
    }

  }
}
