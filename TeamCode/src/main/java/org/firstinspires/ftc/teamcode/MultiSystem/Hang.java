package org.firstinspires.ftc.teamcode.MultiSystem;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

public class Hang extends ParallelCommandGroup {
    public Hang(ElbowArm elbowArm, ExtenderArm extenderArm,ClawUpDown clawUpDown , ClawRollRotate clawRollRotate){
        addCommands(
                new InstantCommand(()-> clawUpDown.setPos(ClawUpDown.COLLECT)),
                new InstantCommand(()-> clawRollRotate.setPose(ClawRollRotate.SPECIAL)),
                new ExtenderArmCommand(extenderArm,elbowArm,12),
                new ElbowArmCommand(elbowArm,138)
        );
    }
}
