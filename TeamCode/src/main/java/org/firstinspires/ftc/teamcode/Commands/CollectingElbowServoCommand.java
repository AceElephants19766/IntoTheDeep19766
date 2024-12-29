package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.CollectingArmServo;

public class CollectingElbowServoCommand extends CommandBase {
    public CollectingArmServo collectingArmServo;

    public CollectingElbowServoCommand (CollectingArmServo collectingArmServo){
        this.collectingArmServo = collectingArmServo;
        addRequirements(collectingArmServo);
    }

    @Override
    public void initialize() {
        collectingArmServo.setPos(0.5);
    }

    @Override
    public void end(boolean interrupted) {
        collectingArmServo.setPos(0);
    }
}
