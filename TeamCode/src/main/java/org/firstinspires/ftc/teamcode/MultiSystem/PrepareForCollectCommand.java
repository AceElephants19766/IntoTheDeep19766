package org.firstinspires.ftc.teamcode.MultiSystem;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

public class PrepareForCollectCommand extends SequentialCommandGroup {
    public PrepareForCollectCommand(ElbowArm elbowArm, ExtenderArm extenderArm, ClawUpDown clawUpDown, ClawRollRotate clawRollRotate){
        addCommands(
                new ElbowArmCommand(elbowArm,20),
                new ExtenderArmCommand(extenderArm,15),
                new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.COLLECT)),
                new InstantCommand(()-> clawRollRotate.SetPose(ClawRollRotate.DEFAULT))
        );
        addRequirements(
                elbowArm,
                extenderArm,
                clawUpDown,
                clawRollRotate
        );
    }
}