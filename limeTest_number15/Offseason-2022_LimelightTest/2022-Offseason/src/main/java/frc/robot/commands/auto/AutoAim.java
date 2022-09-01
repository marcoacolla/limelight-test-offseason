// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class AutoAim extends CommandBase {
  /** Creates a new AutoAim. */
  private final DriveTrain dt;
  private final Timer timer = new Timer();

  private double ty;
  private double tx;
  private double tv;
  private double conDist = -0.1;
  private double conAim = -0.1;
  private double min_aim_command = 0.03;
  private double aimError;
  private double distError;
  private double aimAdjust;
  private double disAdjust;

  public AutoAim(DriveTrain dt, double tx, double ty, double tv){
    addRequirements(dt);
    this.dt = dt;
    this.ty = ty;
    this.tx = tx;
    this.tv = tv;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override

 
  public void execute() {
    if(tv == 0){
      dt.arcadeDrive(0.0, 0.5);
    }else{ 
      aimError = -tx;
      distError = -ty;
      if(tx > 1){
        aimAdjust = aimError*conAim + min_aim_command;
      }else if(tx < -1){
        aimAdjust = aimError*conAim - min_aim_command;
      }
      disAdjust = distError*conDist;
  
      dt.arcadeDrive(disAdjust, aimAdjust);
      /*
      if(tx > 1){
        dt.arcadeDrive(0, 0.75);
      }else if(tx < -1){
        dt.arcadeDrive(0, -0.75);
      }
      // se o lime tá muito em cima, ele anda pra trás
      if(ty > 26){
        dt.arcadeDrive(-0.75, 0);
      }
      // se o lime tá muito embaixo, ele anda pra frente
      else if(ty < 24){
        dt.arcadeDrive(0.75, 0);
      }
      */
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.get() > 15;
  }
}
