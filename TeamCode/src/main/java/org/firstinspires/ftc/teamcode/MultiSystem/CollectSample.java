package org.firstinspires.ftc.teamcode.MultiSystem;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Commands.ClawSetPose;
import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;

public class CollectSample extends SequentialCommandGroup {
    public CollectSample(ElbowArm elbowArm, Claw claw){
        addCommands(
                new ElbowArmCommand(elbowArm, ElbowArm.COLLECT_SAMPLE),
                new WaitCommand(500),
                new ClawSetPose(claw, Claw.CLOSE),
                new WaitCommand(500),
                new ElbowArmCommand(elbowArm, ElbowArm.DEFAULT)
        );
    }
}
