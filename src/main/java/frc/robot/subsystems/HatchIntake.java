/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.*;

/**
 * Add your docs here.
 */
public class HatchIntake extends Subsystem {
  
  public WPI_TalonSRX hatchIntakeMotor = new WPI_TalonSRX(RobotMap.hatchIntakeMotor);
  public WPI_TalonSRX hatchExtensionMotor = new WPI_TalonSRX(RobotMap.hatchExtensionMotor);


  @Override
  public void initDefaultCommand() {
   
  }

  public void intakeUp() {
    Robot.hatchIntake.hatchIntakeMotor.set(-.8);
  }

  public void intakeDown() {
    Robot.hatchIntake.hatchIntakeMotor.set(.8);
  }

  public void intakeStop() {
    Robot.hatchIntake.hatchIntakeMotor.set(0);
  }

  public void resetEncoder(){
    hatchExtensionMotor.setSelectedSensorPosition(0, 0, 0);
  }

  public double getExtensionEncoderPosition(){
    return hatchExtensionMotor.getSelectedSensorPosition(0);
  }

  public void extendIntake(double speed){
    Robot.hatchIntake.hatchExtensionMotor.set(speed);
  }

  public void retractIntake(double speed){
    Robot.hatchIntake.hatchExtensionMotor.set(speed);
  }


}
