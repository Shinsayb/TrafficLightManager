package com.example.project.tcpip;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class AppMainController {

    @FXML
    private ImageView BG,
            LBBGE, LGCE, LGLE, LGRE, LGWE, LRCE, LRRE, LRWE, LYCE, LYRE, LBCE, LBRE, LBWE,
            LBBGN, LGCN, LGLN, LGRN, LGWN, LRCN, LRRN, LRWN, LYCN, LYRN, LBCN, LBRN, LBWN,
            LBBGS, LGCS, LGLS, LGRS, LGWS, LRCS, LRRS, LRWS, LYCS, LYRS, LBCS, LBRS, LBWS,
            LBBGW, LGCW, LGLW, LGRW, LGWW, LRCW, LRRW, LRWW, LYCW, LYRW, LBCW, LBRW, LBWW;
    private List<ImageView> allImageViews;
    private List<ImageView> allImageViewsRed;
    @FXML
    private Button buttonAuto, buttonControl;

    private boolean autoModeActive = false;
    private Thread autoThread;
    private Thread redBlinkThread;
    private final Socket socket;

    public AppMainController()
    {
        this.autoModeActive = false;
        this.socket = new Socket();
    }

    public void initialize() {
        try {
            this.socket.connect(new InetSocketAddress("10.10.108.148", Integer.parseInt("9999")));
        } catch (IOException e) {
//            throw new RuntimeException(e);
            System.out.printf("%s\r\n", e.getMessage());
        }
        allImageViews = Arrays.asList(
                LGCE, LGLE, LGRE, LGWE, LRCE, LRRE, LRWE, LYCE, LYRE,
                LGCN, LGLN, LGRN, LGWN, LRCN, LRRN, LRWN, LYCN, LYRN,
                LGCS, LGLS, LGRS, LGWS, LRCS, LRRS, LRWS, LYCS, LYRS,
                LGCW, LGLW, LGRW, LGWW, LRCW, LRRW, LRWW, LYCW, LYRW
        );
        allImageViewsRed = Arrays.asList(
                LRCE, LRRE, LRWE, LRCN, LRRN, LRWN, LRCS, LRRS, LRWS, LRCW, LRRW, LRWW
        );
        loadImage();
    }


    private void loadImage() {
        BG.setImage(loadImage("/img/background.png"));
        LBBGE.setImage(loadImage("/img/e/LBBGE.png"));
        LGCE.setImage(loadImage("/img/e/LGCE.png"));
        LGLE.setImage(loadImage("/img/e/LGLE.png"));
        LGRE.setImage(loadImage("/img/e/LGRE.png"));
        LGWE.setImage(loadImage("/img/e/LGWE.png"));
        LRCE.setImage(loadImage("/img/e/LRCE.png"));
        LRRE.setImage(loadImage("/img/e/LRRE.png"));
        LRWE.setImage(loadImage("/img/e/LRWE.png"));
        LYCE.setImage(loadImage("/img/e/LYCE.png"));
        LYRE.setImage(loadImage("/img/e/LYRE.png"));
        LBCE.setImage(loadImage("/img/e/LBCE.png"));
        LBRE.setImage(loadImage("/img/e/LBRE.png"));
        LBWE.setImage(loadImage("/img/e/LBWE.png"));
        LBBGN.setImage(loadImage("/img/n/LBBGN.png"));
        LGCN.setImage(loadImage("/img/n/LGCN.png"));
        LGLN.setImage(loadImage("/img/n/LGLN.png"));
        LGRN.setImage(loadImage("/img/n/LGRN.png"));
        LGWN.setImage(loadImage("/img/n/LGWN.png"));
        LRCN.setImage(loadImage("/img/n/LRCN.png"));
        LRRN.setImage(loadImage("/img/n/LRRN.png"));
        LRWN.setImage(loadImage("/img/n/LRWN.png"));
        LYCN.setImage(loadImage("/img/n/LYCN.png"));
        LYRN.setImage(loadImage("/img/n/LYRN.png"));
        LBCN.setImage(loadImage("/img/n/LBCN.png"));
        LBRN.setImage(loadImage("/img/n/LBRN.png"));
        LBWN.setImage(loadImage("/img/n/LBWN.png"));
        LBBGS.setImage(loadImage("/img/s/LBBGS.png"));
        LGCS.setImage(loadImage("/img/s/LGCS.png"));
        LGLS.setImage(loadImage("/img/s/LGLS.png"));
        LGRS.setImage(loadImage("/img/s/LGRS.png"));
        LGWS.setImage(loadImage("/img/s/LGWS.png"));
        LRCS.setImage(loadImage("/img/s/LRCS.png"));
        LRRS.setImage(loadImage("/img/s/LRRS.png"));
        LRWS.setImage(loadImage("/img/s/LRWS.png"));
        LYCS.setImage(loadImage("/img/s/LYCS.png"));
        LYRS.setImage(loadImage("/img/s/LYRS.png"));
        LBCS.setImage(loadImage("/img/s/LBCS.png"));
        LBRS.setImage(loadImage("/img/s/LBRS.png"));
        LBWS.setImage(loadImage("/img/s/LBWS.png"));
        LBBGW.setImage(loadImage("/img/w/LBBGW.png"));
        LGCW.setImage(loadImage("/img/w/LGCW.png"));
        LGLW.setImage(loadImage("/img/w/LGLW.png"));
        LGRW.setImage(loadImage("/img/w/LGRW.png"));
        LGWW.setImage(loadImage("/img/w/LGWW.png"));
        LRCW.setImage(loadImage("/img/w/LRCW.png"));
        LRRW.setImage(loadImage("/img/w/LRRW.png"));
        LRWW.setImage(loadImage("/img/w/LRWW.png"));
        LYCW.setImage(loadImage("/img/w/LYCW.png"));
        LYRW.setImage(loadImage("/img/w/LYRW.png"));
        LBCW.setImage(loadImage("/img/w/LBCW.png"));
        LBRW.setImage(loadImage("/img/w/LBRW.png"));
        LBWW.setImage(loadImage("/img/w/LBWW.png"));
    }

    private Image loadImage(String path) {
        Image image = new Image(getClass().getResourceAsStream(path));
        if (image.isError()) {
            System.out.println("Failed to load image: " + path);
        }
        return image;
    }

    private void setAllImagesVisible(boolean visible) {
        Platform.runLater(() -> {
            allImageViews.forEach(imageView -> imageView.setVisible(visible));
        });
    }
    private void setAllImagesRedVisible(boolean visible) {
        Platform.runLater(() -> {
            allImageViewsRed.forEach(imageView -> imageView.setVisible(visible));
        });
    }

    private void updateImageViewAndSendData(ImageView imageView, boolean isVisible, String dataLabel) {
        Platform.runLater(() -> {
            imageView.setVisible(isVisible);
        });
        sendToRaspberryPi(String.format("%s,%s\n", dataLabel, isVisible ? "HIGH" : "LOW"));
    }

    private void sendToRaspberryPi(String data) {
        if (this.socket.isConnected()) {
            byte[] bytesData = data.getBytes(StandardCharsets.UTF_8);
            try {
                OutputStream outputStream = this.socket.getOutputStream();
                outputStream.write(bytesData);
                outputStream.flush();
                System.out.println("Sent to Raspberry Pi: " + data);
            } catch (IOException e) {
                System.out.println("Error sending data to Raspberry Pi: " + e.getMessage());
            }
        } else {
            System.out.println("Socket is not connected.");
        }
    }

    private void signalAuto() throws InterruptedException {
        System.out.println("Activating Auto Signals");

        setAllImagesVisible(false);
        updateImageViewAndSendData(LGCE, true, "LGCE");
        updateImageViewAndSendData(LGLE, true, "LGLE");
        updateImageViewAndSendData(LRCN, true, "LRCN");
        updateImageViewAndSendData(LRCS, true, "LRCS");
        updateImageViewAndSendData(LRCW, true, "LRCW");
        updateImageViewAndSendData(LRRE, true, "LRRE");
        updateImageViewAndSendData(LRRN, true, "LRRN");
        updateImageViewAndSendData(LRRS, true, "LRRS");
        updateImageViewAndSendData(LRRW, true, "LRRW");
        updateImageViewAndSendData(LRWE, true, "LRWE");
        updateImageViewAndSendData(LGWN, true, "LGWN");
        updateImageViewAndSendData(LRWS, true, "LRWS");
        updateImageViewAndSendData(LRWW, true, "LRWW");
        Thread.sleep(1500);

        updateImageViewAndSendData(LGLE, false, "LGLE");
        updateImageViewAndSendData(LRCW, false, "LRCW");
        updateImageViewAndSendData(LGCW, true, "LGCW");
        updateImageViewAndSendData(LYRE, true, "LYRE");
        updateImageViewAndSendData(LRRE, false, "LRRE");
        updateImageViewAndSendData(LRWN, true, "LRWN");
        updateImageViewAndSendData(LGWN, false, "LGWN");
        updateImageViewAndSendData(LRWS, false, "LRWS");
        updateImageViewAndSendData(LGWS, true, "LGWS");
        Thread.sleep(1500);

        updateImageViewAndSendData(LGCE, false, "LGCE");
        updateImageViewAndSendData(LYCE, true, "LYCE");
        updateImageViewAndSendData(LYRE, false, "LYRE");
        updateImageViewAndSendData(LGRE, true, "LGRE");
        updateImageViewAndSendData(LRRW, false, "LRRW");
        updateImageViewAndSendData(LYRW, true, "LYRW");
        updateImageViewAndSendData(LGWS, false, "LGWS");
        updateImageViewAndSendData(LRWS, true, "LRWS");
        updateImageViewAndSendData(LGWW, false, "LGWW");
        Thread.sleep(1500);

        updateImageViewAndSendData(LRCE, true, "LRCE");
        updateImageViewAndSendData(LYCE, false, "LYCE");
        updateImageViewAndSendData(LGLW, true, "LGLW");
        updateImageViewAndSendData(LGRE, false, "LGRE");
        updateImageViewAndSendData(LRRE, true, "LRRE");
        updateImageViewAndSendData(LYRW, false, "LYRW");
        updateImageViewAndSendData(LGRW, true, "LGRW");
        Thread.sleep(1500);

        setAllImagesVisible(false);

        updateImageViewAndSendData(LGCN, true, "LGCN");
        updateImageViewAndSendData(LGLN, true, "LGLN");
        updateImageViewAndSendData(LRCW, true, "LRCW");
        updateImageViewAndSendData(LRCE, true, "LRCE");
        updateImageViewAndSendData(LRCS, true, "LRCS");
        updateImageViewAndSendData(LRRN, true, "LRRN");
        updateImageViewAndSendData(LRRW, true, "LRRW");
        updateImageViewAndSendData(LRRE, true, "LRRE");
        updateImageViewAndSendData(LRRS, true, "LRRS");
        updateImageViewAndSendData(LRWN, true, "LRWN");
        updateImageViewAndSendData(LGWW, true, "LGWW");
        updateImageViewAndSendData(LRWE, true, "LRWE");
        updateImageViewAndSendData(LRWS, true, "LRWS");
        Thread.sleep(1500);

        updateImageViewAndSendData(LGLN, false, "LGLN");
        updateImageViewAndSendData(LRCS, false, "LRCS");
        updateImageViewAndSendData(LGCS, true, "LGCS");
        updateImageViewAndSendData(LYRN, true, "LYRN");
        updateImageViewAndSendData(LRRN, false, "LRRN");
        updateImageViewAndSendData(LRWW, true, "LRWW");
        updateImageViewAndSendData(LGWW, false, "LGWW");
        updateImageViewAndSendData(LRWE, false, "LRWE");
        updateImageViewAndSendData(LGWE, true, "LGWE");
        Thread.sleep(1500);

        updateImageViewAndSendData(LGCN, false, "LGCN");
        updateImageViewAndSendData(LYCN, true, "LYCN");
        updateImageViewAndSendData(LYRN, false, "LYRN");
        updateImageViewAndSendData(LGRN, true, "LGRN");
        updateImageViewAndSendData(LRRS, false, "LRRS");
        updateImageViewAndSendData(LYRS, true, "LYRS");
        updateImageViewAndSendData(LGWE, false, "LGWE");
        updateImageViewAndSendData(LRWE, true, "LRWE");
        updateImageViewAndSendData(LGWS, false, "LGWS");
        Thread.sleep(1500);

        updateImageViewAndSendData(LRCN, true, "LRCN");
        updateImageViewAndSendData(LYCN, false, "LYCN");
        updateImageViewAndSendData(LGLS, true, "LGLS");
        updateImageViewAndSendData(LGRN, false, "LGRN");
        updateImageViewAndSendData(LRRN, true, "LRRN");
        updateImageViewAndSendData(LYRS, false, "LYRS");
        updateImageViewAndSendData(LGRS, true, "LGRS");
        Thread.sleep(1500);

    }

    private void signalEast() throws InterruptedException {
        System.out.println("Activating East Signals");
        setAllImagesVisible(false);
        Platform.runLater(() -> {
            LGCE.setVisible(true);
            LGLE.setVisible(true);
            LGWN.setVisible(true);
            LRRE.setVisible(true);
        });
        Thread.sleep(5000);  // Display green lights for 5 seconds

        Platform.runLater(() -> {
            LGLE.setVisible(false);
            LGWN.setVisible(false);
            LRRE.setVisible(false);
            LRWN.setVisible(true);
            LYRE.setVisible(true);
        });
        Thread.sleep(1500);  // Display yellow right for 3 seconds

        Platform.runLater(() -> {
            LYRE.setVisible(false);
            LGRE.setVisible(true);
            LGCW.setVisible(true);
        });
        Thread.sleep(3000);  // Display green right for 3 seconds

        Platform.runLater(() -> {
            LGRE.setVisible(false);
            LRRE.setVisible(true);
            LRWE.setVisible(true);
        });
    }

    private void signalWest() {
        // Implement the logic for signalWest which will run after signalEast
    }

    @FXML
    private void buttonClickedAuto(ActionEvent event) {
        if (autoThread == null || !autoThread.isAlive()) {
            startAutoMode();
        }
        autoModeActive = !autoModeActive;
        buttonAuto.setText(autoModeActive ? "Pause Auto" : "Resume Auto");

        if (!autoModeActive) {
            synchronized (autoThread) {
                autoThread.notify();
            }
        }
    }


    private void startAutoMode() {
        autoThread = new Thread(() -> {
            try {
                while (true) {
                    synchronized (autoThread) {
                        while (!autoModeActive) {
                            autoThread.wait();
                        }
                    }
                    signalAuto();
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                System.out.println("Auto mode thread interrupted.");
                Thread.currentThread().interrupt();
            }
        });
        autoThread.setDaemon(true);
        autoThread.start();
    }

    @FXML
    private void buttonClickedAllRed(ActionEvent event) {
        setAllImagesVisible(false);
        setAllImagesRedVisible(true);
    }

    @FXML
    private void buttonClickedRedBlink(ActionEvent event) {
        if (redBlinkThread == null || !redBlinkThread.isAlive()) {
            redBlinkThread = new Thread(() -> {
                try {
                    while (true) {
                        setAllImagesVisible(false);
                        setAllImagesRedVisible(true);  // 모든 빨간 이미지 활성화
                        Thread.sleep(500);  // 0.5초 동안 활성화 유지

                        setAllImagesRedVisible(false);  // 모든 빨간 이미지 비활성화
                        Thread.sleep(500);  // 0.5초 동안 비활성화 유지
                    }
                } catch (InterruptedException e) {
                    System.out.println("Red blink thread interrupted.");
                    Thread.currentThread().interrupt();  // 스레드 중단 시 중단 정보를 스레드에 전달
                }
            });
            redBlinkThread.setDaemon(true);
            redBlinkThread.start();
        } else {
            // 이미 블링크 스레드가 실행 중인 경우, 다시 시작하지 않음
            System.out.println("Red blink already running.");
        }
    }
    @FXML
    private void buttonClickedControl(ActionEvent event) {
        System.out.println("Control Screen should be implemented.");
    }

    @FXML
    private void buttonClickedClear(ActionEvent event) {
        setAllImagesVisible(false);  // 모든 이미지를 비활성화
        autoModeActive = false;  // 자동 모드 비활성화
        buttonAuto.setText("Start Auto");  // 버튼 텍스트 초기화
        if (autoThread != null && autoThread.isAlive()) {
            autoThread.interrupt();  // 자동 모드 스레드 중단
        }
        if (redBlinkThread != null && redBlinkThread.isAlive()) {
            redBlinkThread.interrupt();  // 자동 모드 스레드 중단
        }
    }
}