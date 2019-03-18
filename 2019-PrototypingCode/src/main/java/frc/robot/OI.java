/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.AutoMoveCargoToPosition;
import frc.robot.commands.ExtendIntake;
//import frc.robot.commands.HatchIntakeDown;
//import frc.robot.commands.HatchIntakeUp;
import frc.robot.commands.RetractIntake;
import frc.robot.commands.SwapDriveDirection;
import frc.robot.commands.SwapIntake;
import frc.robot.commands.VisionCommand;


/**
 * Add your docs here.
 */
public class OI {
    
    private Joystick pilotController;
    private Joystick coPilotController;

    public OI() {
        pilotController = new Joystick(0);
        coPilotController = new Joystick(1);

        /*
        Button copilotButtonA = new JoystickButton(coPilotController, RobotMap.joystickButtonA);
        copilotButtonA.whileHeld(new HatchIntakeDown());
        
        Button copilotButtonY = new JoystickButton(coPilotController, RobotMap.joystickButtonY);
        copilotButtonY.whileHeld(new HatchIntakeUp());
        */

        Button copilotLeftTrigger = new JoystickButton(coPilotController, RobotMap.joystickLeftBumper);
        copilotLeftTrigger.whenPressed(new SwapIntake());

        Button copilotButtonY = new JoystickButton(coPilotController, RobotMap.joystickButtonY);
        copilotButtonY.whenPressed(new ExtendIntake(1.75));

        Button copilotButtonB = new JoystickButton(coPilotController, RobotMap.joystickButtonB);
        copilotButtonB.whenPressed(new ExtendIntake(1.6));
        
        Button copilotButtonA = new JoystickButton(coPilotController, RobotMap.joystickButtonA);
        copilotButtonA.whenPressed(new RetractIntake(0.94));

        Button copilotButtonX = new JoystickButton(coPilotController, RobotMap.joystickButtonX);
        copilotButtonX.whenPressed(new AutoMoveCargoToPosition(Robot.cargoIntake.getOffset()));

        Button pilotButtonY = new JoystickButton(pilotController, RobotMap.joystickButtonY);
        pilotButtonY.whenPressed(new SwapDriveDirection());

        Button pilotButtonA = new JoystickButton(pilotController, RobotMap.joystickButtonA);
        pilotButtonA.toggleWhenPressed(new VisionCommand());

        
        
        
        copilotButtonA.close();
        copilotButtonY.close();
        copilotButtonX.close();
        copilotButtonB.close();
        copilotLeftTrigger.close();
        pilotButtonY.close();
        pilotButtonA.close();

    }

    public Joystick getPilotController() {
        return pilotController;
    }
    public Joystick getcoPilotController() {
        return coPilotController;
    }
}
