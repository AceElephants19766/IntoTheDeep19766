package org.firstinspires.ftc.teamcode.MultiSystem;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

public class PrepaereForScoreSample extends SequentialCommandGroup {
    public PrepaereForScoreSample(ElbowArm elbowArm, ExtenderArm extenderArm, ClawUpDown clawUpDown, ClawRollRotate clawRollRotate){
        addCommands(
                new ElbowArmCommand(elbowArm,ElbowArm.SCORING_SAMPLE),

                new WaitCommand(500),

                new ExtenderArmCommand(extenderArm,elbowArm,ExtenderArm.SCORE),

                new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.SCORING),clawUpDown),
                new InstantCommand(()-> clawRollRotate.setPose(ClawRollRotate.DEFAULT),clawRollRotate)
        );

        addRequirements(
                extenderArm,
                clawUpDown,
                clawRollRotate
        );
    }
}
