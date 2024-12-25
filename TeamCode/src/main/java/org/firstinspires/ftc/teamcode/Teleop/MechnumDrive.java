package org.firstinspires.ftc.teamcode.Teleop;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;


import org.firstinspires.ftc.teamcode.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.Commands.Imu;
import org.firstinspires.ftc.teamcode.Commands.PIDCommandTest;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrainMecanum;
import org.firstinspires.ftc.teamcode.Subsystems.LeftElbow;
import org.firstinspires.ftc.teamcode.Subsystems.LeftElevator;
import org.firstinspires.ftc.teamcode.Subsystems.MotorControllTest;
import org.firstinspires.ftc.teamcode.Subsystems.RightElbow;
import org.firstinspires.ftc.teamcode.Subsystems.RightElevator;
import org.firstinspires.ftc.teamcode.Commands.RightElbowRunForSeconds;

@TeleOp
public class MechnumDrive extends CommandOpMode
{



    public GamepadEx gamepadEx1;

    //subsystems
    public DriveTrainMecanum driveTrainMecanum;
    public RightElbow rightElbow;
    public RightElevator rightElevator;
    public LeftElbow leftElbow;
    public LeftElevator leftElevator;

    public IMU imu;

    @Override
    public void initialize() {

        //GamePad
        gamepadEx1 = new GamepadEx(gamepad1);

        //Subsystems
        driveTrainMecanum = new DriveTrainMecanum(hardwareMap);
        rightElbow = new RightElbow(hardwareMap);
//        rightElevator = new RightElevator(hardwareMap);
//        leftElbow = new LeftElbow(hardwareMap);
//        leftElevator = new LeftElevator(hardwareMap);

        //mecanum
        driveTrainMecanum.setDefaultCommand(
                new DriveCommand(
                        driveTrainMecanum,
                        gamepadEx1
                )
        );
        gamepadEx1.getGamepadButton(GamepadKeys.Button.A).whenPressed(
                new RightElbowRunForSeconds(rightElbow, 5)
        );

//        gamepadEx1.getGamepadButton(GamepadKeys.Button.START).whenPressed(
//                new Imu(driveTrainMecanum)
//        );

    }
}
