package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.Subsystems.DriveTrainMecanum;

public class Imu extends InstantCommand {

    public DriveTrainMecanum driveTrainMecanum;
    public IMU imu;

    public Imu(DriveTrainMecanum driveTrainMecanum){
        this.driveTrainMecanum = driveTrainMecanum;
    }

    @Override
    public void initialize() {
        super.initialize();
        imu.resetYaw();
    }
}
