package org.firstinspires.ftc.teamcode.MultiSystem;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.Commands.ClawSetPose;
import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

import java.util.function.DoubleSupplier;

public class CollectSample extends SequentialCommandGroup {
    public CollectSample(ElbowArm elbowArm, ExtenderArm extenderArm, Claw claw, ClawUpDown clawUpDown, DoubleSupplier rightTriggerSupplier){
        addCommands(
                //elbow down
                new ElbowArmCommand(elbowArm,(int)(elbowArm.getDeg()-17)),
                //claw down
                new InstantCommand(()-> clawUpDown.setPos(1)),
                new WaitCommand(300),

                //close claw
                new ClawSetPose(claw, Claw.CLOSE),
                new WaitCommand(200),

                //claw up
                new InstantCommand(()->clawUpDown.setPos(ClawUpDown.P_F_COLLECT_SPECIMEN),clawUpDown),
                //elbow up
                new ElbowArmCommand(elbowArm,(int)(elbowArm.getDeg()+17)),
                //return to default
                new ExtenderArmCommand(extenderArm,elbowArm,ExtenderArm.COLLECT),
                new ElbowArmCommand(elbowArm, ElbowArm.COLLECT_SAMPLE)
        );
        addRequirements(
                extenderArm,
                claw
        );
    }
}
