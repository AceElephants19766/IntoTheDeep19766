package org.firstinspires.ftc.teamcode.Test.TeleOpTests;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;


import org.firstinspires.ftc.teamcode.Commands.ClawCommand;
import org.firstinspires.ftc.teamcode.Commands.ClawRollRotateCommand;
import org.firstinspires.ftc.teamcode.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.Commands.HangArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ResetImu;
import org.firstinspires.ftc.teamcode.Test.CommandTests.RightElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrainMecanum;
import org.firstinspires.ftc.teamcode.Subsystems.HangArm;
import org.firstinspires.ftc.teamcode.Test.SubsystemTests.RightElbow;

@Disabled
public class MechnumDrive extends CommandOpMode
{



    public GamepadEx gamepadEx1;
    public GamepadEx gamepadEx2;

    //subsystems
    public DriveTrainMecanum driveTrainMecanum;
    public RightElbow rightElbow;
    public Claw claw;
    public ClawRollRotate clawRotat;
    public HangArm hangArm;


    public IMU imu;

    @Override
    public void initialize() {

        //GamePad
        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);

        //Subsystems
        driveTrainMecanum = new DriveTrainMecanum(hardwareMap);
        rightElbow = new RightElbow(hardwareMap);
        claw = new Claw(hardwareMap);
        clawRotat = new ClawRollRotate(hardwareMap);
        hangArm = new HangArm(hardwareMap);
        rightElbow = new RightElbow(hardwareMap);

        //mecanum
        driveTrainMecanum.setDefaultCommand(
                new DriveCommand(
                        driveTrainMecanum,
                        gamepadEx2
                )
        );
        gamepadEx2.getGamepadButton(GamepadKeys.Button.START).whenPressed(
                new ResetImu(driveTrainMecanum)
        );

        gamepadEx1.getGamepadButton(GamepadKeys.Button.B).toggleWhenPressed(
                new ClawCommand(claw,0.5)
        );

        gamepadEx1.getGamepadButton(GamepadKeys.Button.X).toggleWhenPressed(
                new ClawRollRotateCommand(clawRotat,0.5)
        );

        gamepadEx1.getGamepadButton(GamepadKeys.Button.Y).whileActiveOnce(
                new HangArmCommand(hangArm,-0.5)
        );
        gamepadEx1.getGamepadButton(GamepadKeys.Button.A).whileActiveOnce(
                new HangArmCommand(hangArm,0.5)
        );

        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_UP).whileActiveOnce(
                new RightElbowArm(rightElbow,0.5)
        );

        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whileActiveOnce(
                new RightElbowArm(rightElbow,-0.5)
        );


//        gamepadEx1.getGamepadButton(GamepadKeys.Button.A).whenPressed(
//                new RightElbowRunForSeconds(rightElbow, 5)
//        );
//        gamepadEx1.getGamepadButton(GamepadKeys.Button.A).whenPressed(
//                new RightElbowRunForSeconds(rightElbow, 5)
//        );

    }
}
