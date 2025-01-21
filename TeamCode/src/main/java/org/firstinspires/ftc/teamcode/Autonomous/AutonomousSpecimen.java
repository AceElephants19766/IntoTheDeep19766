package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Commands.ActionCommand;
import org.firstinspires.ftc.teamcode.Commands.ElbowKeepPos;
import org.firstinspires.ftc.teamcode.Subsystems.AutoDriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrainMecanum;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;
import org.firstinspires.ftc.teamcode.Subsystems.HangArm;

@Autonomous
public class AutonomousSpecimen extends CommandOpMode {
    //Subsystem
    private AutoDriveTrain autoDriveTrain;

    public Claw claw;
    public ClawRollRotate clawRollRotat;
    public ClawUpDown clawUpDown;
    public HangArm hangArm;
    public ExtenderArm extenderArm;
    public ElbowArm elbowArm;

    public DriveTrainMecanum driveTrainMecanum;

    public Trigger joystickRightYUpCondition;
    public Trigger joystickRightYDownCondition;

    @Override
    public void initialize() {
        //Subsystems
        claw = new Claw(hardwareMap);
        clawRollRotat = new ClawRollRotate(hardwareMap);
        clawUpDown = new ClawUpDown(hardwareMap);
        extenderArm = new ExtenderArm(hardwareMap);
        elbowArm = new ElbowArm(hardwareMap);

        hangArm = new HangArm(hardwareMap);

        Pose2d initialPose = new Pose2d(-40, -62, Math.toRadians(90));
        autoDriveTrain = new AutoDriveTrain(hardwareMap, initialPose);

        elbowArm.setDefaultCommand(
                new ElbowKeepPos(elbowArm)
        );

        TrajectoryActionBuilder redLeftPrepaerForSpicimen = autoDriveTrain.getMecanumDrive().actionBuilder(
                        initialPose
                )
                .setTangent(Math.toRadians(0))
                .splineToSplineHeading(
                        new Pose2d(-10, -35, Math.toRadians(90)),
                        Math.toRadians(90) //tangent
                );

        TrajectoryActionBuilder redLeftBackingUpAfterSpicimen = redLeftPrepaerForSpicimen.endTrajectory().fresh()
                .splineToConstantHeading(new Vector2d(0, -42), Math.toRadians(90));

        TrajectoryActionBuilder redLeftGoToSamples = redLeftBackingUpAfterSpicimen.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .splineToConstantHeading(new Vector2d(-34, -40), Math.toRadians(90));

        schedule(
                new InstantCommand(),
                new SequentialCommandGroup(
                        new ActionCommand(redLeftPrepaerForSpicimen.build(), autoDriveTrain),
                        new ActionCommand(redLeftBackingUpAfterSpicimen.build(), autoDriveTrain),
                        new ActionCommand(redLeftGoToSamples.build(), autoDriveTrain)
                )
        );

    }
}
