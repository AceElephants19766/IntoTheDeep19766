package org.firstinspires.ftc.teamcode.Teleop;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.Subsystems.DriveTrainMecanum;
import org.firstinspires.ftc.teamcode.Subsystems.LeftElbow;
import org.firstinspires.ftc.teamcode.Subsystems.LeftElevator;
import org.firstinspires.ftc.teamcode.Subsystems.RightElbow;
import org.firstinspires.ftc.teamcode.Subsystems.RightElevator;
import org.firstinspires.ftc.teamcode.Commands.RightElbowRunForSeconds;

@TeleOp
public class MechnumDrive extends CommandOpMode
{


    boolean firstRun = true;

    //subsystems
    public DriveTrainMecanum driveTrainMecanum;
    public RightElbow rightElbow;
    public RightElevator rightElevator;
    public LeftElbow leftElbow;
    public LeftElevator leftElevator;

    //commands
    public Command runForFiveSec;


    @Override
    public void initialize() {
        //Subsystems
        driveTrainMecanum = new DriveTrainMecanum(hardwareMap);
//      mecanumDrive.setDefaultCommand(mj njii);
        rightElbow = new RightElbow(hardwareMap);
        rightElevator = new RightElevator(hardwareMap);
        leftElbow = new LeftElbow(hardwareMap);
        leftElevator = new LeftElevator(hardwareMap);

        //Commands
        runForFiveSec = new RightElbowRunForSeconds(rightElbow,500);

    }

    @Override
    public void run() {
        super.run();

        if(firstRun) {
            runForFiveSec.schedule();
            firstRun = false;
        }
        //mecanum

        //inputs
        double x = gamepad1.left_stick_x*1.1;
        double y = -gamepad1.left_stick_y;
        double rx = gamepad1.right_stick_x;

    }
}
