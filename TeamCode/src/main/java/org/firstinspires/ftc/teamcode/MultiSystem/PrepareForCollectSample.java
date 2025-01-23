package org.firstinspires.ftc.teamcode.MultiSystem;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

public class PrepareForCollectSample extends SequentialCommandGroup {
    public PrepareForCollectSample(ElbowArm elbowArm, ExtenderArm extenderArm, ClawUpDown clawUpDown, ClawRollRotate clawRollRotate){
        addCommands(
                new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.COLLECT)),
                new InstantCommand(()-> clawRollRotate.setPose(ClawRollRotate.SPECIAL)),
                new WaitCommand(700),
                new ExtenderArmCommand(extenderArm,2),
                new ElbowArmCommand(elbowArm,ElbowArm.DEFAULT)
        );
        addRequirements(
                extenderArm,
                clawUpDown,
                clawRollRotate
        );
    }
}