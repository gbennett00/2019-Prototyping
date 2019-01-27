/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

public class ExtendHatchIntake extends PIDCommand {
  
  private double targetRotations;
  
  public ExtendHatchIntake(double rotations) {
    super(0.5,0,0);
    requires(Robot.hatchIntake);

    getPIDController().setAbsoluteTolerance(0.1);
    getPIDController().setSetpoint(rotations);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    super.initialize();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    getPIDController().enable();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return getPIDController().onTarget();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.hatchIntake.hatchExtensionMotor.set(0);
    getPIDController().disable();
    super.end();
  }

 @Override
  protected double returnPIDInput() {
    targetRotations = Robot.hatchIntake.getExtensionEncoderPosition() / 4096;
    
    return targetRotations;
  }

  @Override
  protected void usePIDOutput(double output) {
    output = Math.min(output, 0.5);
    output = Math.max(output, -0.5);

    Robot.hatchIntake.extendIntake(output);

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
