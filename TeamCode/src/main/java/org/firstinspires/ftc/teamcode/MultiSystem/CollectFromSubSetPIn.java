package org.firstinspires.ftc.teamcode.MultiSystem;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

import java.util.function.DoubleSupplier;

public class CollectFromSubSetPIn extends ParallelCommandGroup {
    public CollectFromSubSetPIn(ElbowArm elbowArm, ExtenderArm extenderArm, ClawUpDown clawUpDown, Claw claw, DoubleSupplier rightTriggerSupplier){
        addCommands(
                new InstantCommand(()->claw.SetPose(Claw.OPEN)),
                new InstantCommand(()-> clawUpDown.setPos(ClawUpDown.COLLECT)),
                new InstantCommand(()-> extenderArm.setPower(-0.6)),
                new InstantCommand(()-> {
                    int ang = (int)(Math.toDegrees(Math.acos((25.0/(38+(extenderArm.getLength())))))) -48;
                        elbowArm.getPidController().setSetPoint(ang);
                })
        );
        addRequirements(
                clawUpDown,
                claw
        );
    }
}
