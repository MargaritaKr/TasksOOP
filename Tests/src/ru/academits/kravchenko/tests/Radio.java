package ru.academits.kravchenko.tests;

public class Radio implements ElectricityConsumer {
    private void playMusic(){
        System.out.println("Играет музыка");
    }

    @Override
    public void electricityOn() {
        playMusic();

    }
}
