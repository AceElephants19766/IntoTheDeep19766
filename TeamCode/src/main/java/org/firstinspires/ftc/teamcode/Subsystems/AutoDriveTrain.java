package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Libraries.RoadRunner.MecanumDrive;

public class AutoDriveTrain extends SubsystemBase {

    private MecanumDrive mecanumDrive;

    public AutoDriveTrain (HardwareMap hardwareMap, Pose2d initialPose){
        mecanumDrive = new MecanumDrive(hardwareMap, initialPose);
    }

    public MecanumDrive getMecanumDrive() {
        return mecanumDrive;
    }

}