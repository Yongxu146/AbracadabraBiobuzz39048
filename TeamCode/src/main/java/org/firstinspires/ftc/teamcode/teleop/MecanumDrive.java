package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp
public class MecanumDrive extends LinearOpMode {

    private CRServo servo;

    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration

        servo = hardwareMap.get(CRServo.class, "servo0");
        DcMotor FL = hardwareMap.dcMotor.get("FL");
        DcMotor BL = hardwareMap.dcMotor.get("BL");
        DcMotor FR = hardwareMap.dcMotor.get("FR");
        DcMotor BR = hardwareMap.dcMotor.get("BR");
        DcMotor intake = hardwareMap.dcMotor.get("intake");
        DcMotor outake = hardwareMap.dcMotor.get("Outake");
        //DcMotor servo = hardwareMap.dcMotor.get("servo");

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page. BR reversed
        FR.setDirection(DcMotorSimple.Direction.REVERSE);
        FL.setDirection(DcMotorSimple.Direction.REVERSE); //mec mount it reverse
        BR.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.FORWARD);
        intake.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            if(gamepad1.a){intake.setPower(100);}
            else {intake.setPower(0);}
            if(gamepad1.x){outake.setPower(100);}
            else {outake.setPower(0);}

            if(gamepad1.left_trigger_pressed){servo.setPower(1.0);}
            else {servo.setPower(0.0);}

            //if(gamepad1.x){frontLeftPower = 1;}
            //else {frontLeftPower = 0;}
            //if(gamepad1.y){frontRightPower = 1;}
            //else {frontRightPower = 0;}
            //if(gamepad1.a){backLeftPower = 1;}
            //else {backLeftPower = 0;}
            //if(gamepad1.b){backRightPower = 1;}
            //else {backRightPower = 0;}

            FL.setPower(frontLeftPower);
            BL.setPower(backLeftPower);
            FR.setPower(frontRightPower);
            BR.setPower(backRightPower);
        }
    }
}