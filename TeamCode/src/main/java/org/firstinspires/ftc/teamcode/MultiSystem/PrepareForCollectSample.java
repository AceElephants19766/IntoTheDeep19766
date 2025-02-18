package org.firstinspires.ftc.teamcode.MultiSystem;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

public class PrepareForCollectSample extends ConditionalCommand {
    public PrepareForCollectSample(ElbowArm elbowArm, ExtenderArm extenderArm, Claw claw, ClawUpDown clawUpDown, ClawRollRotate clawRollRotate){
        super(
                new SequentialCommandGroup(
                        new InstantCommand(()->clawUpDown.setPos(ClawUpDown.PREAPER_SCORING_BACKWARD_SPECIMEN)),
                        new InstantCommand(() -> claw.SetPose(Claw.OPEN)),
                        new WaitCommand(200),
                        new InstantCommand(()->clawUpDown.setPos(ClawUpDown.COLLECT)),
                        new WaitCommand(700),
                        new ExtenderArmCommand(extenderArm,ExtenderArm.COLLECT),
                        new ElbowArmCommand(elbowArm,ElbowArm.DEFAULT),
                        new WaitCommand(500),
                        new ExtenderArmCommand(extenderArm,ExtenderArm.P_F_COLLECTSAMPLE)
                ),
                new SequentialCommandGroup(
                        new InstantCommand(() -> claw.SetPose(Claw.OPEN)),
                        new InstantCommand(()->clawUpDown.setPos(ClawUpDown.COLLECT)),
                        new ElbowArmCommand(elbowArm,ElbowArm.DEFAULT),
                        new WaitCommand(500),
                        new ExtenderArmCommand(extenderArm,ExtenderArm.P_F_COLLECTSAMPLE)
                ),
                ()-> elbowArm.getDeg() > 90

        );
        addRequirements(
                extenderArm,
                claw,
                clawUpDown,
                clawRollRotate
        );
    }
}