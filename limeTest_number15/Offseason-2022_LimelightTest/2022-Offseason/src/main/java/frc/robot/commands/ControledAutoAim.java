// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class ControledAutoAim extends CommandBase {
  /** Creates a new ControledAutoAim. */

  private double tx;
  private double ty;
  private double tv;
  private final DriveTrain dt;
  private final double propHead = -0.03;
  private final double propDist = -0.03;
  private final double min_command = 0.1;

  public ControledAutoAim(DriveTrain dt, double tx, double ty, double tv) {
    addRequirements(dt);
    this.dt = dt;
    this.tx = tx;
    this.ty = ty;
    this.tv = tv;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(tv == 0){
      dt.arcadeDrive(0.0, 0.5);
    }else if(tv == 1){
      double heading_error = -tx;
      double dist_error = -ty;
      double heading_adjust = 0;


      if(tx > 1){
        heading_adjust = propHead * heading_error - min_command;
      }else if(tx < -1){ 
        heading_adjust = propHead * heading_error + min_command;
      }

      double distance_adjust = propDist * dist_error;
      
      dt.arcadeDrive(distance_adjust, heading_adjust);
    }
   
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
