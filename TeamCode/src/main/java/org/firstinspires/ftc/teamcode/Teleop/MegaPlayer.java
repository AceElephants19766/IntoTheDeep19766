package org.firstinspires.ftc.teamcode.Teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.ClawRollRotateToggleCommand;
import org.firstinspires.ftc.teamcode.Commands.ClawToggleCommand;
import org.firstinspires.ftc.teamcode.Commands.ClawUpDownToggleCommand;
import org.firstinspires.ftc.teamcode.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ElbowKeepPos;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ExtenderGetToZero;
import org.firstinspires.ftc.teamcode.Commands.ResetImu;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrainMecanum;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;
import org.firstinspires.ftc.teamcode.Subsystems.HangArm;

@TeleOp
public class MegaPlayer extends CommandOpMode {

    //Gampad
    public GamepadEx gamepadEx1;
    public GamepadEx gamepadEx2;

    //Subsystem
    public Claw claw;
    public ClawRollRotate clawRollRotat;
    public ClawUpDown clawUpDown;
    public HangArm hangArm;
    public ExtenderArm extenderArm;
    public ElbowArm elbowArm;

    public DriveTrainMecanum driveTrainMecanum;

    @Override
    public void initialize() {
        //Subsystems
        claw = new Claw(hardwareMap);
        clawRollRotat = new ClawRollRotate(hardwareMap);
        clawUpDown = new ClawUpDown(hardwareMap);
        extenderArm = new ExtenderArm(hardwareMap);
        elbowArm = new ElbowArm(hardwareMap);

        hangArm = new HangArm(hardwareMap);

        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);

        driveTrainMecanum = new DriveTrainMecanum(hardwareMap);

        //mecanum
        driveTrainMecanum.setDefaultCommand(
                new DriveCommand(
                        driveTrainMecanum,
                        gamepadEx2
                )
        );
        //IMU Reset
        gamepadEx2.getGamepadButton(GamepadKeys.Button.START).whenPressed(
                new ResetImu(driveTrainMecanum)
        );

        elbowArm.setDefaultCommand(
                new ElbowKeepPos(elbowArm)
        );
        //extender arm PID
        gamepadEx1.getGamepadButton(GamepadKeys.Button.Y).whenPressed(
                new ExtenderArmCommand(extenderArm,30)
        );
        gamepadEx1.getGamepadButton(GamepadKeys.Button.A).whenPressed(
                new ExtenderGetToZero(extenderArm)
        );

        //elbow arm up & down
        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(
                new ElbowArmCommand(elbowArm,90)
        );
        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(
                new ElbowArmCommand(elbowArm,10 )
        );

        //claw up down safe pos
        gamepadEx2.getGamepadButton(GamepadKeys.Button.B).whenPressed(
                new ClawUpDownToggleCommand(clawUpDown,0.64)
        );

        //claw open&close
        gamepadEx2.getGamepadButton(GamepadKeys.Button.B).toggleWhenPressed(
                new ClawToggleCommand(claw)
        );

        //Claw roll rotation
        gamepadEx2.getGamepadButton(GamepadKeys.Button.X).toggleWhenPressed(
                new ClawRollRotateToggleCommand(clawRollRotat,ClawRollRotate.DEFAULT)
        );

        //Claw up & down
        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).toggleWhenPressed(
                new ClawUpDownToggleCommand(clawUpDown, ClawUpDown.COLLECT)
        );

    }

    @Override
    public void run() {
        super.run();
        telemetry.addData("Extender",extenderArm.getLength());
        telemetry.update();
    }
}