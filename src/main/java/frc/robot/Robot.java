/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutoDriveForward;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.HatchIntake;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static HatchIntake hatchIntake;
  public static Drivetrain drivetrain;

  public static OI oi;

  public static UsbCamera frontCamera;
	public static UsbCamera backCamera;
	public static VideoSink cameraServer;

  public SendableChooser<Command> autoChooser;


  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {

    hatchIntake = new HatchIntake();
    drivetrain = new Drivetrain();

    oi = new OI();

    autoChooser = new SendableChooser<>();

    autoChooser.setDefaultOption("Drive forward 6 feet", new AutoDriveForward(74));
    autoChooser.addOption("Pass HAB line (lvl. 1)", new AutoDriveForward(48));
    autoChooser.addOption("Pass HAB line (lvl. 2)", new AutoDriveForward(100));
    
    SmartDashboard.putData(autoChooser);

    frontCamera = CameraServer.getInstance().startAutomaticCapture(RobotMap.frontCamera);
		frontCamera.setResolution(40, 40);
		frontCamera.setExposureAuto();
		backCamera = CameraServer.getInstance().startAutomaticCapture(RobotMap.backCamera);
		backCamera.setResolution(40, 40);
		backCamera.setExposureAuto();
		cameraServer = CameraServer.getInstance().getServer();

  
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  @Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
  
    drivetrain.resetEncoders();
    autoChooser.getSelected().start();
  
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    }
  

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
