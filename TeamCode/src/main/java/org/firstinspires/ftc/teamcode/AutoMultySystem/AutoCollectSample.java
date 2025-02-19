package org.firstinspires.ftc.teamcode.AutoMultySystem;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Commands.ClawSetPose;
import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

public class AutoCollectSample extends SequentialCommandGroup {
    public AutoCollectSample(ElbowArm elbowArm, ExtenderArm extenderArm, Claw claw, ClawUpDown clawUpDown) {
        addCommands(
                new ElbowArmCommand(elbowArm,10),
                new WaitCommand(500),
                new ClawSetPose(claw, Claw.CLOSE),
                new WaitCommand(200),
                new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.P_F_COLLECT_SPECIMEN)),
                new ExtenderArmCommand(extenderArm, ExtenderArm.COLLECT),
                new ElbowArmCommand(elbowArm, ElbowArm.DEFAULT)
        );
        addRequirements(
                extenderArm,
                claw
        );
    }
}
