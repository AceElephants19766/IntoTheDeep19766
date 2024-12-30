package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.CollectingArmServo;

public class CollectingElbowServoCommand extends CommandBase {

    public double pos;
    public CollectingArmServo collectingArmServo;

    public CollectingElbowServoCommand (CollectingArmServo collectingArmServo,double pos){
        this.collectingArmServo = collectingArmServo;
        this.pos = pos;
        addRequirements(collectingArmServo);
    }

    @Override
    public void initialize() {
        collectingArmServo.Lift(pos);
    }

    @Override
    public void end(boolean interrupted) {
        collectingArmServo.Lift(0);
    }
}
