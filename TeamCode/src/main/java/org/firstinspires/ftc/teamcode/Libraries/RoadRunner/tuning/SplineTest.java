package org.firstinspires.ftc.teamcode.Libraries.RoadRunner.tuning;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Libraries.RoadRunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.Libraries.RoadRunner.TankDrive;

public final class SplineTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d beginPose = new Pose2d(50, -50, Math.toRadians(90));
        if (TuningOpModes.DRIVE_CLASS.equals(MecanumDrive.class)) {
            MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

            waitForStart();

            Actions.runBlocking(
                drive.actionBuilder(beginPose)
                    .setTangent(Math.toRadians(90))
                    .lineToY(50)
                    .turn(Math.toRadians(90))
                    .lineToX(-50)
                    .turn(Math.toRadians(90))
                    .lineToY(-50)
                    .turn(Math.toRadians(90))
                    .lineToX(50)
                    .build());
        } else if (TuningOpModes.DRIVE_CLASS.equals(TankDrive.class)) {
            TankDrive drive = new TankDrive(hardwareMap, beginPose);

            waitForStart();

            Actions.runBlocking(
                    drive.actionBuilder(beginPose)
                    .setTangent(Math.toRadians(90))
                    .lineToY(50)
                    .turn(Math.toRadians(90))
                    .lineToX(-50)
                    .turn(Math.toRadians(90))
                    .lineToY(-50)
                    .turn(Math.toRadians(90))
                    .lineToX(50)
                    .build());
        } else {
            throw new RuntimeException();
        }
    }
}
