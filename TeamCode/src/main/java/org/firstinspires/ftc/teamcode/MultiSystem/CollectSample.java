package org.firstinspires.ftc.teamcode.MultiSystem;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.Commands.ClawRollRotateToggleCommand;
import org.firstinspires.ftc.teamcode.Commands.ClawSetPose;
import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

public class CollectSample extends SequentialCommandGroup {
    public CollectSample(ElbowArm elbowArm, ExtenderArm extenderArm, Claw claw, ClawRollRotate clawRollRotate){
        addCommands(
                new InstantCommand(()-> clawRollRotate.setPose(ClawRollRotate.DEFAULT)),
                new ExtenderArmCommand(extenderArm,0),
                new ElbowArmCommand(elbowArm, ElbowArm.COLLECT_SAMPLE),
                new WaitCommand(500),
                new ExtenderArmCommand(extenderArm,ExtenderArm.COLLECTSAMPLE),
                new WaitUntilCommand(() -> elbowArm.getPidController().getPositionError() < 6),
                new WaitCommand(500),
                new ClawSetPose(claw, Claw.CLOSE),
                new WaitCommand(1000),
                new ElbowArmCommand(elbowArm, ElbowArm.DEFAULT)
        );
    }
}
