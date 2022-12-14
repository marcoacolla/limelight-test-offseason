// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.StorageSystem;

public class AutoStorage extends CommandBase {

  private final StorageSystem storageSystem;
  private double speed;
  private double time;
  private Timer timer = new Timer();

  public AutoStorage(StorageSystem storageSystem, double speed, double time) {
    this.speed = speed;
    this.time = time;
    this.storageSystem = storageSystem;
    addRequirements(storageSystem);
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  @Override
  public void execute() {
    storageSystem.activate(speed);
  }

  @Override
  public void end(boolean interrupted) {
    storageSystem.stop();
    timer.stop();
  }

  @Override
  public boolean isFinished() {
    return timer.get() >= time;
  }
}
