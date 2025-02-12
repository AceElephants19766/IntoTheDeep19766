package org.firstinspires.ftc.teamcode.MultiSystem;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.Commands.ClawSetPose;
import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

public class CollectSample extends SequentialCommandGroup {
    public CollectSample(ElbowArm elbowArm, ExtenderArm extenderArm, Claw claw){
        addCommands(
                new ElbowArmCommand(elbowArm,5),
                new WaitCommand(200),
                new ClawSetPose(claw, Claw.CLOSE),
                new WaitCommand(500),
                new ElbowArmCommand(elbowArm, ElbowArm.DEFAULT),
                new WaitUntilCommand(() -> elbowArm.getPidController().getPositionError() < 10),
                new ExtenderArmCommand(extenderArm,ExtenderArm.COLLECT)
        );
        addRequirements(
                extenderArm,
                claw
        );
    }
}
